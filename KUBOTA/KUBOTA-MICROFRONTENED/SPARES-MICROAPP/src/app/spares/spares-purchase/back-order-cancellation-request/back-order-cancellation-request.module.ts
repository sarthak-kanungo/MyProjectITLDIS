import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackOrderCancellationRequestRoutingModule } from './back-order-cancellation-request-routing.module';
import { SearchBackOrderCancellationComponent } from './component/search-back-order-cancellation/search-back-order-cancellation.component';
import { CreateBackOrderCancellationComponent } from './component/create-back-order-cancellation/create-back-order-cancellation.component';
import { CreateBackOrderCancellationPageComponent } from './component/create-back-order-cancellation-page/create-back-order-cancellation-page.component';
import { ItemDetailsBackOrderCancellationComponent } from './component/item-details-back-order-cancellation/item-details-back-order-cancellation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';


@NgModule({
  declarations: [SearchBackOrderCancellationComponent, CreateBackOrderCancellationComponent, CreateBackOrderCancellationPageComponent, ItemDetailsBackOrderCancellationComponent],
  imports: [
    CommonModule,
    BackOrderCancellationRequestRoutingModule,
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
export class BackOrderCancellationRequestModule { }
