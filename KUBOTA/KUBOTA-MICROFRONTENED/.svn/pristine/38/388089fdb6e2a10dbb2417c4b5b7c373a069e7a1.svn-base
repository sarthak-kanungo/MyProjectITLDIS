import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { SearchInvoiceService } from './search-invoice.service';
import { FormGroup } from '@angular/forms';
import { DataTable, ColumnSearch, TableHeading, NgswSearchTableService } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { SearchInvoiceApiService } from './search-invoice-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { SearchAllotmentDeAllotmentService } from '../../../de-allotment/component/search-allotment-de-allotment/search-allotment-de-allotment.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DateService } from '../../../../../root-service/date.service';
import { MatDatepickerInput } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-invoice',
  templateUrl: './search-invoice.component.html',
  styleUrls: ['./search-invoice.component.scss'],
  providers: [
    SearchInvoiceService,
    SearchInvoiceApiService,
    SearchAllotmentDeAllotmentService
  ]
})
export class SearchInvoiceComponent implements OnInit {

  options = false
  searchform = false
  selected = 10;
  invoiceNumberList$: Observable<SelectList[]>;
  chassisNumberList$: Observable<SelectList[]>;
  customerNameList$: Observable<SelectList[]>;
  mobileNumberList$: Observable<SelectList[]>;
  productList$: Observable<SelectList[]>;
  seriesList$: Observable<SelectList[]>;
  subModelList$: Observable<SelectList[]>;
  variantList$: Observable<SelectList[]>;
  modelList$: Observable<SelectList[]>;
  clearSearchRow = new BehaviorSubject<string>("");
  itemNoList$: Observable<SelectList[]>;
  enquiryNumberList$: Observable<SelectList[]>;
  enquiryTypeList$: Observable<SelectList[]>;
  invoiceStatusList$: Observable<SelectList[]>;
  invoiceTypeList$: Observable<SelectList[]>;

  states: string[] = ['states'];
  invoicestatus: string[] = ['invoicestatus'];
  invoiceTypes: string[] = ['invoiceTypes'];
  seriesList: string[] = ['series'];
  models: string[] = ['models'];
  subModelList: string[] = ['submodels'];
  variantList: string[] = ['variants'];

  page: number = 0;
  size: number = 10;
  searchInvoiceForm: FormGroup;
  @Input() dataTable: DataTable;
  @Input() totalSearchRecordCount: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  clickOnTableFields: Array<TableHeading> = [{ title: 'invoiceNumber', isClickable: true }, { title: 'cancel', isClickable: true }, { title: 'edit', isClickable: true }];
  @Output() searchFilterChange = new EventEmitter<InvoiceSearchForm>()
  column: string;
  searchFilter: any;
  serverCurrentDate: string;

