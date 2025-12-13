import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { SalesPurchaseOrderSearchServiceService } from './sales-purchase-order-search-service.service';
import { FormGroup } from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ColumnSearch } from 'ngsw-search-table';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { AutoDealerCodeSearch, SearchFilterPurchaseDomain } from 'PurchaseSearchModule';
import { MarketingActivitySearchService } from '../../../../activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { SearchEnquiryV2WebService } from '../../../../pre-sales/enquiry-v2/services/search-enquiry-v2-web.service';
import { ProductInterestedV2WebService } from '../../../../pre-sales/enquiry-v2/services/product-interested-v2-web.service';
import { SavePurchaseOrder } from '../../pages/purchase-order-create/purchase-order-create';
import { MatDatepickerInput } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { DateService } from 'src/app/root-service/date.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { SalesPurchaseOrderSearchContainerServiceService } from '../sales-purchase-order-search-container/sales-purchase-order-search-container-service.service';

@Component({
  selector: 'app-sales-purchase-order-search',
  templateUrl: './sales-purchase-order-search.component.html',
  styleUrls: ['./sales-purchase-order-search.component.scss'],
  providers: [SalesPurchaseOrderSearchServiceService, AutocompleteService, MarketingActivitySearchService, SearchEnquiryV2WebService, ProductInterestedV2WebService]
})
export class SalesPurchaseOrderSearchComponent implements OnInit {

  searchPoForm: FormGroup;
  @Input() isShowAdvanceFilter: boolean = false;
  model: string
  @Input() displayPoNumberFn: Function;
  @Input() poNumberAutoList: Observable<(object | string)[]>;
  @Input() displayItemNumberFn: Function;
  @Input() itemNumberAutoList: Observable<(object | string)[]>;
  @Input() poTypesList = [];
  @Input() depotsList = [];
  @Input() poStatusList = [];
  @Input() dealerList = [];
  @Input() productsList = [];
  @Input() seriesList = [];
  @Input() modelsList = [];
  clearSearchRow = new BehaviorSubject<string>("");
  @Input() submodelsList = [];
  @Input() variantsList = [];
  @Input() searchFilter: any;

  @Output() poNumberAutoChange: EventEmitter<string> = new EventEmitter<string>();
  @Output() itemNumberAutoChange: EventEmitter<string> = new EventEmitter<string>();
  @Output() getSearchFormValues: EventEmitter<SearchFilterPurchaseDomain> = new EventEmitter<SearchFilterPurchaseDomain>();
  @Output() tableAction: EventEmitter<string> = new EventEmitter<string>();
  @Output() pageChange: EventEmitter<string> = new EventEmitter<string>();
  @Output() initialQueryParamsEvent: EventEmitter<object> = new EventEmitter<object>();
  @Output() onUrlChangeEvent: EventEmitter<object> = new EventEmitter<object>();
  @Input() totalTableElements: number;
  @Input() dataTable: DataTable;
  @Input() actionButtons = [];

  public isKaiLogin: boolean;
  public loginUserType: string;
  public selectedFromDate: Date;
  public todaysDate = new Date();
  public searchValue: ColumnSearch;
  minDate: Date;
  maxDate: Date
  isKai: boolean = true;

  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>

