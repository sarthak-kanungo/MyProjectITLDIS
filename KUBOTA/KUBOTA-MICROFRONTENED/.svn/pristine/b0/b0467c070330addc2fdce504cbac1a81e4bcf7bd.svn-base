import { BehaviorSubject, Observable, of } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { Component, OnInit, AfterViewInit } from '@angular/core';

import { LoginFormService } from '../../../../../root-service/login-form.service';
import { NgswSearchTableService, DataTable, ColumnSearch } from 'ngsw-search-table';
import { SearchDeliveryChallanService } from './search-delivery-challan.service';
import { AutoDealerCode, SearchDcModal } from 'search-delivery-challan';
import { Router, ActivatedRoute } from '@angular/router';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { DateService } from '../../../../../root-service/date.service';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { MarketingActivitySearchService } from 'src/app/sales-pre-sales/activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { BaseDto } from 'BaseDto';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-delivery-challan',
  templateUrl: './search-delivery-challan.component.html',
  styleUrls: ['./search-delivery-challan.component.scss'],
  providers: [SearchDeliveryChallanService, EnquiryCommonService, MarketingActivitySearchService]
})
export class SearchDeliveryChallanComponent implements OnInit, AfterViewInit {
  public page = 0;
  public size = 10;
  public actionButtons = [];
  public dealerType: string;
  public dataTable: DataTable;
  public minToDate = new Date();
  public maxToDate = new Date();
  public todaysDate = new Date();
  public totalTableElements: number;
  public searchValue: ColumnSearch = {} as ColumnSearch

  public dcNoAutoList: Observable<(string | object)[]>;
  public chassisNoAutoList: Observable<(string | object)[]>;
  public customerNameAutoList: Observable<(string | object)[]>;
  public mobileNoAutoList: Observable<(string | object)[]>;
  public enquiryNoAutoList: Observable<(string | object)[]>;
  public itemNoAutoList: Observable<(string | object)[]>;
  public engineNoAutoList: Observable<(string | object)[]>;
  clearSearchRow = new BehaviorSubject<string>("");
  public enquiryTypeList = [];
  public dcStatusList = [];
  public productList = [];
  public seriesList = [];
  public modelList = [];
  public subModelList = [];
  public variantList = [];
  public searchform: boolean;
  public searchFilter: any;
  searchFlag: boolean = true;
  public searchDeliveryChallanForm: FormGroup;

  deliveryChallanNumberNgModel: any = "";
  deliveryChallanDateNgModel: any = "";
  deliveryChallantypeNgModel: any = "";
  enquiryNumberNgModel: any = "";
  enquiryDateNgModel: any = "";
  enquiryTypeNgModel: any = "";
  dcStatusNgModel: any = "";
  getPassNumberNgModel: any = "";
  gatePassDateNgModel: any = "";
  remarkNgModel: any = "";
  itemNumberNgModel: any = "";
  itemDescriptionNgModel: any = "";
  quantityNgModel: any = "";
  chassisNumberNgModel: any = "";
  engineNumberNgModel: any = "";
  colorNgModel: any = "";
  customerTypeNgModel: any = "";
  companyNameNgModel: any = "";
  customerCodeNgModel: any = "";
  customerNameNgModel: any = "";
  invoiceCustomerNameNgModel: any = "";
  mobileNumberNgModel: any = "";
  address1NgModel: any = "";
  address2NgModel: any = "";
  address3NgModel: any = "";
  pinCodeNgModel: any = "";
  postOfficeNgModel: any = "";
  villageNgModel: any = "";
  tehsilNgModel: any = "";
  districtNgModel: any = "";
  cityNgModel: any = "";
  stateNgModel: any = "";
  gstinNumberNgModel: any = "";
  panNumberNgModel: any = "";
  insuranceCompanyCodeNgModel: any = "";
  insuranceCompanyNameNgModel: any = "";
  policyStartDateNgModel: any = "";
  policyExipryDateNgModel: any = "";
  requestNumberNgModel: any = "";
  dealerNameNgModel: any = "";
  dealerCodeNgModel: any = "";
  createdDateNgModel: any = "";
  itelephoneNumberNgModel: any = "";
  iaddress2NgModel: any = "";
  iemailNgModel: any = "";
  icityNgModel: any = "";
  iaddress1NgModel: any = "";
  istateNgModel: any = "";
  ilocalityNgModel: any = "";
  iTehsilNgModel: any = "";
  iaddress3NgModel: any = "";
  ipinCodeNgModel: any = "";
  icountryNgModel: any = "";
  financierNgModel:any =""
  kaiInvoiceNumberNgModel:any =""
  kaiInvoiceDateNgModel:any =""
  dseCodeNgModel:any =""
  dseNameNgModel:any =""
  cashLoneNgModel:any =""
  dealerStateNgModel:any =""
  TransferToDealerNameNgModel:any=''
  TransferToDealerCodeNgModel:any 

