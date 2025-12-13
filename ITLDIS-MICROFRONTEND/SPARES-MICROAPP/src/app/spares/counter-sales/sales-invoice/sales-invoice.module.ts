import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SalesInvoiceRoutingModule } from './sales-invoice-routing.module';
import { MaterialModule, DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { SparesSalesInvoiceCreatePageComponent } from './component/spares-sales-invoice-create-page/spares-sales-invoice-create-page.component';
import { SparesSalesInvoiceSearchPageComponent } from './component/spares-sales-invoice-search-page/spares-sales-invoice-search-page.component';
import { SparesSalesInvoiceSearchComponent } from './component/spares-sales-invoice-search/spares-sales-invoice-search.component';
import { SparesSalesInvoiceComponent } from './component/spares-sales-invoice/spares-sales-invoice.component';
import { SparesSalesInvoiceItemDetailsComponent } from './component/spares-sales-invoice-item-details/spares-sales-invoice-item-details.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';

import { LabourChargesComponent } from './component/labour-charges/labour-charges.component';
import { OutsideJobChargesComponent } from './component/outside-job-charges/outside-job-charges.component';

@NgModule({
  declarations: [
    SparesSalesInvoiceCreatePageComponent,
    SparesSalesInvoiceSearchPageComponent,
    SparesSalesInvoiceSearchComponent,
    SparesSalesInvoiceComponent,
    SparesSalesInvoiceItemDetailsComponent,
    LabourChargesComponent,
    OutsideJobChargesComponent
  ],
  imports: [
    CommonModule,
    SalesInvoiceRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule,
    NgswSearchTableModule,
    EditableTableModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class SalesInvoiceModule { }
