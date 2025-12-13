import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivityReportSearchTableService } from './activity-report-search-table.service';
import { BaseDto } from 'BaseDto';
import { activityStatusSearchDomain, ActivityTypeDomain, AutoActivityNoSearch, FilterSearchReport } from 'ActualReportModule';
import { ActivityReportSearchTableContainerService } from '../activity-report-search-table-container/activity-report-search-table-container.service';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { ColumnSearch } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { DateService } from '../../../../../root-service/date.service';
import { MarketingActivitySearchContainerService } from '../../../activity-proposal/component/marketing-activity-search-container/marketing-activity-search-container.service'
import { MarketingActivitySearchService } from '../../../activity-proposal/component/marketing-activity-search/marketing-activity-search.service'
import { ToastrService } from 'ngx-toastr';
import { AutoDealerCodeSearch } from 'ActivityProposalModule';
import { AutoCompSubModel, PopulateByItemNo } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/enquiry';
import { ModelBySeries, Product, SeriesByProduct, Variants } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/search-enquiry-v2';
import { SearchEnquiryV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/search-enquiry-v2-web.service';
import { ProductInterestedV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/product-interested-v2-web.service';
@Component({
  selector: 'app-activity-report-search-table',
  templateUrl: './activity-report-search-table.component.html',
  styleUrls: ['./activity-report-search-table.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }, ActivityReportSearchTableService, ActivityReportSearchTableContainerService, MarketingActivitySearchContainerService, MarketingActivitySearchService,
    SearchEnquiryV2WebService,ProductInterestedV2WebService
  ]
})
export class ActivityReportSearchTableComponent implements OnInit {

  activityTypeList: BaseDto<Array<ActivityTypeDomain>>
  dropdowngetActivityType: BaseDto<Array<ActivityTypeDomain>>
  @Input() totalTableElements: number
  actionButtons = [];
  size = 10;
  page = 0;
  searchValue: ColumnSearch = {} as ColumnSearch
  autoActivityNo: BaseDto<Array<AutoActivityNoSearch>>
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  minDate: Date;
  maxDate: Date
  @Input() getSearchActivityStatus: BaseDto<Array<activityStatusSearchDomain>>
  dataTable: DataTable;
  isShowAdvanceFilter: boolean
  searchReportListForm: FormGroup
  public loginUserType: string;
  public filterData: object = {};
  isKai: boolean = true;
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  searchFlag: boolean = true;

  activityNumberNgModel: any = "";
  activityCreationDateNgModel: any = "";
  activityTypeNgModel: any = "";
  activityStatusNgModel: any = "";
  fromDateNgModel: any = "";
  toDateNgModel: any = "";
  actualFromDateNgModel: any = "";
  actualToDateNgModel: any = "";
  totalEnquiriesNgModel: any = "";
  totalRetailsNgModel: any = "";
  totalBookingsNgModel: any = "";
  searchFilterValues: any;
  key='arst';

  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  branches:any[] = [];
  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  modelBySeries: Array<ModelBySeries> = []
  subModelByModel: Array<AutoCompSubModel> = []
  model: any
  variants: Array<Variants> = []


  constructor(
    private activityReportSearchTableService: ActivityReportSearchTableService,
    private tableDataService: TableDataService,
    private router: Router,
    private actRt: ActivatedRoute,
    private fb: FormBuilder,
    private loginFormService: LoginFormService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private marketingActivitySearchContainerService: MarketingActivitySearchContainerService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private toastr: ToastrService,
    private searchEnquiryV2WebService:SearchEnquiryV2WebService,
    private productInterestedV2WebService:ProductInterestedV2WebService
    
  ) { this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase() }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.marketingSearchReportListForm()
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchReportListForm.patchValue(this.searchFilterValues);
      this.page = this.searchFilterValues.page;
      this.size = this.searchFilterValues.size;
    }
    localStorage.removeItem('searchFilter');
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate() + 1);
    this.getActivityType();

    this.searchReportListForm.controls.activityNumber.valueChanges.subscribe(value => {
      if (value) {
        this.autoActivityNumber(value)
      }
    })

    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    // this.onSearchActivityReport();
    if (this.searchReportListForm.get('activityNumber').value == null && this.searchReportListForm.get('activityType').value == null && this.searchReportListForm.get('fromDate').value == null && this.searchReportListForm.get('toDate').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.searchReportListForm.get('fromDate').patchValue(backDate);
      this.searchReportListForm.get('toDate').patchValue(new Date());

      this.onSearchActivityReport();
    }
    else{
      localStorage.getItem(this.key)
    this.onSearchActivityReport();
    }
    this.searchReportListForm.controls.dealerShip.valueChanges.subscribe((res) => {
      if (res) {
        this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })
    this.getBranches()
    this.  getProduct()
  }
  ngAfterViewInit() {

  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchReportListForm.get('toDate').value > this.maxDate)
        this.searchReportListForm.get('toDate').patchValue(this.maxDate);
    }
  }
  getOrgLevelByHODept() {
    this.marketingActivitySearchService.getOrgLevelByHODept('MK').subscribe(response => {
      this.orgLevels = response['result'];
      this.getOrgLevelHierForParent(this.orgLevels[0]['LEVEL_ID'], 0);
    })
  }
  getOrgLevelHierForParent(levelId, hierId) {
    this.marketingActivitySearchService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
      this.orgHierLevels1 = response['result'];
    });
  }

  getOrgLevelHier2(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels2 = response['result'];
      });
    } else {
      this.orgHierLevels2 = [];
      this.searchReportListForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchReportListForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchReportListForm.controls.orgHierLevel3.reset();
    this.searchReportListForm.controls.orgHierLevel4.reset();
    this.searchReportListForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchReportListForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchReportListForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchReportListForm.controls.orgHierLevel4.reset();
    this.searchReportListForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchReportListForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchReportListForm.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.searchReportListForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchReportListForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchReportListForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }

  private getActivityType() {
    this.activityReportSearchTableService.getActivityType().subscribe(res => {
      this.dropdowngetActivityType = res as BaseDto<Array<ActivityTypeDomain>>
    })
  }

  autoActivityNumber(event) {
    this.marketingActivitySearchContainerService.getActivityNo(event, 'SEARCH').subscribe(response => {
      console.log('response', response);
      this.autoActivityNo = response as BaseDto<Array<AutoActivityNoSearch>>
    })
  }

  onSearchActivityReport() {

    this.activityNumberNgModel = "";
    this.activityCreationDateNgModel = "";
    this.activityTypeNgModel = "";
    this.activityStatusNgModel = "";
    this.fromDateNgModel = "";
    this.toDateNgModel = "";
    this.actualFromDateNgModel = "";
    this.actualToDateNgModel = "";
    this.totalEnquiriesNgModel = "";
    this.totalRetailsNgModel = "";
    this.totalBookingsNgModel = "";

    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
    else {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
      }
    }
    this.searchFlag = true;
    const filterObj = this.searchReportListForm.value as FilterSearchReport
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchReportListForm.value))

    filterObj.page = this.page
    filterObj.size = this.size
    filterObj.activityNumber = filterObj ? filterObj.activityNumber : null
    filterObj.fromDate = filterObj.fromDate ? this.convertDateToServerFormat(filterObj.fromDate) : null
    filterObj.toDate = filterObj.toDate ? this.convertDateToServerFormat(filterObj.toDate) : null
    filterObj.hierId = this.searchReportListForm.value.orgHierLevel5 ? this.searchReportListForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchReportListForm.value.orgHierLevel4 ? this.searchReportListForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchReportListForm.value.orgHierLevel3 ? this.searchReportListForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchReportListForm.value.orgHierLevel2 ? this.searchReportListForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchReportListForm.value.orgHierLevel1 ? this.searchReportListForm.value.orgHierLevel1['org_hierarchy_id'] : 0)));
    //alert(filterObj.hierId)
    this.filterData = this.removeNullFromObjectAndFormatDate(filterObj);

    if ((filterObj['fromDate'] === null || filterObj['fromDate'] === "" || filterObj['fromDate'] === undefined)) {
      if ((filterObj['toDate'] === null || filterObj['toDate'] === "" || filterObj['toDate'] === undefined)) {
        if (this.searchFlag == true && this.searchReportListForm.get('activityNumber').value || this.searchReportListForm.get('activityType').value || this.searchReportListForm.get('fromDate').value || this.searchReportListForm.get('toDate').value) {

          this.activityReportSearchTableService.getSearchData(filterObj).subscribe(response => {
            this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
            this.totalTableElements = response.count
          })
        } else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    } else if ((filterObj['fromDate'] !== null || filterObj['fromDate'] !== "" || filterObj['fromDate'] !== undefined)) {
      if ((filterObj['toDate'] === null || filterObj['toDate'] === "" || filterObj['toDate'] === undefined)) {
        this.toastr.error("Please Select Date Range.");
      } else {
        this.activityReportSearchTableService.getSearchData(filterObj).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
          console.log('data--',response)
          this.totalTableElements = response.count
        })
      }
    }
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchReportListForm.patchValue(event);
    if (event.activityNumber) {
      this.searchReportListForm.get('activityNumber').patchValue(event.activityNumber.replace(searchValue, newValue));
      event.activityNumber = event.activityNumber.replace(searchValue, newValue)
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }

  clearActivityReportSearch() {
    this.searchReportListForm.reset()
    this.dataTable = null
    localStorage.removeItem(this.key)
  }

  actionOnTableRecord(recordData) {
    console.log(recordData);
    if (recordData['btnAction'].toLocaleLowerCase() === 'activitynumber') {
      this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.actRt })
    }
  }

  etSearchDateValueChange(searchDate, ColumnKey) {
    console.log('etSearchDateValueChange', searchDate);
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  pageChange(event) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.onSearchActivityReport()
  }

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      console.log(formattedDate)
      return formattedDate
    }
    return ''
  }

  private marketingSearchReportListForm() {
    this.searchReportListForm = this.fb.group({
      activityNumber: [null],
      activityType: [null],
      //activityStatus: [null],
      fromDate: [null],
      toDate: [null],
      dealerShip:[null],
      branch:[],
      product:[],
      series:[],
      model:[],
      subModel:[],
      variant:[],
      orgHierLevel1: [null],
      orgHierLevel2: [null],
      orgHierLevel3: [null],
      orgHierLevel4: [null],
      orgHierLevel5: [null]
    })
  }


  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  getBranches(){
    this.activityReportSearchTableService.getBranchCodeList().subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.searchReportListForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }

  getProduct(){
    this.searchEnquiryV2WebService.getAllProduct().subscribe(response => {
      this.products = response.result
    })
  }
  selectProduct(value) {
    this.searchEnquiryV2WebService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response.result
    })
  }
  selectSeries(value) {
    this.searchEnquiryV2WebService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response.result
    })
  }

  selectModel(value) {
    this.model = value;
    this.productInterestedV2WebService.getSubModel(value).subscribe(response => {
      this.subModelByModel = response.result
    })
  }

  selectSubModel(value) {
    this.productInterestedV2WebService.getVariant(this.model, value).subscribe(response => {
      this.variants = response
    })
  }
  
  compareFnSeries(c1: SeriesByProduct, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
    }
    return c1 && c2 ? c1.series === c2.series : c1 === c2;
  }

  compareFnModel(c1: ModelBySeries, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.model === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.model;
    }
    return c1 && c2 ? c1.model === c2.model : c1 === c2;
  }

  compareFnSubModel(c1: AutoCompSubModel, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subModel === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subModel;
    }
    return c1 && c2 ? c1.subModel === c2.subModel : c1 === c2;
  }
}
