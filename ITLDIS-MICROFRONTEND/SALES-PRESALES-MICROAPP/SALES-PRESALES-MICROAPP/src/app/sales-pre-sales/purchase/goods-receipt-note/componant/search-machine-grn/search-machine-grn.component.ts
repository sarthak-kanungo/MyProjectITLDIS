import { Component, OnInit, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { DateAdapter, MatDatepickerInput, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchMachineGrnService } from './search-machine-grn.service';
import { DataTable, NgswSearchTableService, ColumnSearch, TableHeading } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { GRNSearchFilter } from './search-machine-grn-dto';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { StorageLoginUser } from 'LoginDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { DateService } from '../../../../../root-service/date.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { AddImplementsContainerService } from '../../../../sales/quotation/component/add-implements-container/add-implements-container.service';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-machine-grn',
  templateUrl: './search-machine-grn.component.html',
  styleUrls: ['./search-machine-grn.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchMachineGrnService, AddImplementsContainerService
  ]
})
export class SearchMachineGrnComponent implements OnInit, AfterViewInit {

  searchMachineGrnForm: FormGroup;
  options = false
  grnTypes = [] as SelectList[];
  suppliers = [] as SelectList[];
  clearSearchRow = new BehaviorSubject<string>("");
  grnstatus = [] as SelectList[];
  actionButtons = [];
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  dataTable: DataTable;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  page: number = 0;
  size: number = 10;
  dmsGrnNumberList: any;
  accPacInvoiceNumberList: any;
  serverCurrentDate: string;
  loginUser: StorageLoginUser;
  clickOnTableFields: Array<TableHeading> = [{ title: 'dmsGrnNumber', isClickable: true }];
  searchFilter: any;
  itemNumberAutoList = []
  searchFlag: boolean = true;