  invoiceNumberNgModel: any = "";
  invoiceTypeNgModel: any = "";
  deliveryChallanTypeNgModel: any = "";
  invoiceDateNgModel: any = "";
  customerCodeNgModel: any = "";
  customerNameNgModel: any = "";
  invoiceStatusNgModel: any = "";
  hypothecationNgModel: any = "";
  customerTypeNgModel: any = "";
  companyNameNgModel: any = "";
  chassisNoNgModel: any = "";
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key = 'si';
  searchFlag: boolean = true;
  constructor(
    private searchInvoiceService: SearchInvoiceService,
    private router: Router,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private dateService: DateService,
    private searchInvoiceApiService: SearchInvoiceApiService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.getDateFromServer();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchInvoiceForm = this.searchInvoiceService.createSearchInvoice();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchInvoiceForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    console.log(this.searchInvoiceService)
    console.log("this.searchInvoiceService")
    this.invoiceNumberList$ = this.searchInvoiceService.getInvoiceNumberList$;
    this.chassisNumberList$ = this.searchInvoiceService.getChassisNumberList$;
    this.customerNameList$ = this.searchInvoiceService.getCustomerNameList$;
    this.mobileNumberList$ = this.searchInvoiceService.getMobileNumberList$;
    this.productList$ = this.searchInvoiceService.getProductTypeList$;
    this.seriesList$ = this.searchInvoiceService.getSeriesList$;
    this.subModelList$ = this.searchInvoiceService.getSubModelList$;
    this.variantList$ = this.searchInvoiceService.getVariantList$;
    this.modelList$ = this.searchInvoiceService.getModelList$;
    this.itemNoList$ = this.searchInvoiceService.getItemNoList$;
    this.enquiryNumberList$ = this.searchInvoiceService.getEnquiryNumberList$;
    this.enquiryTypeList$ = this.searchInvoiceService.getEnquiryTypeList$;
    this.invoiceStatusList$ = this.searchInvoiceService.getInvoiceStatusList$;
    this.invoiceTypeList$ = this.searchInvoiceService.getInvoiceTypeList$;
    console.log(this.searchInvoiceService)
    console.log("this.searchInvoiceService.................................")
    if ((this.searchInvoiceForm.get('invoiceNumber').value == null || this.searchInvoiceForm.get('invoiceNumber').value == '') && (this.searchInvoiceForm.get('chassisNo').value == null || this.searchInvoiceForm.get('chassisNo').value == '') && (this.searchInvoiceForm.get('customerName').value == null || this.searchInvoiceForm.get('customerName').value == '') && (this.searchInvoiceForm.get('mobileNo').value == null || this.searchInvoiceForm.get('mobileNo').value == '') && (this.searchInvoiceForm.get('fromDate').value == null || this.searchInvoiceForm.get('fromDate').value == '') && (this.searchInvoiceForm.get('toDate').value == null || this.searchInvoiceForm.get('toDate').value == '')) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchInvoiceForm.get('fromDate').patchValue(backDate);
      this.searchInvoiceForm.get('toDate').patchValue(new Date());
      this.searchInvoiceRecord();
    } else {
      localStorage.getItem(this.key)
      this.searchInvoiceRecord();
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
        if (this.searchInvoiceForm.get('toDate').value > this.maxDate)
          this.searchInvoiceForm.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  private getDateFromServer() {
    this.searchInvoiceApiService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.serverCurrentDate = dateRes.result;
      }
    });
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    if (recordData.btnAction === 'invoiceNumber') {
      this.router.navigate(['view/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'cancel') {
      this.router.navigate(['cancel/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['edit/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
  }
  pageChangeOfSearchTable(event) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    this.searchInvoiceRecord();
  }
  searchTableColumnChanged(column: string) {
    this.column = column;
  }
  searchInvoiceRecord() {
    this.invoiceNumberNgModel = "";
    this.invoiceTypeNgModel = "";
    this.deliveryChallanTypeNgModel = "";
    this.invoiceDateNgModel = "";
    this.customerCodeNgModel = "";
    this.customerNameNgModel = "";
    this.invoiceStatusNgModel = "";
    this.hypothecationNgModel = "";
    this.customerTypeNgModel = "";
    this.companyNameNgModel = "";
    this.chassisNoNgModel = "";
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchInvoiceForm.value))
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

    const searchField = {
      ...this.searchInvoiceForm.getRawValue(),
      page: this.page,
      size: this.size,
      // column: this.column
    };

    console.log('searchField: ', searchField);
    this.searchFilter = searchField;
    if (searchField['fromDate'] && searchField['toDate']) {
      searchField['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['fromDate']);
      searchField['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['toDate'])
    }
    console.log('searchField:--2', searchField)
    ObjectUtil.removeNulls(searchField);

    this.searchFilter = searchField;
    if ((searchField['fromDate'] === null || searchField['fromDate'] === "" || searchField['fromDate'] === undefined)) {
      if ((searchField['toDate'] === null || searchField['toDate'] === "" || searchField['toDate'] === undefined)) {
        console.log('searchField:-- 3', this.searchInvoiceForm.get('fromDate').value);
        if (this.searchInvoiceForm.get('invoiceNumber').value || this.searchInvoiceForm.get('chassisNo').value || this.searchInvoiceForm.get('customerName').value || this.searchInvoiceForm.get('mobileNo').value || this.searchInvoiceForm.get('fromDate').value || this.searchInvoiceForm.get('toDate').value) {

          this.searchFilterChange.emit(searchField);
        }
        else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    }

    else if ((searchField['fromDate'] !== null || searchField['fromDate'] !== "" || searchField['fromDate'] !== undefined)) {
      if ((searchField['toDate'] === null || searchField['toDate'] === "" || searchField['toDate'] === undefined)) {
        this.toastr.error("Please Select Date Range");
      } else {
        this.searchFilterChange.emit(searchField);
      }
    }
  }
  clear() {
    this.searchInvoiceForm.reset();
    // this.page = 0;
    // this.size = 10;
    // this.searchInvoiceRecord();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }
  displayFn(obj: SelectList): string | number | undefined {
    return obj ? obj.value : undefined;
  }
  displayCodeFn(obj: SelectList): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  // private setSearchResultTable(searchValue: InvoiceSearchForm) {
  //   this.searchInvoiceApiService.searchInvoice(searchValue).subscribe(searchRes => {
  //     console.log('searchRes=====>', searchRes);
  //     this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
  //     this.totalSearchRecordCount = searchRes['count'];
  //   });
  // }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.searchInvoiceForm.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchInvoiceForm.patchValue({
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
    });
    if (event.fromDate) {
      this.searchInvoiceForm.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.searchInvoiceForm.get('toDate').patchValue(new Date(event.toDate))
    }
    this.searchInvoiceRecord();
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
  getAllVariant(event) {
    console.log('event', event)

  }
  getAllSubModel(event) {
    console.log('event', event)

  }
  getAllModel(event) {
    console.log('event', event)

  }
}
