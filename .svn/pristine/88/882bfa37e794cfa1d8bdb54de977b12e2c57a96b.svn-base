import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { DateAdapter, MatSelectChange, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { SpareGrnSearchPagePresenter } from '../spares-grn-search-page/spare-grn-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { NgswSearchTableService, TableHeading, ColumnSearch } from 'ngsw-search-table';
import { SearchSpareGrn } from '../../domain/spare-grn.domain';
import { ObjectUtil } from '../../../../../utils/object-util';
import { Router, ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { SparesGrnSearchServiceApi } from './spares-grn-search-api.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ToastrService } from 'ngx-toastr';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-spares-grn-search',
  templateUrl: './spares-grn-search.component.html',
  styleUrls: ['./spares-grn-search.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SparesGrnSearchServiceApi
  ],
  viewProviders: [SpareGrnSearchPagePresenter]
})
export class SparesGrnSearchComponent implements OnInit, AfterViewInit {

  dataTable: DataTable;
  totalTableElements: number;
  spareGrnSearchForm: FormGroup;
  clearSearchRow = new BehaviorSubject<string>("");
  page: number;
  size: number;
  clickOnTableFields: Array<TableHeading> = [{ title: 'grnNumber', isClickable: true }, { title: 'edit', isClickable: true }];
  searchValue: ColumnSearch;
  dmsGrnNumberList$: Observable<SelectList[]>;
  invoiceNumberList$: Observable<SelectList[]>;
  grnStatusList$: Observable<SelectList[]>;
  grnTypeList$: Observable<SelectList[]>;
  supplierNameList$: Observable<SelectList[]>;
  supplierTypeList$: Observable<SelectList[]>;
  serverDate
  actionButtons
  searchFilter = {} as SearchSpareGrn;
  searchFlag: boolean = true;
  grnNumberNgModel: any = "";
  grnDateNgModel: any = "";
  grnTypeNgModel: any = "";
  grnStatusNgModel: any = "";
  supplierNameNgModel: any = "";
  supplierTypeNgModel: any = "";
  supplierGstNoNgModel: any = "";
  modeOfTransportNgModel: any = "";
  supplierAddress1NgModel: any = "";
  supplierAddress2NgModel: any = "";
  supplierAddress3NgModel: any = "";
  supplierStateNgModel: any = "";
  transporterNgModel: any = "";
  invoiceNumberNgModel: any = "";
  invoiceDateNgModel: any = "";
  invoiceValueNgModel: any = "";
  grnDoneByNgModel: any = "";
  receiptValueNgModel: any = "";
  goodsReceiptDateNgModel: any = "";
  storesNgModel: any = "";
  basicAmountNgModel: any = "";
  gstAmountNgModel: any = "";
  totalGrnAmountNgModel: any = "";
  searchFilterValues: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  key = 'sgrn'
  constructor(
    private sparesGrnSearchServiceApi: SparesGrnSearchServiceApi,
    private ngswSearchTableService: NgswSearchTableService,
    private spareGrnSearchPagePresenter: SpareGrnSearchPagePresenter,
    private router: Router,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.spareGrnSearchForm = this.spareGrnSearchPagePresenter.getSpareGrnSearchForm;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.spareGrnSearchForm.patchValue(this.searchFilterValues)
    }
    this.dmsGrnNumberValueChanges();
    this.invoiceNumberValueChanges();
    this.getGrnStatus();
    this.getGrnType();
    this.sparesGrnSearchServiceApi.getServerDate().subscribe(date => {
      this.serverDate = date.result;
    });
    if (this.spareGrnSearchForm.get('grnStatus').value == null && this.spareGrnSearchForm.get('grnType').value == null && this.spareGrnSearchForm.get('invoiceNumber').value == null && this.spareGrnSearchForm.get('dmsGrnNumber').value == null && this.spareGrnSearchForm.get('supplierType').value == null && this.spareGrnSearchForm.get('supplierName').value == null && this.spareGrnSearchForm.get('fromDate').value == null && this.spareGrnSearchForm.get('toDate').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.spareGrnSearchForm.get('fromDate').patchValue(backDate);
      this.spareGrnSearchForm.get('toDate').patchValue(new Date());
      this.searchSparesGrn();
    }
    else {
      this.searchSparesGrn();
    }
  }

  ngAfterViewInit() {
  }
  fromDateSelected(event) {
    
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.spareGrnSearchForm.get('toDate').value > this.maxDate)
        this.spareGrnSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  private getGrnStatus() {
    this.grnStatusList$ = this.sparesGrnSearchServiceApi.getGrnStatus();
  }
  private getGrnType() {
    this.grnTypeList$ = this.sparesGrnSearchServiceApi.getGrnType();
    this.supplierNameList$ = null;

  }
  public getSupplierName(event: MatSelectChange) {

    this.supplierNameList$ = this.sparesGrnSearchServiceApi.getSupplierName(event.value.id);
  }
  getSuppilerType(event: MatSelectChange) {
    
    this.supplierTypeList$ = this.sparesGrnSearchServiceApi.getSuppilerType(event.value);
    this.spareGrnSearchForm.get('supplierName').enable()
  }
  searchSparesGrn() {
    this.resetAllSearchValue()
    const searchField: SearchSpareGrn = this.spareGrnSearchForm.getRawValue();
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
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      searchField.page = this.page,
        searchField.size = this.size
    }
    else {
      searchField.page = this.page,
        searchField.size = this.size
    }



    ObjectUtil.removeNulls(searchField);
    
    this.searchFilter = searchField;
    localStorage.setItem(this.key, JSON.stringify(this.spareGrnSearchForm.value))
    if (this.checkValidDatesInput(searchField)) {
      if (this.spareGrnSearchForm.get('grnStatus').value || this.spareGrnSearchForm.get('grnType').value || this.spareGrnSearchForm.get('invoiceNumber').value || this.spareGrnSearchForm.get('dmsGrnNumber').value || this.spareGrnSearchForm.get('supplierType').value || this.spareGrnSearchForm.get('supplierName').value || this.spareGrnSearchForm.get('fromDate').value || this.spareGrnSearchForm.get('toDate').value) {

        this.sparesGrnSearchServiceApi.searchSpareGrn(searchField).subscribe(res => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res.result);
          this.totalTableElements = res.count;
        });
      }
      else if (this.spareGrnSearchForm.get('fromDate').value == null || this.spareGrnSearchForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
  }
  pageChangeOfSearchTable(event) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.spareGrnSearchForm.patchValue(event);
    this.searchSparesGrn();
  }
  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData.record.id)
    if (recordData.btnAction === 'grnNumber') {
      this.router.navigate(['view/', id], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['update/', id], { relativeTo: this.activatedRoute })
    }
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  private dmsGrnNumberValueChanges() {
    this.spareGrnSearchForm.get('dmsGrnNumber').valueChanges.subscribe(val => {
      if (typeof val === 'string') {
        this.dmsGrnNumberList$ = this.sparesGrnSearchServiceApi.searchBySpareGrnNumber(val);
      }
    });
  }
  private invoiceNumberValueChanges() {
    this.spareGrnSearchForm.get('invoiceNumber').valueChanges.subscribe(val => {
      if (typeof val === 'string') {
        this.invoiceNumberList$ = this.sparesGrnSearchServiceApi.searchInvoiceNumberFromGrn(val);
      }
    });
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.spareGrnSearchForm.patchValue(event);
    this.spareGrnSearchForm.patchValue({
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
    });
    if (event.fromDate) {
      this.spareGrnSearchForm.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.spareGrnSearchForm.get('toDate').patchValue(new Date(event.toDate))
    }
    this.searchSparesGrn();
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  resetSearchForm() {
    const { page, size } = this.spareGrnSearchForm.getRawValue();
    this.spareGrnSearchForm.reset();
    this.spareGrnSearchForm.patchValue({ page, size });
    const searchFilter = this.spareGrnSearchForm.getRawValue();
    ObjectUtil.removeNulls(searchFilter);
    this.searchFilter = searchFilter;
    // this.searchSparesGrn();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  checkValidDatesInput(filtObj: SearchSpareGrn) {
    // filtObj.quotationFromDate = this.partQuotationSearchForm.value.quotationFromDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationFromDate) : null
    // filtObj.quotationToDate = this.partQuotationSearchForm.value.quotationToDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationToDate) : null
    const quotationFromDate = filtObj.fromDate;
    const quotationToDate = filtObj.toDate


    if ((quotationFromDate === null || quotationFromDate === "" || quotationFromDate === undefined)) {
      if ((quotationToDate === null || quotationToDate === "" || quotationToDate === undefined)) {
        return true;
      } else {
        return false;
      }
    } else {
      if ((quotationToDate === null || quotationToDate === "" || quotationToDate === undefined)) {
        return false;
      } else {
        return true;
      }
    }
  }
  resetAllSearchValue() {
    this.grnNumberNgModel = "";
    this.grnDateNgModel = "";
    this.grnTypeNgModel = "";
    this.grnStatusNgModel = "";
    this.supplierNameNgModel = "";
    this.supplierTypeNgModel = "";
    this.supplierGstNoNgModel = "";
    this.modeOfTransportNgModel = "";
    this.supplierAddress1NgModel = "";
    this.supplierAddress2NgModel = "";
    this.supplierAddress3NgModel = "";
    this.supplierStateNgModel = "";
    this.transporterNgModel = "";
    this.invoiceNumberNgModel = "";
    this.invoiceDateNgModel = "";
    this.invoiceValueNgModel = "";
    this.grnDoneByNgModel = "";
    this.receiptValueNgModel = "";
    this.goodsReceiptDateNgModel = "";
    this.storesNgModel = "";
    this.basicAmountNgModel = "";
    this.gstAmountNgModel = "";
    this.totalGrnAmountNgModel = "";
  }

  generateReport(){
    const formValues = this.spareGrnSearchForm.getRawValue();
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    ObjectUtil.removeNulls(formValues);

    if (this.checkValidDatesInput(formValues)) {
      if (this.spareGrnSearchForm.get('grnStatus').value || this.spareGrnSearchForm.get('grnType').value || this.spareGrnSearchForm.get('invoiceNumber').value || this.spareGrnSearchForm.get('dmsGrnNumber').value || this.spareGrnSearchForm.get('supplierType').value || this.spareGrnSearchForm.get('supplierName').value || this.spareGrnSearchForm.get('fromDate').value || this.spareGrnSearchForm.get('toDate').value) {
         this.downloadReport(formValues);
      } else if (this.spareGrnSearchForm.get('fromDate').value == null || this.spareGrnSearchForm.get('toDate').value == null) {
         this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  private downloadReport(searchObject) {
    if(this.spareGrnSearchForm.get('fromDate').value == null || this.spareGrnSearchForm.get('toDate').value == null){
        this.toastr.error("Please Select Date Range First")
    }else{
    this.sparesGrnSearchServiceApi.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
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
