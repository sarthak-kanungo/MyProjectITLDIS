import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';

import { SparePaymentReceiptSearchPagePresenter } from './spare-payment-receipt-search-page.presenter';
import { SparePaymentReceiptSearchPageService } from './spare-payment-receipt-search-page.service';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SearchSparesPaymentReceipt } from 'SparePaymentReceiptModule';
import { BaseDto } from 'BaseDto';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-spare-payment-receipt-search-page',
  templateUrl: './spare-payment-receipt-search-page.component.html',
  styleUrls: ['./spare-payment-receipt-search-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparePaymentReceiptSearchPagePresenter, SparePaymentReceiptSearchPageService]
})
export class SparePaymentReceiptSearchPageComponent implements OnInit {
  public searchPaymentReceiptForm = this.sparePaymentReceiptSearchPagePresenter.getSearchPaymentReceiptForm;
  public page = 0;
  public size = 10;
  public dataTable: DataTable;
  public filterData: object = {};
  public searchValue: ColumnSearch;
  public totalTableElements: number;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");

  receiptNumberNgModel: any
  receiptTypeNgModel: any
  receiptModeNgModel: any
  receiptDateNgModel: any
  receiptAmountNgModel: any
  payeeTypeNgModel: any
  customerNameNgModel: any
  customerAddress1NgModel: any
  customerAddress2NgModel: any
  countryNgModel: any
  stateNgModel: any
  districtNgModel: any
  tehsilNgModel: any
  villageNgModel: any
  postOfficeNgModel: any
  pinCodeNgModel: any
  remarkNgModel: any
  checkDDNumberNgModel: any
  checkDDDateNgModel: any
  checkDDBankNgModel: any
  cardTypeNgModel: any
  cardNumberNgModel: any
  cardNameNgModel: any
  transactionNumberNgModel: any
  transactionDateNgModel: any
  today = new Date();
  minDate: Date;
  maxDate: Date
  searchFlag: boolean = true;
  key='spare-payment-receipt';
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private _changeDetectorRef: ChangeDetectorRef,
    private tableDataService: NgswSearchTableService,
    private sparePaymentReceiptSearchPageService: SparePaymentReceiptSearchPageService,
    private sparePaymentReceiptSearchPagePresenter: SparePaymentReceiptSearchPagePresenter,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.getFilterState();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchPaymentReceiptForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.searchPaymentReceiptForm.get('receiptNumber').value==null && this.searchPaymentReceiptForm.get('receiptMode').value==null && this.searchPaymentReceiptForm.get('fromDate').value==null && this.searchPaymentReceiptForm.get('toDate').value==null) {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.searchPaymentReceiptForm.get('fromDate').patchValue(backDate);
    this.searchPaymentReceiptForm.get('toDate').patchValue(new Date());
    this.searchSparePaymentReceipt();
    }
    else{
      localStorage.getItem(this.key)
      this.searchSparePaymentReceipt()
    }
  }
  ngAfterViewInit() {
   
  }
  private getFilterState() {
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      console.log('queryMap--',queryMap['params'])
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        this.filterData = JSON.parse(queryMap.get('searchFilter'));
        this.page = JSON.parse(queryMap.get('searchFilter')).page;
        this.size = JSON.parse(queryMap.get('searchFilter')).size;
      }
    })
  }
  public searchSparePaymentReceipt() {
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
    let formValues = this.searchPaymentReceiptForm.value as SearchSparesPaymentReceipt;
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchPaymentReceiptForm.value))
    formValues['page'] = this.page;
    formValues['size'] = this.size;
    this.filterData = this.sparePaymentReceiptSearchPageService.removeNullFromObjectAndFormatDate(formValues);
    this.router.navigate([], { queryParams: { searchFilter: JSON.stringify(this.filterData) } });
    if (this.checkValidDatesInput()) {
      if (this.searchPaymentReceiptForm.get('receiptNumber').value || this.searchPaymentReceiptForm.get('receiptMode').value || this.searchPaymentReceiptForm.get('fromDate').value || this.searchPaymentReceiptForm.get('toDate').value) {
        this.sparePaymentReceiptSearchPageService.searchSparePaymentReceipt(formValues).subscribe((res: BaseDto<object[]>) => {
          if (res) {
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count;
            this._changeDetectorRef.markForCheck();
          }
        })
      }
      else if (this.searchPaymentReceiptForm.get('fromDate').value == null && this.searchPaymentReceiptForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchPaymentReceiptForm.value

    fltEnqObj.fromDate = this.searchPaymentReceiptForm.getRawValue() ? this.searchPaymentReceiptForm.value.fromDate : null
    fltEnqObj.toDate = this.searchPaymentReceiptForm.getRawValue() ? this.searchPaymentReceiptForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'fromDate1'];
    let toDates = ['toDate', 'toDate2'];
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
  public tableAction(event: object) {
    let id = btoa(event['record']['id'])
    let searchfilter = btoa(JSON.stringify(this.filterData))
    if (event['btnAction'] === 'receiptNumber') {
      this.router.navigate(['../view', id], { relativeTo: this.activatedRoute});
      return;
    }
  }
  public pageSizeChange(event: any) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    this.searchSparePaymentReceipt();
  }
  public etSearchDateValueChange(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  public clearForm() {
    this.searchPaymentReceiptForm.reset();
    this.clearSearchFilter()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  clearSearchFilter() {
    this.receiptNumberNgModel = ""
    this.receiptTypeNgModel = ""
    this.receiptModeNgModel = ""
    this.receiptDateNgModel = ""
    this.receiptAmountNgModel = ""
    this.payeeTypeNgModel = ""
    this.customerNameNgModel = ""
    this.customerAddress1NgModel = ""
    this.customerAddress2NgModel = ""
    this.countryNgModel = ""
    this.stateNgModel = ""
    this.districtNgModel = ""
    this.tehsilNgModel = ""
    this.villageNgModel = ""
    this.postOfficeNgModel = ""
    this.pinCodeNgModel = ""
    this.remarkNgModel = ""
    this.checkDDNumberNgModel = ""
    this.checkDDDateNgModel = ""
    this.checkDDBankNgModel = ""
    this.cardTypeNgModel = ""
    this.cardNumberNgModel = ""
    this.cardNameNgModel = ""
    this.transactionNumberNgModel = ""
    this.transactionDateNgModel = ""
  }
}
