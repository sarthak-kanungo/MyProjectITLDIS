import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchEnquiryV2Presenter } from '../../services/search-enquiry-v2-presenter';
import { FormGroup } from '@angular/forms';
import { SearchEnquiryV2WebService } from '../../services/search-enquiry-v2-web.service';
import { SearchEnquiryFilter, EnquiryNoNameThesil, VillageTehsilDistrict, Product, SeriesByProduct, ModelBySeries, Variants, EnquiryStatus, AutoDealerCodeSearch } from '../../domains/search-enquiry-v2';
import { GeneralInfoWebService } from '../../services/general-info-web.service';
import { Observable } from 'rxjs';
import { EnquirySource, SalePerson, AutoCompSubModel, ItemNumber, PopulateByItemNo } from '../../domains/enquiry';
import { DateAdapter, MatDatepickerInput, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ProductInterestedV2WebService } from '../../services/product-interested-v2-web.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { DateService } from '../../../../../root-service/date.service';
import { MarketingActivitySearchService } from '../../../../activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { BaseDto } from 'BaseDto';
import { ToastrService } from 'ngx-toastr';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { BehaviorSubject } from 'rxjs'

@Component({
  selector: 'app-search-enquiry-v2',
  templateUrl: './search-enquiry-v2.component.html',
  styleUrls: ['./search-enquiry-v2.component.scss'],
  providers: [SearchEnquiryV2Presenter, SearchEnquiryV2WebService, ProductInterestedV2WebService, GeneralInfoWebService, MarketingActivitySearchService, EnquiryCommonService,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },]
})
export class SearchEnquiryV2Component implements OnInit {
  clearSearchRow = new BehaviorSubject<string>("");
  searchEnquiry: FormGroup
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  minDate: Date;
  maxDate: Date
  searchValue: ColumnSearch = {} as ColumnSearch
  searchform: boolean
  actionButtons = []
  dataTable: DataTable;
  sources: Array<EnquirySource>
  salePerson: Array<SalePerson>
  enquiryNumber$: BaseDto<Array<EnquiryNoNameThesil>>
  villages: Observable<Array<VillageTehsilDistrict>>
  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  modelBySeries: Array<ModelBySeries> = []
  subModelByModel: Array<AutoCompSubModel> = []
  model: any
  variants: Array<Variants> = []
  enquriesStatus: Array<EnquiryStatus> = []
  dealercodeList: any
  itemNumber$: Observable<Array<ItemNumber>>
  states = []
  enqyuiryTypes: string[] = ['Hot', 'Warm', 'Cold'];
  selectOptions: string[] = [
    'YES', 'NO',
  ];
  loginUserType: string;
  designation: string;
  page: number = 0;
  size: number = 10;
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];
  totalTableRecords: number
  isKai: boolean = true
  public filterData: object
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  showPartial: boolean = false;
  searchCheck: boolean = true;
  searchFlag: boolean = true;

  actionNgModel: any = "";
  enquiryStatusNgModel: any = "";
  enquiryTypeNgModel: any = "";
  enquiryNumberNgModel: any = "";
  enquiryDateNgModel: any = "";
  salesPersonNgModel: any = "";
  appEnquiryFlagNgModel: any = "";
  validationDateNgModel: any = "";
  sourceNgModel: any = "";
  currentFollowUpDateNgModel: any = "";
  nextFollowUpDateNgModel: any = "";
  tentativePurchaseDateNgModel: any = "";
  prospectCodeNgModel: any = "";
  prospectTypeNgModel: any = "";
  companyNameNgModel: any = "";
  firstNameNgModel: any = "";
  middleNameNgModel: any = "";
  lastNameNgModel: any = "";
  fatherNameNgModel: any = "";
  mobileNumberNgModel: any = "";
  telephoneNumberNgModel: any = "";
  whatsAppNumberNgModel: any = "";
  emailIdNgModel: any = "";
  address1NgModel: any = "";
  address2NgModel: any = "";
  address3NgModel: any = "";
  pinCodeNgModel: any = "";
  postOfficeNgModel: any = "";
  tehsilNgModel: any = "";
  districtNgModel: any = "";
  cityNgModel: any = "";
  stateNgModel: any = "";
  countryNgModel: any = "";
  itemNumberNgModel: any = "";
  itemDescriptionNgModel: any = "";
  productNgModel: any = "";
  modelModelNgModel: any = "";
  subModelNgModel: any = "";
  variantNgModel: any = "";
  cashLoanNgModel: any = "";
  financierNgModel: any = "";
  subsidyNgModel: any = "";
  exchangeRequiredNgModel: any = "";
  generationActivityNumberNgModel: any = "";
  conversionActivityNumberNgModel: any = "";
  remarksNgModel: any = "";
  assetValueNgModel: any = "";
  commonNGModelValueNgModel: any = "";
  autoCloseNgModel: any = "";
  dealerNameNgModel:any= "";
  dealerCodeNgModel:any="";

  searchFilterValues: any;
  postData: { fltEnqObj: SearchEnquiryFilter; };
  key = 'enquiry'
  constructor(
    private router: Router,
    private searchEnquiryV2Presenter: SearchEnquiryV2Presenter,
    private searchEnquiryV2WebService: SearchEnquiryV2WebService,
    private loginFormService: LoginFormService,
    private tableDataService: NgswSearchTableService,
    private generalInfoWebService: GeneralInfoWebService,
    private productInterestedV2WebService: ProductInterestedV2WebService,
    private enqRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private toastr: ToastrService,
    private enquiryCommonService: EnquiryCommonService,
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
    this.designation = this.loginFormService.getDesignation();
  }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem('enquiry')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.clickOnTableFields = [{ 'title': 'enquiryNumber', 'isClickable': true }, { 'title': 'action', 'isClickable': true }]
    this.searchEnquiry = this.searchEnquiryV2Presenter.searchEnquiryFormGroup.get('searchEnquiry') as FormGroup

    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchEnquiry != null) {
      this.searchEnquiry.patchValue(this.searchFilterValues)
    }
    // localStorage.removeItem(this.key);
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.loadAllDropDown()
    this.valueChangesForAutoComplate()

    this.dealerCode()

    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    this.statesDropdown(0);


    if ((this.searchEnquiry.get('enquiryNumber').value == null || this.searchEnquiry.get('enquiryNumber').value == '') && (this.searchEnquiry.get('fromDate').value == null || this.searchEnquiry.get('fromDate').value == '') && (this.searchEnquiry.get('toDate').value == null || this.searchEnquiry.get('toDate').value == '') && (this.searchEnquiry.get('enquiryType').value == null || this.searchEnquiry.get('enquiryType').value == '') && (this.searchEnquiry.get('itemNo').value == null || this.searchEnquiry.get('itemNo').value == '') && (this.searchEnquiry.get('salesPerson').value == null || this.searchEnquiry.get('salesPerson').value == '')) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.searchEnquiry.get('fromDate').patchValue(backDate);
      this.searchEnquiry.get('toDate').patchValue(new Date());
      this.onClickSearch()
    }
    else {
      localStorage.getItem(this.key)
      this.onClickSearch()
    }
  }
  // ngAfterViewInit() {
  //   this.onClickSearch();
  //   this.maxDate = this.today
  //   let backDate = new Date();
  //   backDate.setMonth(this.today.getMonth() - 1);
  //   this.minDate = backDate;
  //   this.searchEnquiry.get('fromDate').patchValue(backDate);
  //   this.searchEnquiry.get('toDate').patchValue(new Date());

  // }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchEnquiry.get('toDate').value > this.maxDate)
        this.searchEnquiry.get('toDate').patchValue(this.maxDate);
    }
  }
  getStates(event) {
    // console.log('getStates--', typeof event.option.value);
    if (typeof event.option.value === 'object') {
      this.statesDropdown(event.option.value.villageId)
    } else {
      this.statesDropdown(0);
    }
  }
   
  private dealerCode(){
    this.searchEnquiry.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        // if (typeof res === 'string') {
        //   this.searchEnquiry.controls.dealerName.patchValue('');
        // }
        this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response 
        })
      }
    })
  }
  statesDropdown(id) {
    // console.log('id : ' + id)
    this.searchEnquiryV2WebService.getStates(id).subscribe(response => {
      this.states = response.result
    });
    this.searchEnquiry.get('state').setErrors({
      enquaryError: 'Select Village First'
    })
  }
  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.searchEnquiry.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchEnquiry.controls.dealerName.patchValue('');
    }
  }
  displayVillageFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['villageName'] : undefined;
  }
  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  getOrgLevelByHODept() {
    this.marketingActivitySearchService.getOrgLevelByHODept('SA').subscribe(response => {
      this.orgLevels = response['result'];
      /*
      this.orgLevels.forEach( level => {
          this.activityProposalList.addControl(level['LEVEL_CODE'], new FormControl() )
      })
      */
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
      this.searchEnquiry.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchEnquiry.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchEnquiry.controls.orgHierLevel3.reset();
    this.searchEnquiry.controls.orgHierLevel4.reset();
    this.searchEnquiry.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchEnquiry.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchEnquiry.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchEnquiry.controls.orgHierLevel4.reset();
    this.searchEnquiry.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchEnquiry.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchEnquiry.controls.orgHierLevel3.patchValue(undefined);
    }
    this.orgHierLevels5 = [];
    this.searchEnquiry.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchEnquiry.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchEnquiry.controls.orgHierLevel4.patchValue(undefined);
    }
  }
  getOrgLevelHier6(hier) {
    if (typeof hier === 'string') {
      this.searchEnquiry.controls.orgHierLevel5.patchValue(undefined);
    }
  }
  loadAllDropDown() {
    this.generalInfoWebService.getSource().subscribe(response => {
      this.sources = response.result as Array<EnquirySource>
    })
    this.generalInfoWebService.getSalePerson().subscribe(response => {
      this.salePerson = response.result
    })
    this.searchEnquiryV2WebService.getAllProduct().subscribe(response => {
      this.products = response.result
      // console.log("all Products---->", response);
    })
    // this.searchEnquiryV2WebService.getAllVariant().subscribe(response => {
    //   this.variants = response.result
    // })
    this.searchEnquiryV2WebService.dropDownEnquiryStatus().subscribe(response => {
      this.enquriesStatus = response.result
    })
  }

  valueChangesForAutoComplate() {
    this.searchEnquiry.get('enquiryNumber').valueChanges.subscribe(value => {
      if (value) {
        let enquiryNumber = typeof value == 'object' ? value.enquiryNumber : value
        // console.log("value is: ", value)
        // console.log("value is of type: ", typeof value)
        this.autoEnquiryNo(enquiryNumber)
      }
    })
    this.searchEnquiry.get('itemNo').valueChanges.subscribe(value => {
      if (value) {
        // console.log("itemNo---->", value)
        let itemNo = typeof value == 'object' ? value.itemNo : value
        this.autoItemNo(itemNo)
      }
    })
    this.searchEnquiry.get('village').valueChanges.subscribe(value => {
      if(value){
        if (this.searchEnquiry.get('state').value) {
          this.autoVillage(value, this.searchEnquiry.get('state').value['stateId']);
        } else {
          this.autoVillage(value, 0);
        }
     
      }
    })
      // console.log("value villages1...", value)
      
  }

  autoVillage(value, stateId: number) {
    this.villages = this.searchEnquiryV2WebService.getVillageTehsilDistrict(value, stateId);
  }

  autoItemNo(itemNo) {
    this.itemNumber$ = this.productInterestedV2WebService.getItemNumberModelProductSeries(itemNo)
    // console.log("this.itemNumber$---->", this.itemNumber$)
  }

  autoEnquiryNo(enquiryNumber) {
    //this.enquiryNumber$ = this.searchEnquiryV2WebService.getEnquiryNumberNameMobileNoTehsil(enquiryNumber)
    this.enquiryCommonService.searchEnquiryCode(enquiryNumber, 'ENQUIRY_SEARCH').subscribe(response => {
      this.enquiryNumber$ = response as BaseDto<Array<EnquiryNoNameThesil>>
    })
  }

  selectProduct(value) {
    // console.log(value);
    this.searchEnquiryV2WebService.getSeriesByProduct(value).subscribe(response => {
      // console.log(response);
      this.seriesByProduct = response.result
    })
  }

  selectSeries(value) {
    this.searchEnquiryV2WebService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response.result
    })
  }

  selectModel(value) {
    // console.log(value);
    this.model = value;
    this.productInterestedV2WebService.getSubModel(value).subscribe(response => {
      this.subModelByModel = response.result
    })
  }

  selectSubModel(value) {
    // console.log(value);
    this.productInterestedV2WebService.getVariant(this.model, value).subscribe(response => {
      this.variants = response
    })

  }



  onKeyDownItemNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.searchEnquiryV2Presenter.resetForItemNo()
      this.searchEnquiryV2Presenter.enableForItemNo()
    }
  }

  selectItemNumber(event) {
    this.productInterestedV2WebService.getByItemNo(event.option.value).subscribe(response => {
      // console.log(response);
      this.patchProduct(response)
      this.patchSeries(response)
      this.patchModel(response)
      this.patchSubModel(response)
      this.patchVariant(response)
      this.searchEnquiryV2Presenter.disabledForItemNo()
    })
  }

  patchProduct(response: PopulateByItemNo) {
    this.products.findIndex(res => res.product === response.product)
    this.searchEnquiry.get('product').patchValue(response.product)
  }

  patchSeries(response: PopulateByItemNo) {
    this.searchEnquiry.get('series').patchValue(response.series)
    this.searchEnquiryV2WebService.getSeriesByProduct(response.product).subscribe(response => {
      this.seriesByProduct = response.result
    })

  }

  patchModel(response: PopulateByItemNo) {
    this.searchEnquiry.get('model').patchValue(response.model)
    this.searchEnquiryV2WebService.getModelBySeries(response.series).subscribe(response => {
      this.modelBySeries = response.result
    })

  }
  //VSCodeUserSetup-x64-1.50.1
  patchSubModel(response: PopulateByItemNo) {
    this.searchEnquiry.get('subModel').patchValue(response.subModel)
    this.productInterestedV2WebService.getSubModel(response.model).subscribe(response => {
      this.subModelByModel = response.result
    })

  }

  patchVariant(response: PopulateByItemNo) {
    this.variants.findIndex(res => res.variant === response.variant)
    this.searchEnquiry.get('variant').patchValue(response.variant)
  }
  isAllowTransferEnquiry: boolean = false;
  onClickSearch() {
    this.resetAllSearchValue();
    if (this.checkValidDatesInput()) {
      if (this.searchFlag == true && this.searchEnquiry.get('enquiryNumber').value || this.searchEnquiry.get('fromDate').value || this.searchEnquiry.get('toDate').value || this.searchEnquiry.get('enquiryType').value || this.searchEnquiry.get('itemNo').value || this.searchEnquiry.get('salesPerson').value ||this.searchEnquiry.get('dealerCode').value ||this.searchEnquiry.get('source').value ||this.searchEnquiry.get('enquiryStatus').value||this.searchEnquiry.get('product').value||this.searchEnquiry.get('series').value||this.searchEnquiry.get('model').value||this.searchEnquiry.get('subModel').value||this.searchEnquiry.get('variant').value||this.searchEnquiry.get('finance').value||this.searchEnquiry.get('autoClose').value||this.searchEnquiry.get('subsidy').value||this.searchEnquiry.get('exchange').value||this.searchEnquiry.get('nextfollowUpFromDate').value||this.searchEnquiry.get('nextFollowUpToDate').value||this.searchEnquiry.get('tentativePurchaseFromDate').value||this.searchEnquiry.get('tentativePurchaseToDate').value||this.searchEnquiry.get('state').value||this.searchEnquiry.get('village').value||this.searchEnquiry.get('orgHierLevel1').value) {
        localStorage.setItem(this.key, JSON.stringify(this.searchEnquiry.value))
        this.searchEnquiryV2WebService
          .getEnquirySearch(this.setEnquirySearchResult()).subscribe(data => {
            // console.log("search data ---->", data)
            this.isAllowTransferEnquiry = data.isAllowTransferEnquiry;
            data.result.map((res => {
              if (res.validationDate) {
                if (res.enquiryStatus === 'DROP' || res.enquiryStatus === 'LOST') {
                  res.action = 'Reopen';
                } else if (res.enquiryStatus != 'RETAILED') {
                  res.action = 'Followup';
                } else {
                  res.action = '';
                }
                return res;
              }
              res.action = 'Validate';
              return res;
            }))
            this.dataTable = this.tableDataService.convertIntoDataTable(data.result)

            this.totalTableRecords = data.count;

            // console.log("Changed dataTable ---->", this.dataTable)
          })
      }
      else if (this.searchEnquiry.get('fromDate').value == null && this.searchEnquiry.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
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

  setEnquirySearchResult() {
    let loginUserId = this.loginFormService.getLoginUserId()
    const fltEnqObj = this.searchEnquiry.value as SearchEnquiryFilter
    let key = 'searchFilter';

    fltEnqObj.userId = loginUserId
    if (this.searchFlag == true && this.searchEnquiry.get('enquiryNumber').value || this.searchEnquiry.get('fromDate').value || this.searchEnquiry.get('toDate').value || this.searchEnquiry.get('enquiryType').value || this.searchEnquiry.get('itemNo').value || this.searchEnquiry.get('salesPerson').value) {
      // this.page = 0;
      // this.size = 10;
      localStorage.setItem(this.key, JSON.stringify(this.searchEnquiry.value))
      fltEnqObj.page = this.page
      fltEnqObj.size = this.size
      fltEnqObj[key] === null && delete fltEnqObj[key];

    }
    else {
      fltEnqObj.page = this.page
      fltEnqObj.size = 0
      // this.toastr.error("Please fill atleast one input field.");
    }
    fltEnqObj.enquiryNumber = this.searchEnquiry.value ? this.searchEnquiry.value.enquiryNumber : null
    fltEnqObj.salesPerson = this.searchEnquiry.value ? this.searchEnquiry.value.salesPerson : null
    fltEnqObj.source = this.searchEnquiry.value ? this.searchEnquiry.value.source : null
    fltEnqObj.product = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().product : null
    fltEnqObj.series = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().series : null
    fltEnqObj.model = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().model : null
    fltEnqObj.subModel = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().subModel : null
    fltEnqObj.variant = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().variant : null
    fltEnqObj.itemNo = this.searchEnquiry.value ? this.searchEnquiry.value.itemNo : null
    fltEnqObj.state = this.searchEnquiry.value.state ? this.searchEnquiry.value.state['stateName'] : null
    fltEnqObj.village = this.searchEnquiry.value.village ? this.searchEnquiry.value.village.villageName : null
    fltEnqObj.hierId = this.searchEnquiry.value.orgHierLevel5 ? this.searchEnquiry.value.orgHierLevel5['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel4 ? this.searchEnquiry.value.orgHierLevel4['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel3 ? this.searchEnquiry.value.orgHierLevel3['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel2 ? this.searchEnquiry.value.orgHierLevel2['org_hierarchy_id'] : this.searchEnquiry.value.orgHierLevel1 ? this.searchEnquiry.value.orgHierLevel1['org_hierarchy_id'] : 0)))
    fltEnqObj.showPartial = this.showPartial;
    fltEnqObj.fromDate = this.searchEnquiry.getRawValue() ? this.searchEnquiry.value.fromDate : null
    fltEnqObj.toDate = this.searchEnquiry.getRawValue() ? this.searchEnquiry.value.toDate : null

    fltEnqObj.finance = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().finance : null
    fltEnqObj.autoClose = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().autoClose : null
    fltEnqObj.subSidy = this.searchEnquiry.getRawValue() ? this.searchEnquiry.getRawValue().subsidy : null
    fltEnqObj.dealerId = this.searchEnquiry.get('dealerCode').value ? this.searchEnquiry.get('dealerCode').value.id : null

    this.filterData = this.removeNullFromObjectAndFormatDate(fltEnqObj);
    // console.log("search Values--->", fltEnqObj)

    return fltEnqObj;

  }

  checkValidDatesInput() {
    const fltEnqObj = this.searchEnquiry.value as SearchEnquiryFilter

    fltEnqObj.fromDate = this.searchEnquiry.getRawValue() ? this.searchEnquiry.value.fromDate : null
    fltEnqObj.toDate = this.searchEnquiry.getRawValue() ? this.searchEnquiry.value.toDate : null

    // console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
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

  initialQueryParams(event) {
    // console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchEnquiry.patchValue(event);
    if (event.enquiryNumber) {
      this.searchEnquiry.get('enquiryNumber').patchValue(event.enquiryNumber.replace(searchValue, newValue));
      event.enquiryNumber = event.enquiryNumber.replace(searchValue, newValue)
    }
    this.searchEnquiryV2WebService.getSeriesByProduct(event.product).subscribe(response => {
      this.seriesByProduct = response.result
    })
    this.searchEnquiryV2WebService.getModelBySeries(event.series).subscribe(response => {
      this.modelBySeries = response.result
    })
    this.productInterestedV2WebService.getSubModel(event.model).subscribe(response => {
      this.subModelByModel = response.result
    })
    // console.log("this.searchEnquiry ", this.searchEnquiry);

  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {

    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate' || element === 'nextfollowUpFromDate' || element === 'nextFollowUpToDate' || element === 'tentativePurchaseFromDate' || element === 'tentativePurchaseToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      // console.log("searchObject si ----> ", searchObject);
      return searchObject;
    }
  }
  onClickClearSearch() {
    this.resetAllSearchValue();
     this.dealercodeList=null
    this.dataTable = null
    this.totalTableRecords = null

  }


  clearEnquirySearch() {
    this.searchEnquiryV2Presenter.resetAll()
    this.searchEnquiryV2Presenter.enableForItemNo()
    this.seriesByProduct = []
    this.modelBySeries = []
    this.subModelByModel = []
    this.variants = []
    this.clearSearchRow.next("");
    this.onClickClearSearch()
    localStorage.removeItem(this.key)
    this.dealercodeList=null
  }

  enqyuiryDateChanges(searchDate, columnKey) {
    this.searchValue = new ColumnSearch(searchDate, columnKey);

  }
  actionOnTableRecord(recordData) {
    // console.log("recordData ", recordData);
    if (recordData.btnAction.toLowerCase() === 'enquirynumber') {
      if (!recordData.record.appEnquiryFlag || recordData.record.action.toLowerCase() !== 'validate') {
        this.router.navigate(['view', btoa(recordData.record.enquiryNumber)], { relativeTo: this.enqRt })
      } else {
        this.router.navigate(['viewMobile', btoa(recordData.record.enquiryNumber)], { relativeTo: this.enqRt })
      }
    }

    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action.toLowerCase() === 'validate') {
        this.router.navigate(['viewMobile', btoa(recordData.record.enquiryNumber)], { relativeTo: this.enqRt })
      } else if (recordData.record.enquiryStatus === 'LOST' || recordData.record.enquiryStatus === 'DROP') {
        this.searchEnquiryV2WebService.reopenEnquiry(recordData.record.enquiryNumber).subscribe(response => {
          if (response.result.toString() === 'Success') {
            this.toastr.success(`Enquiry(${recordData.record.enquiryNumber}) Re-Opened Successfully`, 'Success')
            this.clearEnquirySearch();
          } else {
            this.toastr.error("Enquiry not reopened");
          }
        });
      } else {
        this.router.navigate(['followup', recordData.record.enquiryNumber], { relativeTo: this.enqRt })
      }
    }
  }

  onUrlChange(event) {
    // console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }

  pageChange(event) {
    // console.log("page changes---->", event)
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearch()
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

  columnLengthChanges(event) {
    if (event === 'All') {
      this.showPartial = false;
    } else {
      this.showPartial = true;
    }
    this.onClickSearch();
  }

  downloadExcel(event) {
    const fltEnqObj = this.setEnquirySearchResult()
    this.downloadExcelReport(fltEnqObj);
  }
  private downloadExcelReport(searchObject) {
    if(this.searchEnquiry.get('fromDate').value == null || this.searchEnquiry.get('toDate').value == null && this.searchEnquiry.get('enquiryNumber').value == null && this.searchEnquiry.get('enquiryType').value == null && this.searchEnquiry.get('salesPerson').value == null&& this.searchEnquiry.get('itemNo').value == null&& this.searchEnquiry.get('dealerCode').value == null&& this.searchEnquiry.get('source').value == null&& this.searchEnquiry.get('enquiryStatus').value == null&& this.searchEnquiry.get('product').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null&& this.searchEnquiry.get('enquiryNumber').value == null){
      this.toastr.error("Please Select Atleast One Input Field");
    }
   
    else{
    this.searchEnquiryV2WebService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  }
  resetAllSearchValue() {
    this.actionNgModel = "";
    this.enquiryStatusNgModel = "";
    this.enquiryTypeNgModel = "";
    this.enquiryNumberNgModel = "";
    this.enquiryDateNgModel = "";
    this.salesPersonNgModel = "";
    this.appEnquiryFlagNgModel = "";
    this.validationDateNgModel = "";
    this.sourceNgModel = "";
    this.currentFollowUpDateNgModel = "";
    this.nextFollowUpDateNgModel = "";
    this.tentativePurchaseDateNgModel = "";
    this.prospectCodeNgModel = "";
    this.prospectTypeNgModel = "";
    this.companyNameNgModel = "";
    this.firstNameNgModel = "";
    this.middleNameNgModel = "";
    this.lastNameNgModel = "";
    this.fatherNameNgModel = "";
    this.mobileNumberNgModel = "";
    this.telephoneNumberNgModel = "";
    this.whatsAppNumberNgModel = "";
    this.emailIdNgModel = "";
    this.address1NgModel = "";
    this.address2NgModel = "";
    this.address3NgModel = "";
    this.pinCodeNgModel = "";
    this.postOfficeNgModel = "";
    this.tehsilNgModel = "";
    this.districtNgModel = "";
    this.cityNgModel = "";
    this.stateNgModel = "";
    this.countryNgModel = "";
    this.itemNumberNgModel = "";
    this.itemDescriptionNgModel = "";
    this.productNgModel = "";
    this.modelModelNgModel = "";
    this.subModelNgModel = "";
    this.variantNgModel = "";
    this.cashLoanNgModel = "";
    this.financierNgModel = "";
    this.subsidyNgModel = "";
    this.exchangeRequiredNgModel = "";
    this.generationActivityNumberNgModel = "";
    this.conversionActivityNumberNgModel = "";
    this.remarksNgModel = "";
    this.assetValueNgModel = "";
    this.commonNGModelValueNgModel = "";
  }
}
