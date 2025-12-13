import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InvoiceRoutingModule } from './invoice-routing.module';
import { InvoiceCreateComponent } from './pages/invoice-create/invoice-create.component';
import { InvoiceSearchComponent } from './pages/invoice-search/invoice-search.component';
import { DeliveryChallansComponent } from './component/delivery-challans/delivery-challans.component';
import { InvoiceComponent } from './component/invoice/invoice.component';
import { InvoiceSearchTableComponent } from './component/invoice-search-table/invoice-search-table.component';
import { MaterialDetailsChargesComponent } from './component/material-details-charges/material-details-charges.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchInvoiceComponent } from './component/search-invoice/search-invoice.component';
import { InvoiceContainerComponent } from './component/invoice-container/invoice-container.component';
import { DeliveryChallansContainerComponent } from './component/delivery-challans-container/delivery-challans-container.component';
import { MaterialDetailsChargesContainerComponent } from './component/material-details-charges-container/material-details-charges-container.component';
import { InvoiceProspectDetailsComponent } from './component/invoice-prospect-details/invoice-prospect-details.component';
import { InvoiceProspectDetailsContainerComponent } from './component/invoice-prospect-details-container/invoice-prospect-details-container.component';
import { InvoiceInsuranceDetailsComponent } from './component/invoice-insurance-details/invoice-insurance-details.component';
import { InvoiceInsuranceDetailsContainerComponent } from './component/invoice-insurance-details-container/invoice-insurance-details-container.component';
import { CancelInvoiceFormComponent } from './component/cancel-invoice-form/cancel-invoice-form.component';
import { CancelInvoiceFormContainerComponent } from './component/cancel-invoice-form-container/cancel-invoice-form-container.component';
import { InvoiceSearchTableContainerComponent } from './component/invoice-search-table-container/invoice-search-table-container.component';
import { InvoiceStoreService } from './invoice-store.service';
import { EditableTableModule } from 'editable-table';
import { InvoiceAccessoryDetailComponent } from './component/invoice-accessory-detail/invoice-accessory-detail.component';
import { SaveInvoiceAdaptorService } from './model/save-invoice-adaptor.service';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [
    InvoiceCreateComponent,
    InvoiceSearchComponent,
    DeliveryChallansComponent,
    InvoiceComponent,
    InvoiceSearchTableComponent,
    MaterialDetailsChargesComponent,
    SearchInvoiceComponent,
    InvoiceContainerComponent,
    DeliveryChallansContainerComponent,
    MaterialDetailsChargesContainerComponent,
    InvoiceProspectDetailsComponent,
    InvoiceProspectDetailsContainerComponent,
    InvoiceInsuranceDetailsComponent,
    InvoiceInsuranceDetailsContainerComponent,
    CancelInvoiceFormComponent,
    CancelInvoiceFormContainerComponent,
    InvoiceSearchTableContainerComponent,
    InvoiceAccessoryDetailComponent
  ],
  imports: [
    CommonModule,
    InvoiceRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    EditableTableModule,
    NgswSearchTableModule,
    UiModule
  ],
  exports: [
    InvoiceComponent,
    DeliveryChallansComponent,
    MaterialDetailsChargesComponent,
    InvoiceContainerComponent,
    DeliveryChallansContainerComponent,
    MaterialDetailsChargesContainerComponent,
    InvoiceProspectDetailsComponent,
    InvoiceProspectDetailsContainerComponent,
    InvoiceInsuranceDetailsComponent,
    InvoiceInsuranceDetailsContainerComponent,
    CancelInvoiceFormComponent,
    CancelInvoiceFormContainerComponent
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class InvoiceModule { }