  searchFilterValues: any;

  loginUserType: string;
  isKai: boolean = true;
  public dealerList: Observable<(string | object)[]>;
  public filterData: object
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  dealercodeList: BaseDto<Array<AutoDealerCode>>
  key='sdc';

  constructor(
    private router: Router,
    private dateService: DateService,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private loginFormService: LoginFormService,
    private autocompleteService: AutocompleteService,
    private tableDataService: NgswSearchTableService,
    private searchDeliveryChallanService: SearchDeliveryChallanService,
    private enquiryCommonService: EnquiryCommonService,
    private loginService: LoginFormService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private toastr: ToastrService,
  ) {
    this.dealerType = this.loginFormService.getLoginUserType().toLowerCase();
    // this.dateService.getSystemGeneratedDate().subscribe(res => {
    //   this.minToDate = new Date(res.result);
    //   this.todaysDate = new Date(res.result);
    // })
  }

  ngOnInit() {
    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchDeliveryChallanForm = this.searchDeliveryChallanService.createSearchDeliveryChallanForm();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchDeliveryChallanForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    this.customerNameValueChanges();
    this.chassisNoValueChanges();
    this.enquiryNoValueChanges();
    this.mobileNoValueChanges();
    this.engineNoValueChanges();
    this.itemNoValueChanges();
    this.dcNoValueChanges();

    this.getAllEnquiryType();
    this.getAllSubModels();
    this.getAllDcStatus();
    this.getAllProducts();
    this.getAllVariants();
    this.getAllModels();
    this.getAllSeries();
    this.getDealerCode()

    // this.searchDeliveryChallan();
    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }

