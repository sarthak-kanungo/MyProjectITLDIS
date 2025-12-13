import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { CancelInvoiceFormApiService } from './cancel-invoice-form-api.service';
import { map } from 'rxjs/operators';
import { SelectListAdapter } from '../../../../../core/model/select-list.model';
import { InvoiceStoreService } from '../../invoice-store.service';

@Injectable()
export class CancelInvoiceFormService {

  invoiceCancellationFrom: FormGroup

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private cancelInvoiceFormApiService: CancelInvoiceFormApiService,
    private selectListAdapter: SelectListAdapter,
    private invoiceStore: InvoiceStoreService
  ) { }

  createInvoiceCancellationFrom() {
    return this.invoiceStore.getInvoiceCancellationFrom;
  }
  getInvoiceCancellationType() {
    let keyMap = {
      id: 'id',
      value: 'invoiceCancellationType'
    };
    return this.cancelInvoiceFormApiService.getInvoiceCancellationType().pipe(
      map(res => {
        return this.selectListAdapter.adapt(res.result, keyMap);
      })
    );
  }
  getInvoiceCancellationReason() {
    let keyMap = {
      id: 'id',
      value: 'reason'
    };
    return this.cancelInvoiceFormApiService.getInvoiceCancellationReason().pipe(
      map(res => {
        return this.selectListAdapter.adapt(res.result, keyMap);
      })
    );
  }
  getBrand() {
    let keyMap = {
      // id: 'id',
      value: 'brand'
    };
    return this.cancelInvoiceFormApiService.getBrand().pipe(
      map(res => {
        return this.selectListAdapter.adapt(res.result, keyMap);
      })
    );
  }
  getInvoiceCancellationOtherReason() {
    let keyMap = {
      id: 'id',
      value: 'otherReason'
    };
    return this.cancelInvoiceFormApiService.getInvoiceCancellationOtherReason().pipe(
      map(res => {
        return this.selectListAdapter.adapt(res.result, keyMap);
      })
    );
  }
}
