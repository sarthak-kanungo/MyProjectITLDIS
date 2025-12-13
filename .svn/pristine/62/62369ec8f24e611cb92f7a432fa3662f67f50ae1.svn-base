import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerOrderRoutingModule } from './customer-order-routing.module';

import { CustomerOrderComponent } from './component/customer-order/customer-order.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SoPageComponent } from './component/so-page/so-page.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { SoSearchPageComponent } from './component/so-search-page/so-search-page.component';
import { SoSearchComponent } from './component/so-search/so-search.component';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { ItemDetailsTableComponent } from './component/item-details-table/item-details-table.component';
import { UiModule } from '../../../ui/ui.module';




@NgModule({
  declarations: [

    CustomerOrderComponent,
    SoPageComponent,
    SoSearchPageComponent,
    SoSearchComponent,
    ItemDetailsTableComponent,
  ],
  imports: [
    CommonModule,
    CustomerOrderRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  exports: [
    ItemDetailsTableComponent
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class CustomerOrderModule { }