  poNumberNgModel: any = "";
  lastApprovedByModel: any = "";
  poDateNgModel: any = "";
  poTypeNgModel: any = "";
  dealerCodeNgModel: any = "";
  dealerNameNgModel: any = "";
  depotNgModel: any = "";
  totalQuantityNgModel: any = "";
  poStatusNgModel: any = "";
  totalCreditLimitNgModel: any = "";
  availableLimitNgModel: any = "";
  searchFilterValues: any;
  searchFlag: boolean = true;
  page: number = 0;
  size: number = 10;
  key = 'searchFilterPo';
  constructor(
    private loginService: LoginFormService,
    private localStorageService: LocalStorageService,
    private autocompleteService: AutocompleteService,
    private salesPurchaseOrderSearchServiceService: SalesPurchaseOrderSearchServiceService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private searchEnquiryV2WebService: SearchEnquiryV2WebService,
    private productInterestedV2WebService: ProductInterestedV2WebService,
    private toastr: ToastrService,
    private dateService: DateService,
    private salesPurchaseOrderSearchContainerServiceService: SalesPurchaseOrderSearchContainerServiceService,
  ) {
    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
    this.isKaiLogin = this.localStorageService.getLoginUser().dealerCode ? false : true;
  }


  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    localStorage.removeItem('searchFilter');
    this.searchFilterValues = localStorage.getItem('searchFilterPo')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchPoForm = this.salesPurchaseOrderSearchServiceService.createForm();
    console.log('this.searchPoForm---', this.searchPoForm)
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchPoForm != null) {
      this.searchPoForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem(this.key);

    this.poNumberValueChange();
    this.itemNumberValueChange();

    this.searchPoForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        if (typeof res === 'string') {
          this.searchPoForm.controls.dealerName.patchValue('');
        }
        this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })

    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    if (this.searchPoForm.get('poNumber').value == null && this.searchPoForm.get('poType').value == null && this.searchPoForm.get('depot').value == null && this.searchPoForm.get('itemNo').value == null && this.searchPoForm.get('fromDate').value == null && this.searchPoForm.get('toDate').value == null) {
      this.maxDate = this.todaysDate
      let backDate = new Date();
      backDate.setMonth(this.todaysDate.getMonth() - 1);
      this.minDate = backDate;
      this.searchPoForm.get('fromDate').patchValue(backDate);
      this.searchPoForm.get('toDate').patchValue(new Date());
      this.searchPo();
    }
    else {
      localStorage.getItem(this.key)
      this.searchPo();
    }

  }
  // ngAfterViewInit() {
  //   this.searchPo();
  //   this.maxDate = this.todaysDate
  //   let backDate = new Date();
  //   backDate.setMonth(this.todaysDate.getMonth() - 1);
  //   this.minDate = backDate;
  //   this.searchPoForm.get('fromDate').patchValue(backDate);
  //   this.searchPoForm.get('toDate').patchValue(new Date());
  // }

  resetFormControlNotHavingObject = this.autocompleteService.resetFormControlNotHavingObject;

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
      this.searchPoForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchPoForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchPoForm.controls.orgHierLevel3.reset();
    this.searchPoForm.controls.orgHierLevel4.reset();
    this.searchPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchPoForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchPoForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchPoForm.controls.orgHierLevel4.reset();
    this.searchPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchPoForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchPoForm.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.searchPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchPoForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchPoForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }


  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  displayActivityFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['activityNumber'] : undefined;
  }
  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.searchPoForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchPoForm.controls.dealerName.patchValue('');
    }
  }


  showAdvanceSearch() {
    this.isShowAdvanceFilter = !this.isShowAdvanceFilter;
  }
  poNumberValueChange() {
    this.searchPoForm.controls.poNumber.valueChanges.subscribe(changedValue => {
      if (changedValue != null) {
        this.poNumberAutoChange.emit(changedValue);
      }
    })
  }
  itemNumberValueChange() {
    this.searchPoForm.controls.itemNo.valueChanges.subscribe(changedValue => {
      if (changedValue != null) {
        this.itemNumberAutoChange.emit(changedValue);
      }
    })
  }
  searchPo() {

    this.poNumberNgModel = "";
    this.lastApprovedByModel = ""
    this.poDateNgModel = "";
    this.poTypeNgModel = "";
    this.depotNgModel = "";
    this.totalQuantityNgModel = "";
    this.poStatusNgModel = "";
    this.totalCreditLimitNgModel = "";
    this.availableLimitNgModel = "";
    this.dealerCodeNgModel = "";
    this.dealerNameNgModel = "";
    // this.dateFormat.persistentFlag=true

    if (this.checkValidDatesInput()) {

      if (this.searchFlag == true && this.searchPoForm.get('poNumber').value || this.searchPoForm.get('poType').value || this.searchPoForm.get('depot').value || this.searchPoForm.get('itemNo').value || this.searchPoForm.get('fromDate').value || this.searchPoForm.get('toDate').value || this.searchPoForm.get('dealerCode').value || this.searchPoForm.get('poStatus').value || this.searchPoForm.get('product').value || this.searchPoForm.get('series').value || this.searchPoForm.get('model').value || this.searchPoForm.get('subModel').value || this.searchPoForm.get('variant').value || this.searchPoForm.get('orgHierLevel1').value || this.searchPoForm.get('orgHierLevel2').value || this.searchPoForm.get('orgHierLevel3').value || this.searchPoForm.get('orgHierLevel4').value || this.searchPoForm.get('orgHierLevel5').value) {
        // this.page = 0;
        // this.size = 10;
        localStorage.setItem(this.key, JSON.stringify(this.searchPoForm.value))
        let searchForm = this.searchPoForm.value as SearchFilterPurchaseDomain;
        console.log(searchForm, 'searchForm')
        searchForm.dealerId = this.searchPoForm.value.dealerCode ? this.searchPoForm.value.dealerCode['id'] : null
        searchForm.hierId = this.searchPoForm.value.orgHierLevel5 ? this.searchPoForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel4 ? this.searchPoForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel3 ? this.searchPoForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel2 ? this.searchPoForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchPoForm.value.orgHierLevel1 ? this.searchPoForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))
        // this.dateFormat.fromDatePersistent = searchForm.fromDate;
        // this.dateFormat.toDatePersistent = searchForm.toDate;
        this.getSearchFormValues.emit(searchForm);
        searchForm.page = this.page;
        searchForm.size = this.size;
      }
      else if (this.searchPoForm.get('fromDate').value == null && this.searchPoForm.get('toDate').value == null) {
        // searchForm.page = this.page;
        // searchForm.size = 0;
        this.toastr.error("Please fill atleast one input field.");
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
    if (this.dataTable != undefined) {
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
    this.searchFlag = true;
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchPoForm.value as SearchFilterPurchaseDomain

    fltEnqObj.fromDate = this.searchPoForm.getRawValue() ? this.searchPoForm.value.fromDate : null
    fltEnqObj.toDate = this.searchPoForm.getRawValue() ? this.searchPoForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'nextfollowUpFromDate', 'tentativePurchaseFromDate'];
    let toDates = ['toDate', 'nextFollowUpToDate', 'tentativePurchaseToDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }

    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }

  }
  onClickClearSearch() {
    this.poNumberNgModel = "";
    this.lastApprovedByModel = ""
    this.poDateNgModel = "";
    this.poTypeNgModel = "";
    this.depotNgModel = "";
    this.totalQuantityNgModel = "";
    this.poStatusNgModel = "";
    this.totalCreditLimitNgModel = "";
    this.availableLimitNgModel = "";
    this.dealerCodeNgModel = "";
    this.dealerNameNgModel = "";
    this.dataTable = null
  }
  clearForm() {
    this.searchPoForm.reset();
    this.onClickClearSearch();
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  tableActionClick(event) {
    this.tableAction.emit(event);
  }
  fromDateChange(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.todaysDate)
        this.maxDate = this.todaysDate;
      else
        this.maxDate = maxDate;
      if (this.searchPoForm.get('toDate').value > this.maxDate)
        this.searchPoForm.get('toDate').patchValue(this.maxDate);
    }
  }
  pageSizeChange(event) {
    this.pageChange.emit(event);
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  initialQueryParams(event) {

    if (event && event['fromDate']) {
      event['fromDate'] = new Date(event['fromDate']);
      this.selectedFromDate = new Date(event['fromDate'])
    }
    if (event && event['toDate']) event['toDate'] = new Date(event['toDate']);
    this.searchPoForm.patchValue(event);
    this.initialQueryParamsEvent.emit(event);
  }
  onUrlChange(event) {
    if (!event) {
      return;
    }

    this.onUrlChangeEvent.emit(event);
  }

  selectProduct(value) {

    this.searchEnquiryV2WebService.getSeriesByProduct(value).subscribe(response => {

      this.seriesList = response.result
      console.log(this.seriesList, 'ressss')
      // this.seriesList.unshift();
      this.modelsList = [];
      this.submodelsList = [];
      this.variantsList = [];
    })
  }

  selectSeries(value) {
    this.searchEnquiryV2WebService.getModelBySeries(value).subscribe(response => {
      this.modelsList = response.result
      this.submodelsList = [];
      this.variantsList = [];
    })
  }

  selectModel(value) {
    this.model = value;
    this.productInterestedV2WebService.getSubModel(value).subscribe(response => {
      this.submodelsList = response.result
      this.variantsList = [];
    })
  }

  selectSubModel(value) {
    this.productInterestedV2WebService.getVariant(this.model, value).subscribe(response => {
      this.variantsList = response
    })

  }

  salesPoExcelReport(event) {
    let formValues = {} as any
    const searchFormValues = this.searchPoForm.getRawValue();
    let data = '';
    if (searchFormValues) {

      if (searchFormValues['depot']) {
        (searchFormValues['depot'].forEach(res => {
          searchFormValues['depot'] = res;
          data = data + searchFormValues['depot'] + ',';
          (searchFormValues['depot']) = data

        }))
      }

    }
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;

    searchFormValues['fromDate'] = searchFormValues['fromDate'] ? this.dateService.getDateIntoYYYYMMDD(searchFormValues['fromDate']) : null;
    searchFormValues['toDate'] = searchFormValues['toDate'] ? this.dateService.getDateIntoYYYYMMDD(searchFormValues['toDate']) : null;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    console.log('formValues', searchFormValues)
    this.downloadExcel(searchFormValues)


  }
  downloadExcel(data) {
    if (this.searchPoForm.get('poNumber').value == null && this.searchPoForm.get('poType').value == null && this.searchPoForm.get('depot').value == null && this.searchPoForm.get('itemNo').value == null && this.searchPoForm.get('fromDate').value == null && this.searchPoForm.get('toDate').value == null && this.searchPoForm.get('dealerCode').value == null && this.searchPoForm.get('poStatus').value == null && this.searchPoForm.get('product').value == null && this.searchPoForm.get('series').value == null && this.searchPoForm.get('model').value == null && this.searchPoForm.get('subModel').value == null && this.searchPoForm.get('variant').value == null && this.searchPoForm.get('orgHierLevel1').value == null && this.searchPoForm.get('orgHierLevel2').value == null && this.searchPoForm.get('orgHierLevel3').value == null && this.searchPoForm.get('orgHierLevel4').value == null && this.searchPoForm.get('orgHierLevel5').value == null) {
      this.toastr.error("Please Select At least One Input Field")
    }
    else {
      this.salesPurchaseOrderSearchContainerServiceService.downloadPOExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    }
  }

}
