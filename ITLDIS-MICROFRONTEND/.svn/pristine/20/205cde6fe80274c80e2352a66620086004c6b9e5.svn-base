import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackOrderCancellationApprovalRoutingModule } from './back-order-cancellation-approval-routing.module';
import { SearchBackOrderCancellationApprovalComponent } from './component/search-back-order-cancellation-approval/search-back-order-cancellation-approval.component';
import { BackOrderCancellationApprovalPageComponent } from './component/back-order-cancellation-approval-page/back-order-cancellation-approval-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { CreateBackOrderCancellationComponent } from './component/create-back-order-cancellation/create-back-order-cancellation.component';
import { ItemDetailsBackOrderApprovalComponent } from './component/item-details-back-order-approval/item-details-back-order-approval.component';


@NgModule({
  declarations: [SearchBackOrderCancellationApprovalComponent, BackOrderCancellationApprovalPageComponent, CreateBackOrderCancellationComponent, ItemDetailsBackOrderApprovalComponent],
  imports: [
    CommonModule,
    BackOrderCancellationApprovalRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    NgswSearchTableModule,
    ToastrModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class BackOrderCancellationApprovalModule { }
