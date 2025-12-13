import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { BehaviorSubject, Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { MarketingActivitySearchService } from 'src/app/sales-pre-sales/activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { Category } from '../market-intelligence-create/market-intelligence-create.component';
import { MarketIntelligenceService } from '../market-intelligence.service';
import { saveAs } from 'file-saver';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-market-intelligence-search',
  templateUrl: './market-intelligence-search.component.html',
  styleUrls: ['./market-intelligence-search.component.scss'],
  providers: [MarketingActivitySearchService, MarketIntelligenceService]
})
export class MarketIntelligenceSearchComponent implements OnInit {

  public page = 0;
  public size = 10;
  public actionButtons = [];
  public dataTable: DataTable;
  public minToDate = new Date();
  public maxToDate = new Date();
  public todaysDate = new Date();
  public totalTableElements: number;
  private searchFlag: boolean = true;
  public searchValue: ColumnSearch = {} as ColumnSearch
  userNameNgModel="";
  dealerNameNgModel="";
  dealerCodeNgModel="";
  feedbackCategoryNgModel="";
  feedbackNgModel="";
  dateNgModel="";
  productNgModel="";
  competitorNgModel="";
  private searchFilterValues: any;
  private loginUserType: string;
  public isKai: boolean = true;
  public dealercodeList: any[]
  public filterData: object;

  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];

  categories: Category[];

  clearSearchRow = new BehaviorSubject<string>("");

  constructor(private formBuilder: FormBuilder,
    private searchTableService: NgswSearchTableService,
    private dateService: DateService,
    private mtService: MarketIntelligenceService,
    private loginService: LoginFormService,
    private toastr:ToastrService,
    private marketingActivitySearchService: MarketingActivitySearchService) { }

  searchMIForm = this.formBuilder.group({
    fromDate: [null],
    toDate: [null],
    feedbackCategory: [null] ,
    orgHierLevel1: [null],
    orgHierLevel2: [null],
    orgHierLevel3: [null],
    orgHierLevel4: [null],
    orgHierLevel5: [null],
    dealer: [null]
  });

  ngOnInit() {
    
    let backDate = new Date();
    backDate.setMonth(this.todaysDate.getMonth() - 1);
    this.minToDate = backDate;

    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
    this.searchFilterValues = localStorage.getItem('searchFilter');
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)));
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchMIForm.patchValue(this.searchFilterValues)
    } else {
      this.searchMIForm.get('toDate').patchValue(this.todaysDate);
      this.searchMIForm.get('fromDate').patchValue(backDate);
    }
    
    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
      this.getDealerCode();
    }

    this.searchMIData();

    this.mtService.dropdownFeedbackCategory().subscribe(response => {
      this.categories = response['result'];
    })
  }

  searchMIData(){
    let searchObj = this.searchMIForm.value;
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchMIForm.value))


    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = 0
      }
    }
    searchObj.orgHierId = this.searchMIForm.value.orgHierLevel5 ? this.searchMIForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel4 ? this.searchMIForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel3 ? this.searchMIForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel2 ? this.searchMIForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchMIForm.value.orgHierLevel1 ? this.searchMIForm.value.orgHierLevel1['org_hierarchy_id'] : null)))
    searchObj.dealerId = this.searchMIForm.get('dealer').value ? this.searchMIForm.get('dealer').value.id : null

    if (searchObj['fromDate'] && searchObj['toDate']) {
      searchObj['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['fromDate']);
      searchObj['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['toDate'])
    }else if (searchObj['fromDate'] || searchObj['toDate']) {
      this.toastr.error("Please Select Date Range");
      return;
    }
    
    Object.keys(searchObj).forEach(key => {
      ((searchObj[key] === null) || (searchObj[key] === '')) ? delete searchObj[key] : searchObj[key];
    });

    delete searchObj['page'];
    delete searchObj['size'];
    if(Object.keys(searchObj).length==0){
      this.toastr.error("Please fill atleast one input field");
      return;
    }
    searchObj['page'] = this.page;
    searchObj['size'] = this.size;

    this.mtService.fetchSearchData(searchObj).subscribe(response => {
      if (response) {
        this.dataTable = this.searchTableService.convertIntoDataTable(response['result']);
        this.totalTableElements = response['count'];
      }
    });
  }

  public pageChange(event) {
    if (!!event && event.page >= 0) {
      this.page = event.page;
      this.size = event.size
      this.searchFlag = false;
      this.searchMIData();
    }
  }

  getDealerCode() {
    this.searchMIForm.controls.dealer.valueChanges.subscribe(res => {
      if(res){
        this.searchMIForm.controls.dealer.setErrors({'selectFromList':'Select From List'})
        if(typeof res == 'object'){
          this.searchMIForm.controls.dealer.setErrors(null);
          res = res['code'];
        }
        this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response['result'];
        })
      } else {
        this.dealercodeList = [];
      }
    })
  }

  displayDealerFn(event){
    return typeof event == 'object'? event['code'] : event;
  }
  getOrgLevelByHODept() {
    this.marketingActivitySearchService.getOrgLevelByHODept('SA').subscribe(response => {
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
      this.searchMIForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchMIForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchMIForm.controls.orgHierLevel3.reset();
    this.searchMIForm.controls.orgHierLevel4.reset();
    this.searchMIForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchMIForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchMIForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchMIForm.controls.orgHierLevel4.reset();
    this.searchMIForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchMIForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchMIForm.controls.orgHierLevel3.patchValue(undefined);
    }
    this.orgHierLevels5 = [];
    this.searchMIForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchMIForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchMIForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }
  getOrgLevelHier6(hier) {
    if (typeof hier === 'string') {
      this.searchMIForm.controls.orgHierLevel5.patchValue(undefined);
    }
  }

  clearForm() {
    this.searchMIForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
    this.userNameNgModel="";
    this.dealerNameNgModel="";
    this.dealerCodeNgModel="";
    this.feedbackCategoryNgModel="";
    this.feedbackNgModel="";
    this.dateNgModel="";
    this.productNgModel="";
    this.competitorNgModel="";
  }

  public dateChanges(event, keyInObject: string) {
    if (event && event['value']) {
      const date: Date = event.value as Date
      const searchValue = {
        searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
        keyInObject
      };
      this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.keyInObject);
    } else {
      this.searchValue = new ColumnSearch("", keyInObject);
    }
  }

  public fromDateSelected(event) {
    if (event && event['value']) {
      this.minToDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.todaysDate)
        this.maxToDate = this.todaysDate;
      else
        this.maxToDate = maxDate;
      if (this.searchMIForm.get('toDate').value > this.maxToDate)
        this.searchMIForm.get('toDate').patchValue(this.maxToDate);
    }
  }

  downloadExcel(event) {
    const searchObj = this.searchMIForm.getRawValue();
    searchObj['page'] = this.page;
    searchObj.orgHierId = this.searchMIForm.value.orgHierLevel5 ? this.searchMIForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel4 ? this.searchMIForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel3 ? this.searchMIForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchMIForm.value.orgHierLevel2 ? this.searchMIForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchMIForm.value.orgHierLevel1 ? this.searchMIForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))
    searchObj.dealerId = this.searchMIForm.get('dealer').value ? this.searchMIForm.get('dealer').value.id : null

    if (searchObj['fromDate'] && searchObj['toDate']) {
      searchObj['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['fromDate']);
      searchObj['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['toDate'])
    }
    
    Object.keys(searchObj).forEach(key => {
      ((searchObj[key] === null) || (searchObj[key] === '')) ? delete searchObj[key] : searchObj[key];
    });
    
    this.downloadExcelReport(searchObj);
  }

  private downloadExcelReport(searchObject) {
    this.mtService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('Content-Disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
}
