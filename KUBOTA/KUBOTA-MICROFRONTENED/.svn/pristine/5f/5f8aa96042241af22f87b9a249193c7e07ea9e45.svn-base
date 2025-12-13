import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { ReceiptModeSearchDomain, ProductDomain, SeriesDomain, SubModelDomain, VariantDomain, SearchFilterPaymentReceiptListDomain, ReceiptNumberDomain } from 'search-payment-receipt-dto';
import { SearchPaymentRecieptService } from './search-payment-reciept.service';
import { ModelDomain } from 'SearchQuotationsModule';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { ToastrService } from 'ngx-toastr';
import { debug } from 'console';
import { BehaviorSubject } from 'rxjs';
import { MatDatepickerInput } from '@angular/material';
import { IFrameMessageSource, IFrameService, UrlSegment } from 'src/app/root-service/iFrame.service';
@Component({
  selector: 'app-search-payment-reciept',
  templateUrl: './search-payment-reciept.component.html',
  styleUrls: ['./search-payment-reciept.component.scss'],
  providers: [NgswSearchTableService]
})
export class SearchPaymentRecieptComponent implements OnInit {
  dropdownReceiptModeType: BaseDto<Array<ReceiptModeSearchDomain>>;
  dropdownGetAllProductType: BaseDto<Array<ProductDomain>>;
  dropdownGetAllSeriesType: BaseDto<Array<SeriesDomain>>;
  dropdownGetAllModelType: BaseDto<Array<ModelDomain>>;
  dropdowngetAllSubModelType: BaseDto<Array<SubModelDomain>>;
  dropdownGetAllVariantType: BaseDto<Array<VariantDomain>>;
  receiptNumberDomain: BaseDto<Array<ReceiptNumberDomain>>;
  dropdownReceiptType: BaseDto<Array<String>>;
  searchValue: ColumnSearch = {} as ColumnSearch
  searchPaymentReceiptForm: FormGroup;
  public todaysDate = new Date();
  totalTableElements: number;
  public dealerType: string;
  customerMobileList = [];
  customerNameList = [];
  dataTable: DataTable;
  itemNumberList = [];
  showAdvance = false;
  actionButtons = [];
  clearSearchRow = new BehaviorSubject<string>("");
  size = 10;
  page = 0;
  hiddenField: boolean = true
  searchFlag: boolean = true;

