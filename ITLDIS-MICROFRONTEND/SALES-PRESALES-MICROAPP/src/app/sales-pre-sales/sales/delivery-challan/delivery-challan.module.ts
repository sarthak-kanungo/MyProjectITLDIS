import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from '../../../app.module';
import { APP_DATE_FORMATS, AppDateAdapter } from '../../../date.adapter';
import { DeliveryChallanRoutingModule } from './delivery-challan-routing.module';
import { DeliveryChallanComponent } from './component/delivery-challan/delivery-challan.component';
import { DeliveryChallanCreateService } from './pages/delivery-challan-create/delivery-challan-create.service';
import { DeliverableChecklistComponent } from './component/deliverable-checklist/deliverable-checklist.component';
import { DeliveryChallanCreateComponent } from './pages/delivery-challan-create/delivery-challan-create.component';
import { DeliveryChallanSearchComponent } from './pages/delivery-challan-search/delivery-challan-search.component';
import { SearchDeliveryChallanComponent } from './component/search-delivery-challan/search-delivery-challan.component';
import { DeliveryChallanCancelComponent } from './component/delivery-challan-cancel/delivery-challan-cancel.component';
import { ImplementsAndAccessoriesComponent } from './component/implements-and-accessories/implements-and-accessories.component';
import { DeliveryProspectDetailsComponent } from './component/delivery-prospect-details/delivery-prospect-details.component';
import { DeliveryInsuranceDetailsComponent } from './component/delivery-insurance-details/delivery-insurance-details.component';
import { EditableTableModule } from 'editable-table';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [
    ImplementsAndAccessoriesComponent,
    DeliveryInsuranceDetailsComponent,
    DeliveryProspectDetailsComponent,
    DeliveryChallanSearchComponent,
    SearchDeliveryChallanComponent,
    DeliveryChallanCancelComponent,
    DeliveryChallanCreateComponent,
    DeliverableChecklistComponent,
    DeliveryChallanComponent,
  ],
  imports: [
    CommonModule,
    DeliveryChallanRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    EditableTableModule,
    NgswSearchTableModule,
    UiModule
  ],
  exports: [
    DeliveryChallanComponent,
    DeliverableChecklistComponent,
    DeliveryChallanCancelComponent,
    DeliveryProspectDetailsComponent,
    ImplementsAndAccessoriesComponent,
    DeliveryInsuranceDetailsComponent
  ],
  providers: [
    DeliveryChallanCreateService,
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
  ]
})
export class DeliveryChallanModule { }
