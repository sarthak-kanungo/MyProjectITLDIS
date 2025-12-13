import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';

import { NgswSearchTableModule } from 'ngsw-search-table';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { SparePaymentReceiptRoutingModule } from './spare-payment-receipt-routing.module';
import { PayeeDetailsComponent } from './component/payee-details/payee-details.component';
import { PaymentDetailComponent } from './component/payment-detail/payment-detail.component';
import { MaterialModule, DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { SparePaymentReceiptComponent } from './component/spare-payment-receipt/spare-payment-receipt.component';
import { SparePaymentReceiptPageComponent } from './component/spare-payment-receipt-page/spare-payment-receipt-page.component';
import { SparePaymentReceiptPagePresenter } from './component/spare-payment-receipt-page/spare-payment-receipt-page.presenter';
import { SparePaymentReceiptSearchComponent } from './component/spare-payment-receipt-search/spare-payment-receipt-search.component';
import { SparePaymentReceiptSearchPageComponent } from './component/spare-payment-receipt-search-page/spare-payment-receipt-search-page.component';


@NgModule({
  declarations: [SparePaymentReceiptComponent, PayeeDetailsComponent, SparePaymentReceiptSearchComponent, PaymentDetailComponent, SparePaymentReceiptPageComponent, SparePaymentReceiptSearchPageComponent],
  imports: [
    CommonModule,
    SparePaymentReceiptRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule,
    NgswSearchTableModule
  ],
  providers: [
    SparePaymentReceiptPagePresenter,
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class SparePaymentReceiptModule { }
