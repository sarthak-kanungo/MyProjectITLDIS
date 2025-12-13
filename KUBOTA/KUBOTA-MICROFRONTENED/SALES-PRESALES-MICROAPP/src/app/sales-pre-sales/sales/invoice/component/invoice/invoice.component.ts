import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { InvoiceService } from './invoice.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { InvoiceApiService } from './invoice-api.service';
import { BehaviorSubject } from 'rxjs';
import { CustomerByCustomerCode, InvoiceCustomerDetailByCustomerCodeAdaptorService } from '../../model/invoice-customer-detail-by-customer-code-adaptor.service';
import { InvoiceDcByCustomerCodeAdaptorService } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { InvoiceStoreService } from '../../invoice-store.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    InvoiceService,
    InvoiceApiService,
    InvoiceDcByCustomerCodeAdaptorService,
    InvoiceCustomerDetailByCustomerCodeAdaptorService
  ]
})
export class InvoiceComponent implements OnInit, OnChanges {

  invoicestatus: string[] = [
    'Live', 'Cancelled'
  ];

  reasons: string[] = [
    'Exchange Price', 'Discount', 'Product Unknown', 'Quality'
  ];
  hypothecationList$: BehaviorSubject<SelectList[]>;
  customerCodeList$: BehaviorSubject<SelectList[]>;
  invoiceTypeList$: BehaviorSubject<SelectList[]>;
  invoiceCancellationFrom: FormGroup
  invoiceForm: FormGroup;
  customerDetail$: BehaviorSubject<CustomerByCustomerCode>;
  @Input() customerDetail;
  @Input() deliveryChallanDetail;
  @Input() isCancelInvoice: boolean;
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  @Input() invoiceDetail: object;
  model:string
  constructor(
    private invoiceService: InvoiceService,
    private invoiceStoreService: InvoiceStoreService
  ) { }

  ngOnInit() {
    this.invoiceForm = this.invoiceService.createInvoiceForm();
    if (this.isView || this.isCancelInvoice) {
      this.invoiceForm.disable();
      return;
    }else if (this.isEdit){
       this.invoiceForm.disable();
       this.invoiceForm.controls.invoiceType.enable();
       this.invoiceForm.controls.hypothecation.enable();
       this.invoiceTypeList$ = this.invoiceService.getInvoiceType();
       this.hypothecationList$ = this.invoiceService.getHypothecationList();
       return;
    }
    this.getInitialDataForInvoiceCreation();

  }
  ngOnChanges(changes: SimpleChanges): void {
    if (!!changes.customerDetail && !!changes.customerDetail.currentValue) {
      console.log('changes.customerDetail', changes.customerDetail);
      this.invoiceService.storeCustomerDetailIntoInvoiceStore(changes.customerDetail.currentValue);
    }
    if (!!changes.deliveryChallanDetail && !!changes.deliveryChallanDetail.currentValue) {
      console.log('changes.deliveryChallanDetail', changes.deliveryChallanDetail);
      this.invoiceService.storeDCIntoInvoiceStore(changes.deliveryChallanDetail.currentValue);
    }
    if (!!changes.invoiceDetail && changes.invoiceDetail.currentValue) {
      this.patchValueToInvoiceFormForCancelInvoice(changes.invoiceDetail.currentValue);
      this.invoiceStoreService.invoiceTypeSelectionBehaviour.next(changes.invoiceDetail.currentValue['invoiceType']);
    }
  }
  private patchValueToInvoiceFormForCancelInvoice(invoiceDetail) {
    if (this.invoiceForm) {
      this.invoiceForm.patchValue(invoiceDetail);
      this.invoiceForm.get('hypothecation').patchValue({ value: invoiceDetail.hypothecation || null, id: invoiceDetail.bankId });
      
      const invoiceType = { value: invoiceDetail.invoiceType || null, id: null }
      if(this.isView || this.isCancelInvoice){
          this.invoiceTypeList$ = new BehaviorSubject<SelectList[]>([invoiceType])
      }
      /*this.invoiceForm.get('invoiceType').patchValue(invoiceType);*/
    }
    
  }
  invoiceTypeSelectionChange(event){
    this.invoiceStoreService.invoiceTypeSelectionBehaviour.next(event.value);
  }
  private getInitialDataForInvoiceCreation() {
    this.invoiceTypeList$ = this.invoiceService.getInvoiceType();
    this.hypothecationList$ = this.invoiceService.getHypothecationList();
    this.customerCodeList$ = this.invoiceService.getCustomerCodeList();
    this.customerDetail$ = this.invoiceService.getCustomerDetail();
  }
  displayFn(obj: SelectList): string | number | undefined {
    return obj ? obj.value : undefined;
  }
  displayCodeFn(obj: SelectList): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  hypothecationSelected(event: MatAutocompleteSelectedEvent) {
  }
  customerCodeSelected(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.invoiceForm.get('customerCode').setErrors(null);
    }
    this.invoiceForm.controls.invoiceType.disable();
    this.invoiceService.getCustomerDetailsByCustomerCode(event.option.value.code);
    this.invoiceService.getDcByCustomerCode(event.option.value.code);
  }

}


