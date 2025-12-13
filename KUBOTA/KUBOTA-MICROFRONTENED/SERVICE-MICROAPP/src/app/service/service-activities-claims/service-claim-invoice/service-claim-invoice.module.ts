import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceClaimInvoiceRoutingModule } from './service-claim-invoice-routing.module';
import { ServiceClaimInvoiceCreateComponent } from './pages/service-claim-invoice-create/service-claim-invoice-create.component';
import { ServiceClaimInvoiceSearchComponent } from './pages/service-claim-invoice-search/service-claim-invoice-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceClaimInvoiceDetailsComponent } from './component/service-claim-invoice-details/service-claim-invoice-details.component';
import { ClaimDetailsComponent } from './component/claim-details/claim-details.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { DealerAndKaiUploadInvoiceComponent } from './pages/dealer-and-kai-upload-invoice/dealer-and-kai-upload-invoice.component';
import { DealerKaiInvoiceVerifyComponent } from './pages/dealer-kai-invoice-verify/dealer-kai-invoice-verify.component';


@NgModule({
  declarations: [ServiceClaimInvoiceCreateComponent, ServiceClaimInvoiceSearchComponent, ServiceClaimInvoiceDetailsComponent, ClaimDetailsComponent],
  imports: [
    CommonModule,
    ServiceClaimInvoiceRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
  ],
  providers:[{ provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class ServiceClaimInvoiceModule { }