  receiptNumberNgModel: any = "";
  receiptTypeNgModel: any = "";
  receiptDateNgModel: any = "";
  receiptModeNgModel: any = "";
  receiptAmountNgModel: any = "";
  enquiryNumberNgModel: any = "";
  enquiryDateNgModel: any = "";
  enquiryStatusNgModel: any = "";
  variantNgModel: any = "";
  subModelNgModel: any = "";
  customerBalanceNgModel: any = "";
  firstNameNgModel: any = "";
  address1NgModel: any = "";
  pinCodeNgModel: any = "";
  postOfficeNgModel: any = "";
  cityNgModel: any = "";
  tehsilNgModel: any = "";
  districtNgModel: any = "";
  stateNgModel: any = "";
  remarksNgModel: any = "";
  transactionNoNgModel: any = "";
  transactionDateNgModel: any = "";
  bankNameNgModel: any = "";
  serviceProvidesNgModel: any = "";
  cardNoNgModel: any = "";
  cardTypeNgModel: any = "";
  cardNameNgModel: any = "";
  mobileNumberNgModel: any = "";
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  searchFilter: SearchFilterPaymentReceiptListDomain;
  key = 'pr'
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private loginFormService: LoginFormService,
    private tableDataService: NgswSearchTableService,
    private searchPaymentRecieptService: SearchPaymentRecieptService,
    private toastr: ToastrService,
    private iFrameService: IFrameService,
  ) {
    this.dealerType = this.loginFormService.getLoginUserType().toLowerCase();
  }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.getReceiptMode();
    this.createSearchPaymentReceipt();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchPaymentReceiptForm != null) {
      this.searchPaymentReceiptForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');

    this.getAllProduct();
    this.getAllSeries();
    this.getAllModel();
    this.getAllSubModel();
    this.getAllVariant();
    this.getReceiptType();
    this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    // this.searchPaymentReceipt();
    if (this.searchPaymentReceiptForm.get('receiptType').value == null && this.searchPaymentReceiptForm.get('receiptMode').value == null && this.searchPaymentReceiptForm.get('fromDate').value == null && this.searchPaymentReceiptForm.get('toDate').value == null && this.searchPaymentReceiptForm.get('receiptNo').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchPaymentReceiptForm.get('fromDate').patchValue(backDate);
      this.searchPaymentReceiptForm.get('toDate').patchValue(new Date());
      this.searchPaymentReceipt();
    }
    else {
      localStorage.getItem(this.key)
      this.searchPaymentReceipt();
    }
  }
  ngAfterViewInit() {
    // this.searchPaymentReceipt();
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
        if (this.searchPaymentReceiptForm.get('toDate').value > this.maxDate)
          this.searchPaymentReceiptForm.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  createSearchPaymentReceipt() {
    this.searchPaymentReceiptForm = this.fb.group({
      receiptNo: [null, Validators.compose([])],
      receiptMode: [null, Validators.compose([])],
      customerName: [null, Validators.compose([])],
      customerMobileNo: [null, Validators.compose([])],
      fromDate: [null, Validators.compose([])],
      toDate: [{ value: null, disabled: false }, Validators.compose([])],
      receipttodate: [null, Validators.compose([])],
      receiptType: [null, Validators.compose([])],
      product: [null, Validators.compose([])],
      series: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      subModel: [null, Validators.compose([])],
      variant: [null, Validators.compose([])],
      itemNo: [null, Validators.compose([])],
    })
    this.receiptNumberChanges();
    this.customerNameChanges();
    this.customerMobileChanges();
    this.itemNumberChanges();
  }
  private receiptNumberChanges() {

    this.searchPaymentReceiptForm.controls.receiptNo.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.autoReceiptNumber(changedValue)
      }
    })

  }
  private customerNameChanges() {

    this.searchPaymentReceiptForm.controls.customerName.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.autoCustomerName(changedValue)
      }
    })

  }
  private customerMobileChanges() {

    this.searchPaymentReceiptForm.controls.customerMobileNo.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.autoCustomerMobileNo(changedValue)
      }
    })

  }
  private itemNumberChanges() {
    this.searchPaymentReceiptForm.controls.itemNo.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.autoItemNumber(changedValue)
      }
    })

  }
  private autoCustomerName(searchKey: string) {
    this.searchPaymentRecieptService.searchByCustomerName(searchKey).subscribe(res => {
      this.customerNameList = res['result'];
    })
  }
  private autoCustomerMobileNo(searchKey: string) {
    this.searchPaymentRecieptService.searchByCustomerMobileNo(searchKey).subscribe(res => {
      this.customerMobileList = res['result'];
    })
  }
  private autoItemNumber(searchKey: string) {
    this.searchPaymentRecieptService.searchByItemNumber(searchKey).subscribe(res => {
      this.itemNumberList = res['result'];
    })
  }
  displayReceiptNumberFn(value): string | undefined {
    return (value && typeof value === 'object') ? value['receiptNumber'] : value;
  }
  displayCustomerNameFn(value): string | undefined {
    return (value && typeof value === 'object') ? value['customerName'] : value;
  }
  displayCustomerMobileFn(value): string | undefined {
    return (value && typeof value === 'object') ? value['mobileNumber'] : value;
  }
  getReceiptType() {
    this.searchPaymentRecieptService.dropdownReceiptType().subscribe(response => {
      this.dropdownReceiptType = response as BaseDto<Array<String>>
    });
  }
  getReceiptMode() {
    this.searchPaymentRecieptService.dropdownReceiptModeType().subscribe(response => {
      this.dropdownReceiptModeType = response as BaseDto<Array<ReceiptModeSearchDomain>>
    })
  }
  getAllProduct() {
    this.searchPaymentRecieptService.dropdownGetAllProductType().subscribe(response => {
      this.dropdownGetAllProductType = response as BaseDto<Array<ProductDomain>>
    })
  }
  getAllSeries() {
    this.searchPaymentRecieptService.dropdownGetAllSeriesType().subscribe(response => {
      this.dropdownGetAllSeriesType = response as BaseDto<Array<SeriesDomain>>
    })
  }
  getAllModel() {
    this.searchPaymentRecieptService.dropdownGetAllModelType().subscribe(response => {
      this.dropdownGetAllModelType = response as BaseDto<Array<ModelDomain>>
    })
  }
  getAllSubModel() {
    this.searchPaymentRecieptService.dropdowngetAllSubModelType().subscribe(response => {
      this.dropdowngetAllSubModelType = response as BaseDto<Array<SubModelDomain>>
    })
  }
  getAllVariant() {
    this.searchPaymentRecieptService.dropdownGetAllVariantType().subscribe(response => {
      this.dropdownGetAllVariantType = response as BaseDto<Array<VariantDomain>>
    })
  }
  autoReceiptNumber(value) {
    this.searchPaymentRecieptService.searchByReceiptNumber(value).subscribe(response => {
      this.receiptNumberDomain = response as BaseDto<Array<ReceiptNumberDomain>>
    })
  }
  searchPaymentReceipt() {
    this.receiptDateNgModel != null ? this.receiptDateNgModel : null;
    if (this.receiptDateNgModel != null && this.receiptDateNgModel != "") {
      this.searchPaymentReceiptForm.reset();
    }
    let filterobj = {} as SearchFilterPaymentReceiptListDomain;
    filterobj = this.searchPaymentReceiptForm.value as SearchFilterPaymentReceiptListDomain;
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchPaymentReceiptForm.value))

    if ((this.receiptDateNgModel != null && filterobj.fromDate == null && filterobj.toDate == null) ||
      (this.receiptDateNgModel != "" && filterobj.fromDate == "" && filterobj.toDate == "")) {
      filterobj.fromDate = this.receiptDateNgModel;
      filterobj.toDate = this.receiptDateNgModel;
    }
    this.receiptNumberNgModel = "";
    this.receiptTypeNgModel = "";
    this.receiptDateNgModel = "";
    this.receiptModeNgModel = "";
    this.receiptAmountNgModel = "";
    this.enquiryNumberNgModel = "";
    this.enquiryDateNgModel = "";
    this.enquiryStatusNgModel = "";
    this.variantNgModel = "";
    this.subModelNgModel = "";
    this.customerBalanceNgModel = "";
    this.firstNameNgModel = "";
    this.address1NgModel = "";
    this.pinCodeNgModel = "";
    this.postOfficeNgModel = "";
    this.cityNgModel = "";
    this.tehsilNgModel = "";
    this.districtNgModel = "";
    this.stateNgModel = "";
    this.remarksNgModel = "";
    this.transactionNoNgModel = "";
    this.transactionDateNgModel = "";
    this.bankNameNgModel = "";
    this.serviceProvidesNgModel = "";
    this.cardNoNgModel = "";
    this.cardTypeNgModel = "";
    this.cardNameNgModel = "";
    this.mobileNumberNgModel = "";
    typeof filterobj.customerName === 'object' && filterobj.customerName !== null ? filterobj.customerName = filterobj.customerName['customerName'] : null;
    typeof filterobj.customerMobileNo === 'object' && filterobj.customerMobileNo !== null ? filterobj.customerMobileNo = filterobj.customerMobileNo['mobileNumber'] : null;
    typeof filterobj.receiptNo === 'object' && filterobj.receiptNo !== null ? filterobj.receiptNo = filterobj.receiptNo['receiptNumber'] : null;
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
   
    filterobj.size = this.size;
    filterobj.page = this.page.toString();
    // this.searchFlag = true;
    this.searchFilter = filterobj
    console.log('this.searchFilter---1', this.searchFilter)
    if ((filterobj['fromDate'] === null || filterobj['fromDate'] === "" || filterobj['fromDate'] === undefined)) {
      if ((filterobj['toDate'] === null || filterobj['toDate'] === "" || filterobj['toDate'] === undefined)) {
        console.log('this.searchFilter---2', this.searchPaymentReceiptForm.get('receiptType').value)
        if (this.searchPaymentReceiptForm.get('receiptType').value || this.searchPaymentReceiptForm.get('receiptMode').value || this.searchPaymentReceiptForm.get('fromDate').value || this.searchPaymentReceiptForm.get('toDate').value || this.searchPaymentReceiptForm.get('receiptNo').value) {
          localStorage.setItem(this.key, JSON.stringify(this.searchPaymentReceiptForm.value))
          this.searchPaymentRecieptService.searchUsingFilter(filterobj).subscribe(data => {
            this.dataTable = this.tableDataService.convertIntoDataTable(data.result);
            this.totalTableElements = data['count'];
          })
        }
        else {
          this.toastr.error("Please fill atleast one input field.");
        }
      }
    } else if ((filterobj['fromDate'] !== null || filterobj['fromDate'] !== "" || filterobj['fromDate'] !== undefined)) {
      if ((filterobj['toDate'] === null || filterobj['toDate'] === "" || filterobj['toDate'] === undefined)) {
        this.toastr.error("Please Select Date Range.");
      } else {
        this.searchPaymentRecieptService.searchUsingFilter(filterobj).subscribe(data => {
          this.dataTable = this.tableDataService.convertIntoDataTable(data.result);
          this.totalTableElements = data['count'];
        })
      }
    }
  }
  actionOnTableRecord(recordData) {
    if (recordData['btnAction'].toLocaleLowerCase() === 'receiptnumber') {
      this.router.navigate(['view', btoa(recordData.record.receiptNumber)], { relativeTo: this.activatedRoute })
    }
  }
  pageChange(event) {
    if (!!event && event.page >= 0) {
      this.page = event.page;
      this.size = event.size
      this.searchFlag = false;
      this.searchPaymentReceipt();
    }
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.searchPaymentReceiptForm.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchPaymentReceiptForm.patchValue({
      quotationFromDate: event.quotationFromDate ? new Date(event.quotationFromDate) : null,
      quotationToDate: event.quotationToDate ? new Date(event.quotationToDate) : null,
    });

  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
  // For Date Format Used In Search Table
  dateChanges(event, receiptDate) {
    const date: Date = event.value as Date
    const searchValue = {
      searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
      receiptDate
    };
    this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.receiptDate);
    this.searchPaymentReceipt();
  }
  /* added by vinay*/
  clearForm() {
    this.searchPaymentReceiptForm.reset();
    // this.searchPaymentReceipt();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  /* added by vinay*/
}
