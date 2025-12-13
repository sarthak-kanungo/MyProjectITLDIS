import { MatDialog } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { SparesSalesInvoiceSearchPageWebService } from './spares-sales-invoice-search-page-web.service';
import { SparesSalesInvoiceSearchPagePresenter } from './spares-sales-invoice-search-page.presenter';
import { BaseDto } from 'BaseDto';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { LoginFormService } from 'src/app/root-service/login-form.service';
@Component({
  selector: 'app-spares-sales-invoice-search-page',
  templateUrl: './spares-sales-invoice-search-page.component.html',
  styleUrls: ['./spares-sales-invoice-search-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparesSalesInvoiceSearchPageWebService, SparesSalesInvoiceSearchPagePresenter]
})
export class SparesSalesInvoiceSearchPageComponent implements OnInit, AfterViewInit {
  readonly searchInvoiceForm: FormGroup;
  public page = 0;
  public size = 10;
  public actionButtons = [];
  public dataTable: DataTable;
  public searchValue: ColumnSearch;
  public totalTableElements: number;
  public filterData: object = {};
  searchFlag: boolean = true;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");
  Sales_Invoice_NumberNgModel: any
  Invoice_DateNgModel: any
  Customer_TypeNgModel: any
  Reference_DocumentNgModel: any
  Reference_Document_DateNgModel: any
  Sales_TypeNgModel: any
  Customer_CodeNgModel: any
  Customer_NameNgModel: any
  Contact_NumberNgModel: any
  Customer_Address1NgModel: any
  Customer_Address2NgModel: any
  PincodeNgModel: any
  VillageNgModel: any
  TehsilNgModel: any
  DistrictNgModel: any
  StateNgModel: any
  jobCardNumberNgModel:any
  wcrNoNgModel:any
  Mode_Of_TransportNgModel: any
  TransporterNgModel: any
  L_R_NoNgModel: any
  chassisNoNgModel: any
  engineNoNgModel: any
  public todaysDate: Date = new Date();
  minDate: Date;
  maxDate: Date
  key='spare-sales-invoice';
  usertype:string
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toasterService: ToastrService,
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private tableDataService: NgswSearchTableService,
    private sparesSalesInvoiceSearchPageWebService: SparesSalesInvoiceSearchPageWebService,
    private sparesSalesInvoiceSearchPagePresenter: SparesSalesInvoiceSearchPagePresenter,
    private toastr: ToastrService,
    private loginService: LoginFormService
  ) {
    this.searchInvoiceForm = this.sparesSalesInvoiceSearchPagePresenter.searchInvoiceForm;
  }

  ngOnInit() {
    this.usertype = this.loginService.getLoginUserType();
    this.getFilterState();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchInvoiceForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.searchInvoiceForm.get('salesInvoice').value==null && this.searchInvoiceForm.get('referenceDocument').value==null && this.searchInvoiceForm.get('salesType').value==null && this.searchInvoiceForm.get('modeOfTransport').value==null && this.searchInvoiceForm.get('transporter').value==null && this.searchInvoiceForm.get('fromDate').value==null && this.searchInvoiceForm.get('toDate').value==null) {
      this.maxDate = this.todaysDate
      let backDate = new Date();
      backDate.setMonth(this.todaysDate.getMonth() - 1);
      this.minDate = backDate;
      this.searchInvoiceForm.get('fromDate').patchValue(backDate);
      this.searchInvoiceForm.get('toDate').patchValue(new Date());
      this.searchSparesInvoice();
    }
    else {
      localStorage.getItem(this.key)
      this.searchSparesInvoice();
    }
  }
  private getFilterState() {
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        this.filterData = JSON.parse(queryMap.get('searchFilter'));
        this.page = JSON.parse(queryMap.get('searchFilter')).page;
        this.size = JSON.parse(queryMap.get('searchFilter')).size;
      }
    })
  }
  public searchSparesInvoice() {
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
    const formValues = this.searchInvoiceForm.value;
    console.log('vinay--', formValues);

    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchInvoiceForm.value))
    formValues['page'] = this.page;
    formValues['size'] = this.size;
    formValues['salesInvoiceId'] = formValues['salesInvoice'] && formValues['salesInvoice']['id'] ? formValues['salesInvoice']['id'] : null;
    // formValues['salesInvoiceId'] = formValues.salesInvoice.id ? formValues['salesInvoice']['id'] : null
    this.filterData = this.sparesSalesInvoiceSearchPageWebService.removeNullFromObjectAndFormatDate(formValues);
    this.router.navigate([], { queryParams: { searchFilter: JSON.stringify(this.filterData) } });
    if (this.checkValidDatesInput()) {
      if (this.searchInvoiceForm.get('salesInvoice').value || this.searchInvoiceForm.get('referenceDocument').value || this.searchInvoiceForm.get('wcrNo').value || this.searchInvoiceForm.get('jobCardNumber').value || this.searchInvoiceForm.get('salesType').value || this.searchInvoiceForm.get('modeOfTransport').value || this.searchInvoiceForm.get('transporter').value || this.searchInvoiceForm.get('fromDate').value || this.searchInvoiceForm.get('toDate').value) {
        this.sparesSalesInvoiceSearchPageWebService.searchSparesInvoice(formValues).subscribe((res: BaseDto<Array<object>>) => {
          if (res) {
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count
            this.changeDetectorRef.markForCheck();
          }
        })
      }
      else if (this.searchInvoiceForm.get('fromDate').value == null && this.searchInvoiceForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }

    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchInvoiceForm.value

    fltEnqObj.fromDate = this.searchInvoiceForm.getRawValue() ? this.searchInvoiceForm.value.fromDate : null
    fltEnqObj.toDate = this.searchInvoiceForm.getRawValue() ? this.searchInvoiceForm.value.toDate : null

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
  private openConfirmDialog(invoiceId, referenceDocument): void | any {
    let message = `Are you sure you want to cancel invoice?`
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result.button === 'Confirm') {
        this.cancelInvoice(result.remarkText, invoiceId, referenceDocument);
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }
    return confirmationData;
  }
  public cancelInvoice(remarks: string, invoiceId: string, referenceType: string) {
    this.sparesSalesInvoiceSearchPageWebService.cancelInvoice(remarks, invoiceId, referenceType).subscribe(res => {
      this.toasterService.success(res['message'], "Success");
      
      this.searchSparesInvoice();
    })
  }
  public tableAction(event: object) {
    let id = btoa(event['record']['id'])
    let searchfilter = btoa(JSON.stringify(this.filterData))
    if (event['btnAction'] === 'Sales_Invoice_Number') {
      this.router.navigate(['../view', id], { relativeTo: this.activatedRoute });
      return;
    } else if (event['btnAction'] === 'Cancel') {
      this.openConfirmDialog(event['record']['id'], event['record']['referenceDocument']);
    } else if (event['btnAction'] === 'edit') {
      this.router.navigate(['../edit', id], { relativeTo: this.activatedRoute });
      return;
    }
  }
  public pageSizeChange(event: object) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    this.searchSparesInvoice();
  }
  public etSearchDateValueChange(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  public clearForm() {
    this.searchInvoiceForm.reset();
    // this.searchSparesInvoice();
    this.dataTable = null
    this.clearSearchFilter()
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  ngAfterViewInit() {

  }
  clearSearchFilter() {
    this.Sales_Invoice_NumberNgModel = ""
    this.Invoice_DateNgModel = ""
    this.Customer_TypeNgModel = ""
    this.Reference_DocumentNgModel = ""
    this.Reference_Document_DateNgModel = ""
    this.Sales_TypeNgModel = ""
    this.Customer_CodeNgModel = ""
    this.Customer_NameNgModel = ""
    this.Contact_NumberNgModel = ""
    this.Customer_Address1NgModel = ""
    this.Customer_Address2NgModel = ""
    this.PincodeNgModel = ""
    this.VillageNgModel = ""
    this.TehsilNgModel = ""
    this.DistrictNgModel = ""
    this.StateNgModel = ""
    this.Mode_Of_TransportNgModel = ""
    this.TransporterNgModel = ""
    this.L_R_NoNgModel = ""
    this.chassisNoNgModel = ""
    this.engineNoNgModel = ""
    this.jobCardNumberNgModel = ""
    this.wcrNoNgModel = ""
  }

  generateReport(){
    const formValues = this.searchInvoiceForm.getRawValue();
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    ObjectUtil.removeNulls(formValues);

    if (this.checkValidDatesInput()) {
      if (this.searchInvoiceForm.get('salesInvoice').value || this.searchInvoiceForm.get('referenceDocument').value || this.searchInvoiceForm.get('salesType').value || this.searchInvoiceForm.get('modeOfTransport').value || this.searchInvoiceForm.get('transporter').value || this.searchInvoiceForm.get('fromDate').value || this.searchInvoiceForm.get('toDate').value) {
        this.downloadReport(formValues);
      }
      else if (this.searchInvoiceForm.get('fromDate').value == null && this.searchInvoiceForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }

  private downloadReport(searchObject) {
    this.sparesSalesInvoiceSearchPageWebService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
}
