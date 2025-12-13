import { Component, Input, OnInit, TemplateRef, ViewChild, ViewChildren } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { ReceiptModeSearchDomain, ProductDomain, SeriesDomain, SubModelDomain, VariantDomain, SearchFilterPaymentReceiptListDomain, ReceiptNumberDomain } from 'search-payment-receipt-dto';
import { ModelDomain } from 'SearchQuotationsModule';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { ToastrService } from 'ngx-toastr';
import { debug } from 'console';
import { SearchExchangeInventoryService } from './search-exchange-inventory.service';
import { SearchFilterExchangeInventoryListDomain } from 'search-exchange-inventory-dto';
import { SelectList } from 'src/app/core/model/select-list.model';
import { DateService } from 'src/app/root-service/date.service';
import { EnquiryNoNameThesil } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/search-enquiry-v2';
import { IFrameMessageSource, IFrameService, UrlSegment } from 'src/app/root-service/iFrame.service';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDatepickerInput, MatDialog } from '@angular/material';
import { async } from '@angular/core/testing';
import { SalePopUpComponent } from '../../sale-pop-up/sale-pop-up.component';
import { BehaviorSubject } from 'rxjs';
@Component({
  selector: 'app-search-exchange-inventory',
  templateUrl: './search-exchange-inventory.component.html',
  styleUrls: ['./search-exchange-inventory.component.scss'],
  providers: [NgswSearchTableService]
})
export class SearchExchangeInventoryComponent implements OnInit {
  // @ViewChildren('saleCreateDialog') saleCreateDialog: TemplateRef<any>;
  enquiryNumber$: BaseDto<Array<EnquiryNoNameThesil>>
  searchValue: ColumnSearch = {} as ColumnSearch
  searchExchangeInventoryForm: FormGroup;
  saleExchangeInventoryForm: FormGroup;
  productTypeList = [] as SelectList[];
  public todaysDate = new Date();
  totalTableElements: number;
  public dealerType: string;
  customerMobileList = [];
  clearSearchRow = new BehaviorSubject<string>("");
  customerNameList = [];
  dataTable: DataTable;
  itemNumberList = [];
  showAdvance = false;
  actionButtons = [];
  size = 10;
  page = 0;
  hiddenField: boolean = true
  searchFlag: boolean = true;
  public filterData: object
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];
  totalTableRecords: number;
  oldVehId: any;
  soldStatusList: any[] = [
    {
      id: 1,
      value: 'Available'
    },
    {
      id: 2,
      value: 'Sold'
    }
  ];
  actionNgModel: any = "";
  enquiryNumberNgModel: any = "";
  soldStatusNgModel: any = "";
  brandNameNgModel: any = "";
  modelNameNgModel: any = "";
  modelYearNgModel: any = "";
  invInDateNgModel: any = "";
  estimatedExchangePriceNgModel: any = "";
  buyerNameNgModel: any = "";
  buyerContactNoNgModel: any = "";
  saleDateNgModel: any = "";
  sellingPriceNgModel: any = "";
  saleRemarksNgModel: any = "";
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key='sei';
  searchFilterValues: any;

  constructor(private router: Router,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private loginFormService: LoginFormService,
    private tableDataService: NgswSearchTableService,
    private searchExchangeInventoryService: SearchExchangeInventoryService,
    private toastr: ToastrService, private dateService: DateService,
    private iFrameService: IFrameService,
    public dialog: MatDialog,

  ) {
    this.dealerType = this.loginFormService.getLoginUserType().toLowerCase();
  }


  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    
    localStorage.getItem(this.key)
    this.createSearchExchangeInventory();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchExchangeInventoryForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem(this.key);
    this.clickOnTableFields = [{ 'title': 'enquiryNumber', 'isClickable': true }, { 'title': 'action', 'isClickable': true }]
   
    this.valueChangesForAutoComplate()
    if (this.searchExchangeInventoryForm.get('enquiryNumber').value==null && this.searchExchangeInventoryForm.get('fromDate').value==null && this.searchExchangeInventoryForm.get('toDate').value==null && this.searchExchangeInventoryForm.get('status').value==null) {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchExchangeInventoryForm.get('fromDate').patchValue(backDate);
    this.searchExchangeInventoryForm.get('toDate').patchValue(new Date());
    this.onClickSearch();
    }
    else{
      localStorage.getItem(this.key)
      this.onClickSearch();
    }
  }

  ngAfterViewInit() {
   
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate) {
        this.maxDate = this.newdate;
      }
      else {
        this.maxDate = maxDate;
        if (this.searchExchangeInventoryForm.get('toDate').value > this.maxDate)
          this.searchExchangeInventoryForm.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  createSearchExchangeInventory() {
    this.searchExchangeInventoryForm = this.fb.group({
      enquiryNumber: [null, Validators.compose([])],
      customerName: [null, Validators.compose([])],
      customerMobileNo: [null, Validators.compose([])],
      fromDate: [null, Validators.compose([])],
      toDate: [null, Validators.compose([])],
      status: [null, Validators.compose([])]
    })
  }
  private autoCustomerMobileNo(searchKey: string) {
    this.searchExchangeInventoryService.searchByCustomerMobileNo(searchKey).subscribe(res => {
      this.customerMobileList = res['result'];
    })
  }
  private autoCustomerName(searchKey: string) {
    this.searchExchangeInventoryService.searchByCustomerName(searchKey).subscribe(res => {
      this.customerNameList = res['result'];
    })
  }
  // private enquiryNumberChanges() {
  //   this.searchExchangeInventoryForm.controls.enquiryNumber.valueChanges.subscribe(changedValue => {
  //     this.autoEnquiryNo(changedValue)
  //   })
  // }
  displayEnquiryNumberFn(value): string | undefined {
    return (value && typeof value === 'object') ? value['receiptNumber'] : value;
  }
  autoEnquiryNo(enquiryNumber) {
    //this.enquiryNumber$ = this.searchEnquiryV2WebService.getEnquiryNumberNameMobileNoTehsil(enquiryNumber)
    this.searchExchangeInventoryService.searchEnquiryCode(enquiryNumber, 'Exchange_Inventory').subscribe(response => {
      this.enquiryNumber$ = response as BaseDto<Array<EnquiryNoNameThesil>>
    })
  }
  getVehicleStatus() {
    // this.searchExchangeInventoryService.dropdownGetAllVehicleStatus().subscribe(response => {
    //   this.productTypeList = response.result
    // })
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchExchangeInventoryForm.patchValue(event);
    if (event.enquiryNumber) {
      this.searchExchangeInventoryForm.get('enquiryNumber').patchValue(event.enquiryNumber.replace(searchValue, newValue));
      event.enquiryNumber = event.enquiryNumber.replace(searchValue, newValue)
    }
    console.log("this.searchEnquiry ", this.searchExchangeInventoryForm);

  }

  onClickSearch() {
    this.resetAllSearchValue();
    if (this.checkValidDatesInput()) {
      if (this.searchFlag == true && this.searchExchangeInventoryForm.get('enquiryNumber').value || this.searchExchangeInventoryForm.get('fromDate').value || this.searchExchangeInventoryForm.get('toDate').value || this.searchExchangeInventoryForm.get('status').value) {
        this.searchExchangeInventoryService
          .getEnquirySearch(this.setEnquirySearchResult()).subscribe(data => {
            console.log("search data ---->", data)
            data.result.map(((res: any) => {
              if (res.soldStatus == 'Available') {
                res.action = 'Sale';
                return res;
              }
            }))
            this.dataTable = this.tableDataService.convertIntoDataTable(data.result)

            this.totalTableRecords = data.count;

            console.log("Changed dataTable ---->", this.dataTable)
          })
      }else if (this.searchExchangeInventoryForm.get('fromDate').value == null && this.searchExchangeInventoryForm.get('toDate').value == null && this.searchExchangeInventoryForm.get('enquiryNumber').value==null && this.searchExchangeInventoryForm.get('status').value==null) {
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
    const fltEnqObj = this.searchExchangeInventoryForm.value as SearchFilterExchangeInventoryListDomain
    // let key='searchFilter';
    localStorage.setItem(this.key,JSON.stringify(this.searchExchangeInventoryForm.value))
    
    fltEnqObj.userId = loginUserId
    if (this.searchFlag == true && this.searchExchangeInventoryForm.get('enquiryNumber').value || this.searchExchangeInventoryForm.get('fromDate').value || this.searchExchangeInventoryForm.get('toDate').value || this.searchExchangeInventoryForm.get('status').value) {
      this.page = 0;
      this.size = 10;
      fltEnqObj.page = this.page
      fltEnqObj.size = this.size
      
    }
    else {
      fltEnqObj.page = this.page
      fltEnqObj.size = this.size
    }
    fltEnqObj.enquiryNumber = this.searchExchangeInventoryForm.value ? this.searchExchangeInventoryForm.value.enquiryNumber : null
    // fltEnqObj.hierId = this.searchEnquiry.value.orgHierLevel5 ? this.searchEnquiry.value.orgHierLevel5['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel4 ? this.searchEnquiry.value.orgHierLevel4['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel3 ? this.searchEnquiry.value.orgHierLevel3['org_hierarchy_id'] : (this.searchEnquiry.value.orgHierLevel2 ? this.searchEnquiry.value.orgHierLevel2['org_hierarchy_id'] : this.searchEnquiry.value.orgHierLevel1 ? this.searchEnquiry.value.orgHierLevel1['org_hierarchy_id'] : 0)))
    fltEnqObj.fromDate = this.searchExchangeInventoryForm.getRawValue() ? this.searchExchangeInventoryForm.value.fromDate : null
    fltEnqObj.toDate = this.searchExchangeInventoryForm.getRawValue() ? this.searchExchangeInventoryForm.value.toDate : null
    fltEnqObj.status = this.searchExchangeInventoryForm.getRawValue() ? this.searchExchangeInventoryForm.value.status : null

    this.filterData = this.removeNullFromObjectAndFormatDate(fltEnqObj);
    console.log("search Values--->", fltEnqObj)
    return fltEnqObj;

  }

  checkValidDatesInput() {
    const fltEnqObj = this.searchExchangeInventoryForm.value as SearchFilterExchangeInventoryListDomain

    fltEnqObj.fromDate = this.searchExchangeInventoryForm.getRawValue() ? this.searchExchangeInventoryForm.value.fromDate : null
    fltEnqObj.toDate = this.searchExchangeInventoryForm.getRawValue() ? this.searchExchangeInventoryForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    if (fromdates.length > 0) {
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

    }

    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }

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
      console.log("searchObject si ----> ", searchObject);
      return searchObject;
    }
  }


  onCancelClick() {
    this.dialog.closeAll();
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Sale Exchange Inventory?';
    const confirmationData = this.setConfirmationModalData(message);
    const oldVehId = this.oldVehId;
    const dialogRef = this.dialog.open(SalePopUpComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: oldVehId,
      // id:enquiryNumber
    }
    );

    dialogRef.afterClosed().subscribe(result => {
      this.onClickSearch();
    });
  }
  ngOnDestroy() {
    this.dialog.closeAll();
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  actionOnTableRecord(recordData) {

    if (recordData.btnAction.toLowerCase() === 'enquirynumber') {
      this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.activatedRoute})
    }

    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action.toLowerCase() === 'sale') {

        this.oldVehId = recordData.record.id;
        this.openConfirmDialog()
      }
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
  valueChangesForAutoComplate() {
    this.searchExchangeInventoryForm.get('enquiryNumber').valueChanges.subscribe(value => {
      if (value) {
        let enquiryNumber = typeof value == 'object' ? value.enquiryNumber : value
        console.log("value is: ", value)
        console.log("value is of type: ", typeof value)
        this.autoEnquiryNo(enquiryNumber)
      }
    })
  }
  pageChange(event) {
    if (!!event && event.page >= 0) {
      this.page = event.page;
      this.size = event.size
      this.searchFlag = false;
      this.onClickSearch();
    }
  }
  // For Date Format Used In Search Table
  dateChanges(event, receiptDate) {
    const date: Date = event.value as Date
    const searchValue = {
      searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
      receiptDate
    };
    this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.receiptDate);
    // this.searchExchangeInventory();
  }
  clearExchangeInventorySearch() {
    this.searchExchangeInventoryForm.reset()
    // this.onClickSearch()
    this.dataTable=null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  resetAllSearchValue() {
    this.actionNgModel = "";
    this.enquiryNumberNgModel = "";
    this.soldStatusNgModel = "";
    this.brandNameNgModel = "";
    this.modelNameNgModel = "";
    this.modelYearNgModel = "";
    this.invInDateNgModel = "";
    this.estimatedExchangePriceNgModel = "";
    this.buyerNameNgModel = "";
    this.buyerContactNoNgModel = "";
    this.saleDateNgModel = "";
    this.sellingPriceNgModel = "";
    this.saleRemarksNgModel = "";
  }
}
