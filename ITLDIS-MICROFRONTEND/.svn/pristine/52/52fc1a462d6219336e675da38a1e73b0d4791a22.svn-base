import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentReceiptRoutingModule } from './payment-receipt-routing.module';
import { PaymentRecieptCreateComponent } from './pages/payment-reciept-create/payment-reciept-create.component';
import { PaymentRecieptSearchComponent } from './pages/payment-reciept-search/payment-reciept-search.component';
import { PaymentRecieptComponent } from './component/payment-reciept/payment-reciept.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchPaymentRecieptComponent } from './component/search-payment-reciept/search-payment-reciept.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { SearchPaymentRecieptService } from './component/search-payment-reciept/search-payment-reciept.service';


@NgModule({
  declarations: [PaymentRecieptCreateComponent, PaymentRecieptSearchComponent, PaymentRecieptComponent, SearchPaymentRecieptComponent],
  imports: [
    CommonModule,
    PaymentReceiptRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule
  ],
  providers: [{
    provide: DateAdapter, useClass: AppDateAdapter
  },
  {
    provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
  },
    SearchPaymentRecieptService
  ]

})
export class PaymentReceiptModule { }