    // this.searchDeliveryChallanForm.reset()
    if (this.searchDeliveryChallanForm.get('deliveryChallanNumber').value==null && this.searchDeliveryChallanForm.get('chassisNumber').value==null && this.searchDeliveryChallanForm.get('customerName').value==null && this.searchDeliveryChallanForm.get('customerMobileNumber').value==null && this.searchDeliveryChallanForm.get('dcFromDate').value==null && this.searchDeliveryChallanForm.get('dcToDate').value==null) {
    this.searchDeliveryChallanForm.get('dcToDate').patchValue(this.todaysDate);
    let backDate = new Date();
    backDate.setMonth(this.todaysDate.getMonth() - 1);
    this.minToDate = backDate;
    this.searchDeliveryChallanForm.get('dcFromDate').patchValue(backDate);
    // this.searchDeliveryChallan();
    }else{
      localStorage.getItem(this.key)
      // this.searchDeliveryChallan();
    }
  }
  ngAfterViewInit() {

   
  }
  public resetFormControlNotHavingObject = this.autocompleteService.resetFormControlNotHavingObject;

  public searchDeliveryChallan() {

    this.resetTable()

    let searchObj = this.searchDeliveryChallanForm.value as SearchDcModal;
    // searchObj = this.searchDeliveryChallanForm.value;
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchDeliveryChallanForm.value))


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
    searchObj['page'] = this.page;
    searchObj['size'] = this.size;
    searchObj.hierId = this.searchDeliveryChallanForm.value.orgHierLevel5 ? this.searchDeliveryChallanForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel4 ? this.searchDeliveryChallanForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel3 ? this.searchDeliveryChallanForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel2 ? this.searchDeliveryChallanForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchDeliveryChallanForm.value.orgHierLevel1 ? this.searchDeliveryChallanForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))
    // searchObj.dealerId = this.searchDeliveryChallanForm.get('dealer').value ? this.searchDeliveryChallanForm.get('dealer').value.id : null
    // this.searchFlag = true;

    this.convertToSearchObject(searchObj)

    if (searchObj['dcFromDate'] && searchObj['dcToDate']) {
      searchObj['dcFromDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['dcFromDate']);
      searchObj['dcToDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['dcToDate'])
    }
    // console.log('searchObj:--2', searchObj)
    ObjectUtil.removeNulls(searchObj);
    // console.log('searchObj: ', searchObj);

    this.searchFilter = searchObj;
    if ((searchObj['dcFromDate'] === null || searchObj['dcFromDate'] === "" || searchObj['dcFromDate'] === undefined)) {
      if ((searchObj['dcToDate'] === null || searchObj['dcToDate'] === "" || searchObj['dcToDate'] === undefined)) {
        // console.log('searchObj:-- 3', this.searchDeliveryChallanForm.get('dcFromDate').value);
        if (this.searchDeliveryChallanForm.get('deliveryChallanNumber').value || this.searchDeliveryChallanForm.get('chassisNumber').value || this.searchDeliveryChallanForm.get('customerName').value || this.searchDeliveryChallanForm.get('customerMobileNumber').value || this.searchDeliveryChallanForm.get('dcFromDate').value || this.searchDeliveryChallanForm.get('dcToDate').value ||this.searchDeliveryChallanForm.get('enquiryNumber').value||this.searchDeliveryChallanForm.get('enquiryType').value||this.searchDeliveryChallanForm.get('dcStatus').value||this.searchDeliveryChallanForm.get('product').value||this.searchDeliveryChallanForm.get('series').value||this.searchDeliveryChallanForm.get('model').value||this.searchDeliveryChallanForm.get('subModel').value||this.searchDeliveryChallanForm.get('variant').value||this.searchDeliveryChallanForm.get('itemNumber').value||this.searchDeliveryChallanForm.get('engineNumber').value) {
          this.searchDeliveryChallanService.searchDeliveryChallan(searchObj).subscribe(res => {
            if (res) {
              this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
              this.totalTableElements = res['count'];
            }
          })
        }
        else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    }

    else if ((searchObj['dcFromDate'] !== null || searchObj['dcFromDate'] !== "" || searchObj['dcFromDate'] !== undefined)) {
      if ((searchObj['dcToDate'] === null || searchObj['dcToDate'] === "" || searchObj['dcToDate'] === undefined)) {
        this.toastr.error("Please Select Date Range");
      } else {
        this.searchDeliveryChallanService.searchDeliveryChallan(searchObj).subscribe(res => {
          if (res) {
            this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
            this.totalTableElements = res['count'];
          }
        })
      }
    }
  }
  private convertToSearchObject(searchObj: SearchDcModal) {
    if (searchObj) {
      Object.keys(searchObj).forEach(key => {
        ((searchObj[key] === null) || (searchObj[key] === '')) ? delete searchObj[key] : searchObj[key];
      });
      typeof searchObj.chassisNumber === 'object' && searchObj.chassisNumber !== null ? searchObj.chassisNumber = searchObj.chassisNumber['chassisNo'] : searchObj.chassisNumber;
      typeof searchObj.customerMobileNumber === 'object' && searchObj.customerMobileNumber !== null ? searchObj.customerMobileNumber = searchObj.customerMobileNumber['customerMobileNumber'] : searchObj.customerMobileNumber;
      typeof searchObj.customerName === 'object' && searchObj.customerName !== null ? searchObj.customerName = searchObj.customerName['customerName'] : searchObj.customerName;
      typeof searchObj.deliveryChallanNumber === 'object' && searchObj.deliveryChallanNumber !== null ? searchObj.deliveryChallanNumber = searchObj.deliveryChallanNumber['deliveryChallanNumber'] : searchObj.deliveryChallanNumber;
      typeof searchObj.engineNumber === 'object' && searchObj.engineNumber !== null ? searchObj.engineNumber = searchObj.engineNumber['engineNumber'] : searchObj.engineNumber;
      typeof searchObj.enquiryNumber === 'object' && searchObj.enquiryNumber !== null ? searchObj.enquiryNumber = searchObj.enquiryNumber['enquiryNumber'] : searchObj.enquiryNumber;
      typeof searchObj.itemNumber === 'object' && searchObj.itemNumber !== null ? searchObj.itemNumber = searchObj.itemNumber['itemNumber'] : searchObj.itemNumber;
    }
  }
  resetTable() {
    this.deliveryChallanNumberNgModel = "";
    this.deliveryChallanDateNgModel = "";
    this.deliveryChallantypeNgModel = "";
    this.enquiryNumberNgModel = "";
    this.enquiryDateNgModel = "";
    this.enquiryTypeNgModel = "";
    this.dcStatusNgModel = "";
    this.getPassNumberNgModel = "";
    this.gatePassDateNgModel = "";
    this.remarkNgModel = "";
    this.itemNumberNgModel = "";
    this.itemDescriptionNgModel = "";
    this.quantityNgModel = "";
    this.chassisNumberNgModel = "";
    this.engineNumberNgModel = "";
    this.colorNgModel = "";
    this.customerTypeNgModel = "";
    this.companyNameNgModel = "";
    this.customerCodeNgModel = "";
    this.customerNameNgModel = "";
    this.invoiceCustomerNameNgModel = "";
    this.mobileNumberNgModel = "";
    this.address1NgModel = "";
    this.address2NgModel = "";
    this.address3NgModel = "";
    this.pinCodeNgModel = "";
    this.postOfficeNgModel = "";
    this.villageNgModel = "";
    this.tehsilNgModel = "";
    this.districtNgModel = "";
    this.cityNgModel = "";
    this.stateNgModel = "";
    this.gstinNumberNgModel = "";
    this.panNumberNgModel = "";
    this.insuranceCompanyCodeNgModel = "";
    this.insuranceCompanyNameNgModel = "";
    this.policyStartDateNgModel = "";
    this.policyExipryDateNgModel = "";
    this.requestNumberNgModel = "";
    this.dealerNameNgModel = "";
    this.createdDateNgModel = "";
    this.itelephoneNumberNgModel = "";
    this.iaddress2NgModel = "";
    this.iemailNgModel = "";
    this.icityNgModel = "";
    this.iaddress1NgModel = "";
    this.istateNgModel = "";
    this.ilocalityNgModel = "";
    this.iTehsilNgModel = ""
    this.iaddress3NgModel = "";
    this.ipinCodeNgModel = "";
    this.icountryNgModel = "";
    this.financierNgModel =""
    this.kaiInvoiceNumberNgModel =""
    this.kaiInvoiceDateNgModel =""
    this.dseCodeNgModel=""
    this.dseNameNgModel =""
    this.cashLoneNgModel =""
    this.dealerStateNgModel =""
    this.TransferToDealerNameNgModel =''
    this.TransferToDealerCodeNgModel =''
  }
  public pageChange(event) {
    if (!!event && event.page >= 0) {
      this.page = event.page;
      this.size = event.size
      this.searchFlag = false;
      this.searchDeliveryChallan();
    }
  }
  initialQueryParams(event) {
    this.searchDeliveryChallanForm.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchDeliveryChallan();
  }
  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
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
  public actionOnTableRecord(recordData) {
    if (recordData['btnAction'] === 'deliveryChallanNumber') {
      this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData['btnAction'] === 'edit') {
      this.router.navigate(['update', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData['btnAction'] === 'cancel') {
      recordData.record['cancel'] === "Approve" ?
        this.router.navigate(['approve', btoa(recordData.record.id)], { relativeTo: this.activatedRoute }) :
        this.router.navigate(['cancel', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
  }

  private dcNoValueChanges() {

    this.searchDeliveryChallanForm.controls.deliveryChallanNumber.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.searchDeliveryChallanService.searchByDcNo(changedValue).subscribe(res => {
          if (res) {
            this.dcNoAutoList = of(res['result']);
          }
        })
      }
    })

  }
  public displayDcNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['deliveryChallanNumber'] : undefined;
  }
  private chassisNoValueChanges() {

    this.searchDeliveryChallanForm.controls.chassisNumber.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.searchDeliveryChallanService.searchByChassisNo(changedValue).subscribe(res => {
          if (res) {
            this.chassisNoAutoList = of(res['result']);
          }
        })
      }
    })

  }
  public displayChassisNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['chassisNo'] : undefined;
  }
  private customerNameValueChanges() {

    this.searchDeliveryChallanForm.controls.customerName.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.searchDeliveryChallanService.searchByCustomerName(changedValue).subscribe(res => {
          if (res) {
            this.customerNameAutoList = of(res['result']);
          }
        })
      }
    })

  }
  public displayCustomerNameFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['customerName'] : undefined;
  }
  private mobileNoValueChanges() {
    this.searchDeliveryChallanForm.controls.customerMobileNumber.valueChanges.subscribe(changedValue => {
      if (changedValue) {

        this.searchDeliveryChallanService.searchByMobileNo(changedValue).subscribe(res => {
          if (res) {
            this.mobileNoAutoList = of(res['result']);
          }
        })
      }
    })
  }
  public displayMobileNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['customerMobileNumber'] : undefined;
  }
  private enquiryNoValueChanges() {
    // if (this.searchDeliveryChallanForm.value.enquiryNumber != null) {
      this.searchDeliveryChallanForm.controls.enquiryNumber.valueChanges.subscribe(changedValue => {
        if(changedValue){
          this.enquiryCommonService.searchEnquiryCode(changedValue, 'DC_SEARCH').subscribe(response => {
            this.enquiryNoAutoList = of(response['result'])
          })
        }
        /*this.searchDeliveryChallanService.searchByEnquiryNo(changedValue).subscribe(res => {
          if (res) {
            this.enquiryNoAutoList = of(res['result']);
          }
        })*/
        
      })
    // }
  }
  public displayEnquiryNoFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['enquiryNumber'] : undefined;
  }
  private itemNoValueChanges() {
    // if (this.searchDeliveryChallanForm.value.itemNumber != null) {
      this.searchDeliveryChallanForm.controls.itemNumber.valueChanges.subscribe(changedValue => {
        if(changedValue){
          this.searchDeliveryChallanService.searchByItemNo(changedValue).subscribe(res => {
            if (res) {
              this.itemNoAutoList = of(res['result']);
            }
          })
        }
       
      })
    // }
  }
  public displayItemNoFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['itemNo'] : undefined;
  }
  private engineNoValueChanges() {
    // if (this.searchDeliveryChallanForm.value.engineNumber != null) {
      this.searchDeliveryChallanForm.controls.engineNumber.valueChanges.subscribe(changedValue => {
        if(changedValue){
          this.searchDeliveryChallanService.searchByEngineNo(changedValue).subscribe(res => {
            if (res) {
              this.engineNoAutoList = of(res['result']);
            }
          })
        }
        
      })
    // }
  }
  public displayEngineNoFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['engineNumber'] : undefined;
  }
  private getAllEnquiryType() {
    this.searchDeliveryChallanService.getAllEnquiryType().subscribe(res => {
      if (res) {
        this.enquiryTypeList = res['result'];
      }
    })
  }
  private getAllDcStatus() {
    this.searchDeliveryChallanService.getAllDcStatus().subscribe(res => {
      if (res) {
        this.dcStatusList = res['result'];
      }
    })
  }
  private getAllProducts() {
    this.searchDeliveryChallanService.getAllProducts().subscribe(res => {
      if (res) {
        this.productList = res['result'];
      }
    })
  }
  private getAllSeries() {
    this.searchDeliveryChallanService.getAllSeries().subscribe(res => {
      if (res) {
        this.seriesList = res['result'];
        this.seriesList.shift();
      }
    })
  }
  private getAllModels() {
    this.searchDeliveryChallanService.getAllModels().subscribe(res => {
      if (res) {
        this.modelList = res['result'];
        this.modelList.shift()
      }
    })
  }
  private getAllSubModels() {
    this.searchDeliveryChallanService.getAllSubModels().subscribe(res => {
      if (res) {
        this.subModelList = res['result'];
        this.subModelList.shift()
      }
    })
  }
  private getAllVariants() {
    this.searchDeliveryChallanService.getAllVariants().subscribe(res => {
      if (res) {
        this.variantList = res['result'];
        this.variantList.shift()
      }
    })
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
      if (this.searchDeliveryChallanForm.get('dcToDate').value > this.maxToDate)
        this.searchDeliveryChallanForm.get('dcToDate').patchValue(this.maxToDate);
    }
  }
  clearForm() {
    this.searchDeliveryChallanForm.reset();
    // this.searchDeliveryChallan();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }


  public displayDealerFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  downloadExcel(event) {
    const formValues = this.searchDeliveryChallanForm.getRawValue();

    let dcFromDate: string
    if (this.searchDeliveryChallanForm.get('dcFromDate').value) {
      dcFromDate = this.dateService.getDateIntoYYYYMMDD(this.searchDeliveryChallanForm.get('dcFromDate').value);
    }
    let dcToDate: string
    if (this.searchDeliveryChallanForm.get('dcToDate').value) {
      dcToDate = this.dateService.getDateIntoYYYYMMDD(this.searchDeliveryChallanForm.get('dcToDate').value);
    };
    let sendSearchValue = {} as SearchDcModal;
    sendSearchValue.deliveryChallanNumber = formValues.deliveryChallanNumber ? formValues.deliveryChallanNumber.deliveryChallanNumber : null
    sendSearchValue.chassisNumber = formValues.chassisNumber ? formValues.chassisNumber.chassisNo : null
    sendSearchValue.customerName = formValues.customerName ? formValues.customerName.customerName : null
    sendSearchValue.customerMobileNumber = formValues.customerMobileNumber ? formValues.customerMobileNumber.customerMobileNumber : null
    sendSearchValue.page = 0;

    sendSearchValue.dcFromDate = dcFromDate;
    sendSearchValue.dcToDate = dcToDate;

    sendSearchValue.enquiryNumber = formValues.enquiryNumber ? formValues.enquiryNumber.enquiryNumber : null
    sendSearchValue.enquiryType = formValues.enquiryType ? formValues.enquiryType : null
    sendSearchValue.dcStatus = formValues.dcStatus ? formValues.dcStatus : null
    sendSearchValue.product = formValues.product
    sendSearchValue.series = formValues.series
    sendSearchValue.model = formValues.model
    sendSearchValue.subModel = formValues.subModel
    sendSearchValue.varient = formValues.variant
    sendSearchValue.itemNumber = formValues.itemNumber ? formValues.itemNumber.itemNo : null
    sendSearchValue.engineNumber = formValues.engineNumber ? formValues.engineNumber.engineNumber : null
    sendSearchValue.hierId = this.searchDeliveryChallanForm.value.orgHierLevel5 ? this.searchDeliveryChallanForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel4 ? this.searchDeliveryChallanForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel3 ? this.searchDeliveryChallanForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchDeliveryChallanForm.value.orgHierLevel2 ? this.searchDeliveryChallanForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchDeliveryChallanForm.value.orgHierLevel1 ? this.searchDeliveryChallanForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))
    sendSearchValue.dealerId = this.searchDeliveryChallanForm.get('dealer').value ? this.searchDeliveryChallanForm.get('dealer').value.id : null

    this.convertToSearchObject(sendSearchValue);
    this.downloadExcelReport(sendSearchValue);
  }

  private downloadExcelReport(searchObject) {
    this.searchDeliveryChallanService.downloadDcExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('Content-Disposition');
        // console.log('downloadExcelReport-->', headerContentDispostion)
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }

  getDealerCode() {
    // if (this.searchDeliveryChallanForm.value.dealer != null) {
      this.searchDeliveryChallanForm.controls.dealer.valueChanges.subscribe(res => {
        // if (typeof res === 'string') {

        if(res){
          this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
            this.dealercodeList = response as BaseDto<Array<AutoDealerCode>>
          })
        }
        //   this.searchDeliveryChallanForm.controls.dealerName.patchValue('');
        // }
        
      })
    // }
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
      this.searchDeliveryChallanForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchDeliveryChallanForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchDeliveryChallanForm.controls.orgHierLevel3.reset();
    this.searchDeliveryChallanForm.controls.orgHierLevel4.reset();
    this.searchDeliveryChallanForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchDeliveryChallanForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchDeliveryChallanForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchDeliveryChallanForm.controls.orgHierLevel4.reset();
    this.searchDeliveryChallanForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchDeliveryChallanForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchDeliveryChallanForm.controls.orgHierLevel3.patchValue(undefined);
    }
    this.orgHierLevels5 = [];
    this.searchDeliveryChallanForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchDeliveryChallanForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchDeliveryChallanForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }
  getOrgLevelHier6(hier) {
    if (typeof hier === 'string') {
      this.searchDeliveryChallanForm.controls.orgHierLevel5.patchValue(undefined);
    }
  }



}
