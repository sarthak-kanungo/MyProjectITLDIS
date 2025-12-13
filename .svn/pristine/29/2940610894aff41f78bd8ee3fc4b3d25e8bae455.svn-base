import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { BehaviorSubject } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { InvoiceApiService } from './invoice-api.service';
import { CustomerByCustomerCode } from '../../model/invoice-customer-detail-by-customer-code-adaptor.service';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InvoiceDcByCustomerCodeAdaptorService, InvoiceDcByCustomerCode } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { CustomValidators } from '../../../../../utils/custom-validators';


@Injectable()
export class InvoiceService {

  isEdit: boolean;
  private invoiceTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  private hypothecation$ = new BehaviorSubject<SelectList[]>(undefined);
  private customerCodeList$ = new BehaviorSubject<SelectList[]>(undefined);
  private customerDetail$: BehaviorSubject<CustomerByCustomerCode>;

  invoiceForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private invoiceApiService: InvoiceApiService,
    private invoiceStoreService: InvoiceStoreService,
    private invoiceDcByCustomerCodeAdaptorService: InvoiceDcByCustomerCodeAdaptorService
  ) {
    this.customerDetail$ = this.invoiceStoreService.customerDetail$;
  }

  createInvoiceForm() {
    this.invoiceForm = this.fb.group({
      invoiceType: [null, Validators.compose([Validators.required])],
      invoiceNumber: [null],
      invoiceDate: [{ value: null, disabled: true }],
      customerCode: [null, Validators.compose([Validators.required])],
      customerName: [{ value: null, disabled: true }],
      invoiceStatus: [{ value: null, disabled: true }],
      hypothecation: [null],
      id:[null]
    })
    this.invoiceForm.get('hypothecation').valueChanges.subscribe(val => {
      if (!!val || `${ val }` === '0') {
        this.searchHypothecation(val);
      }
    })
    this.invoiceForm.get('customerCode').valueChanges.subscribe(val => {
      if(this.invoiceForm.get('invoiceType').value){
        if (val !== null) {
          this.invoiceForm.get('customerCode').setErrors({
            selectFromList: 'Please select from list',
          });
        }
        if (!!val || `${ val }` === '0') {
          this.searchCustomerCode(val, this.invoiceForm.get('invoiceType').value);
        }
      }else{
        this.invoiceForm.get('invoiceType').setErrors(Validators.compose([Validators.required]));
        this.invoiceForm.get('invoiceType').markAsTouched();
      }
    });
    this.invoiceStoreService.saveInvoiceForm(this.invoiceForm)
    return this.invoiceForm;
  }
  getInvoiceType() {
    this.invoiceApiService.getInvoiceType().subscribe(res => {
      this.invoiceTypeList$.next(res.result);
    })
    return this.invoiceTypeList$;
  }
  getHypothecationList() {
    return this.hypothecation$;
  }
  getCustomerCodeList() {
    return this.customerCodeList$;
  }
  getCustomerDetail() {
    return this.customerDetail$;
  }
  searchHypothecation(seachValue: string) {
    this.invoiceApiService.searchByBankName(seachValue).subscribe(res => {
      this.hypothecation$.next(res.result);
    })
  }
  searchCustomerCode(seachValue: string, invoiceType:string) {
    this.invoiceApiService.searchCustomerCode(seachValue, invoiceType).subscribe(res => {
      this.customerCodeList$.next(res.result);
    })
  }
  getCustomerDetailsByCustomerCode(customerCode) {
    this.invoiceApiService.getCustomerDetailsByCustomerCode(customerCode).subscribe(
      (res) => {
        if (!res) {
          return;
        }
        let customerDetail = { ...res.result }
        this.storeCustomerDetailIntoInvoiceStore(customerDetail);
        this.patchValueToInvoiceForm(customerDetail);
      }
    );
  }
  patchValueToInvoiceForm(customerDetail) {
    if (customerDetail) {
      this.invoiceForm.patchValue({
        customerName: customerDetail.customerName,
        customerCode: customerDetail.customerCode ? { code: customerDetail.customerCode } : null
      }, {
        emitEvent: false
      });
    }
  }
  storeCustomerDetailIntoInvoiceStore(customerDetail) {
    this.customerDetail$.next(customerDetail);
    this.patchValueToInvoiceForm(customerDetail);
  }
  getDcByCustomerCode(customerCode) {
    this.invoiceApiService.getDcByCustomerCode(customerCode).subscribe(
      (res) => {
        if (!res) {
          return;
        }
        this.storeDCIntoInvoiceStore(res.result);
      }
    );
  }
  storeDCIntoInvoiceStore(dcList) {
    let modifiedDCList = this.invoiceDcByCustomerCodeAdaptorService.adapt<InvoiceDcByCustomerCode[]>(dcList);
    this.invoiceStoreService.dcByCustomerCode$.next(modifiedDCList);
    this.invoiceStoreService.resetMaterialAndAccessoryTable();
  }
}
