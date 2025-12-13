import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SalesInvoiceCancellationRoutingModule } from './sales-invoice-cancellation-routing.module';
import { SalesInvoiceCancellationContainerCreateComponent } from './container/sales-invoice-cancellation-container-create/sales-invoice-cancellation-container-create.component';
import { SalesInvoiceCancellationContainerSearchComponent } from './container/sales-invoice-cancellation-container-search/sales-invoice-cancellation-container-search.component';
import { SparePartsSalesReturnInvoiceComponent } from './component/spare-parts-sales-return-invoice/spare-parts-sales-return-invoice.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule, DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { SalesInvoiceCancelComponent } from './component/sales-invoice-cancel/sales-invoice-cancel.component';
import { SalesInvoiceCancelItemDetailsComponent } from './component/sales-invoice-cancel-item-details/sales-invoice-cancel-item-details.component';


@NgModule({
  declarations: [SalesInvoiceCancellationContainerCreateComponent, SalesInvoiceCancellationContainerSearchComponent, SparePartsSalesReturnInvoiceComponent, SalesInvoiceCancelComponent, SalesInvoiceCancelItemDetailsComponent],
  imports: [
    CommonModule,
    SalesInvoiceCancellationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule
  ]
})
export class SalesInvoiceCancellationModule { }