  dmsGrnNumberNgModel: any = "";
  grnDateNgModel: any = "";
  grnTypeNgModel: any = "";
  grnStatusNgModel: any = "";
  invoiceNumberNgModel: any = "";
  invoiceDateNgModel: any = "";
  billToNgModel: any = "";
  shipToNgModel: any = "";
  transporterNameNgModel: any = "";
  driverNameNgModel: any = "";
  driverNumberNgModel: any = "";
  lrNoNgModel: any = "";
  grnByNgModel: any = "";
  grossTotalValueNgModel: any = "";
  transporterVehicleNumberNgModel: any = "";
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  searchFormValues: any;
  key='machinegrn'
  public selectedFromDate: Date;
  pageLoadCount:number=0
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private searchMachineGrnService: SearchMachineGrnService,
    private ngswSearchTableService: NgswSearchTableService,
    private localStorageService: LocalStorageService,
    private dateService: DateService,
    private iFrameService: IFrameService,
    private addImplementsContainerService: AddImplementsContainerService,
    private toastr: ToastrService,
  ) {
    // this.actionButtons.push(this.ngswSearchTableService.addActionButton('edit', 'edit', 'edit'));
    this.loginUser = this.localStorageService.getLoginUser();
  }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem('machinegrn')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchMachineGrnService.getGrnStatus(this.loginUser.id).subscribe(res => {
      this.grnstatus = res.result;
    })
    this.searchMachineGrnService.getGrnType(this.loginUser.id).subscribe(res => {
      this.grnTypes = res.result;
    })
    this.getDateFromServer();
    this.searchMachineGrnForm = this.searchMachineGrnService.createSearchMachineGrnForm();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchMachineGrnForm != null) {
      this.searchMachineGrnForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    // this.searchGrnRecord();

    this.dmsGrnNoValueChanges();
    this.invoiceNoValueChanges();

    this.searchMachineGrnForm.controls.itemNo.valueChanges.subscribe(searchKey => {
      if (searchKey) {
        this.addImplementsContainerService.searchItemNo(searchKey, '', 'PO').subscribe(res => {
          this.itemNumberAutoList = res['result']
        }
        );
      }
    })
    if (this.searchMachineGrnForm.get('fromDate').value == null && this.searchMachineGrnForm.get('toDate').value == null && this.searchMachineGrnForm.get('supplierType').value == null && this.searchMachineGrnForm.get('itemNo').value == null && this.searchMachineGrnForm.get('dmsGrnNumber').value == null && this.searchMachineGrnForm.get('grnStatus').value == null && this.searchMachineGrnForm.get('grnType').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchMachineGrnForm.get('fromDate').patchValue(backDate);
      this.searchMachineGrnForm.get('toDate').patchValue(new Date());
      // this.searchGrnRecord();
    }
    else{
      localStorage.getItem(this.key)
      // this.searchGrnRecord();
    }
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
        if (this.searchMachineGrnForm.get('toDate').value > this.maxDate)
          this.searchMachineGrnForm.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  ngAfterViewInit(): void {
    // this.maxDate = this.newdate
    // let backDate = new Date();
    // backDate.setMonth(this.newdate.getMonth() - 1);
    // this.minDate = backDate;
    // this.searchMachineGrnForm.get('fromDate').patchValue(backDate);
    // this.searchMachineGrnForm.get('toDate').patchValue(new Date());
    // console.log('date----', this.searchMachineGrnForm.get('toDate').patchValue(new Date()))
    
  }
  private getDateFromServer() {
    this.searchMachineGrnService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.serverCurrentDate = dateRes.result;
      }
    });
  }
  private dmsGrnNoValueChanges() {
    this.searchMachineGrnForm.get('dmsGrnNumber').valueChanges.subscribe(val => {
      (val || val === 0) && this.setDmsGrnNumberList(val);
    });
  }
  private invoiceNoValueChanges() {
    this.searchMachineGrnForm.get('invoiceNumber').valueChanges.subscribe(val => {
      (val || val === 0) && this.setInvoiceNoList(val);
    });
  }
  actionOnTableRecord(recordData) {
    if (recordData.btnAction === 'dmsGrnNumber') {
      this.router.navigate(['view/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['edit/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
  }
  pageChangeOfSearchTable(event) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchGrnRecord();
    }
    this.pageLoadCount = 1;
  }
  searchGrnRecord() {
    this.dmsGrnNumberNgModel = "";
    this.grnDateNgModel = "";
    this.grnTypeNgModel = "";
    this.grnStatusNgModel = "";
    this.invoiceNumberNgModel = "";
    this.invoiceDateNgModel = "";
    this.billToNgModel = "";
    this.shipToNgModel = "";
    this.transporterNameNgModel = "";
    this.driverNameNgModel = "";
    this.driverNumberNgModel = "";
    this.lrNoNgModel = "";
    this.grnByNgModel = "";
    this.grossTotalValueNgModel = "";
    this.transporterVehicleNumberNgModel = "";
    this.searchFormValues = this.searchMachineGrnForm.getRawValue();
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
      this.searchFlag = true;
    }
    if (this.checkValidDatesInput()) {
      if (this.searchFlag == true && this.searchMachineGrnForm.get('fromDate').value || this.searchMachineGrnForm.get('toDate').value || this.searchMachineGrnForm.get('invoiceNumber').value || this.searchMachineGrnForm.get('dmsGrnNumber').value || this.searchMachineGrnForm.get('grnStatus').value || this.searchMachineGrnForm.get('grnType').value) {
        localStorage.setItem(this.key, JSON.stringify(this.searchMachineGrnForm.value))
        // this.page = 0;
        // this.size = 10;
        this.searchFormValues['page'] = this.page
        this.searchFormValues['size'] = this.size
      }
      else if (this.searchMachineGrnForm.get('fromDate').value == null && this.searchMachineGrnForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchMachineGrnForm.value))
    const searchField = {
      ...this.searchMachineGrnForm.getRawValue(),
      page: this.page,
      size: this.size
    };
    Object.keys(this.searchFormValues).forEach(key => {

      if (this.searchFormValues[key] === null) {
        delete this.searchFormValues[key];
      } else

        if (this.searchFormValues[key] && (key === 'fromDate') || (key === 'toDate')) {

          this.searchFormValues[key] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues[key]);
        }
    })
    // ObjectUtil.removeNulls(searchField);
    console.log('searchField: ', this.searchFormValues);
    // this.searchFilter = searchField;
    if (this.checkValidDatesInput()) {
      localStorage.setItem(this.key, JSON.stringify(this.searchMachineGrnForm.value))
      this.setSearchResultTable(this.searchFormValues);
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchMachineGrnForm.value as GRNSearchFilter

    fltEnqObj.fromDate = this.searchMachineGrnForm.getRawValue() ? this.searchMachineGrnForm.value.fromDate : null
    fltEnqObj.toDate = this.searchMachineGrnForm.getRawValue() ? this.searchMachineGrnForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
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
  clear() {
    this.searchMachineGrnForm.reset();
    // this.page = 0;
    // this.size = 10;
    // this.searchGrnRecord();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
   
  }
  private setSearchResultTable(searchValue: GRNSearchFilter) {
    if (this.searchFlag == true && searchValue['invoiceNumber'] || searchValue['fromDate'] || searchValue['toDate'] || searchValue['dmsGrnNumber'] || searchValue['grnStatus'] || searchValue['grnType']) {
      this.searchMachineGrnService.searchGrn(searchValue).subscribe(searchRes => {
        this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
        this.totalSearchRecordCount = searchRes['count'];
      });
    }
  }
  private setDmsGrnNumberList(searchValue: string) {
    this.dmsGrnNumberList = this.searchMachineGrnService.searchByDmsGrnNumber(searchValue, this.loginUser.id)
      .pipe(
        map(dmsGrnNoRes => dmsGrnNoRes['result'])
      );
  }
  private setInvoiceNoList(searchValue: string) {
    this.accPacInvoiceNumberList = this.searchMachineGrnService.searchGrnInvoiceNumber(searchValue, this.loginUser.id)
      .pipe(
        map(accPacInvoiceNoRes => accPacInvoiceNoRes['result'])
      );
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    
    if (event && event['fromDate']) {
      event['fromDate'] = new Date(event['fromDate']);
      this.selectedFromDate = new Date(event['fromDate'])
    }
    if (event && event['toDate']) event['toDate'] = new Date(event['toDate']);
    this.searchMachineGrnForm.patchValue(event);

    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    // this.searchMachineGrnForm.patchValue({
    //   fromDate: this.dateService.getDateIntoYYYYMMDD(event.fromDate) ? this.dateService.getDateIntoYYYYMMDD(new Date(event.fromDate)) : null,
    //   toDate: this.dateService.getDateIntoYYYYMMDD(event.toDate) ? this.dateService.getDateIntoYYYYMMDD(new Date(event.toDate)) : null,
    // });
    // this.searchGrnRecord();
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
}
