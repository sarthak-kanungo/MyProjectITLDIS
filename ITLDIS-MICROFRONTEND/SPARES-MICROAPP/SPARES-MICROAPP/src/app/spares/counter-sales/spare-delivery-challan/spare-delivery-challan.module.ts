import { MaterialModule } from './../../../app.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpareDeliveryChallanRoutingModule } from './spare-delivery-challan-routing.module';
import { DeliveryChallanContainerComponent } from './container/delivery-challan-container/delivery-challan-container.component';
import { DeliveryChallanSearchContainerComponent } from './container/delivery-challan-search-container/delivery-challan-search-container.component';
import { SpareDeliveryChallanSearchComponent } from './component/spare-delivery-challan-search/spare-delivery-challan-search.component';
import { SpareDeliveryChallanComponent } from './component/spare-delivery-challan/spare-delivery-challan.component';
import { DeliveryChallanItemDetailsComponent } from './component/delivery-challan-item-details/delivery-challan-item-details.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';


@NgModule({
  declarations: [DeliveryChallanContainerComponent, DeliveryChallanSearchContainerComponent, SpareDeliveryChallanSearchComponent, SpareDeliveryChallanComponent, DeliveryChallanItemDetailsComponent],
  imports: [
    CommonModule,
    SpareDeliveryChallanRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule
  ]
})
export class SpareDeliveryChallanModule { }
