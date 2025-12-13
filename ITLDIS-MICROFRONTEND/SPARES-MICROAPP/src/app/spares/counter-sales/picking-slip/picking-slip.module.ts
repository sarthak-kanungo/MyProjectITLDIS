import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PickingSlipRoutingModule } from './picking-slip-routing.module';
import { PickingSlipContainerComponent } from './container/picking-slip-container/picking-slip-container.component';
import { PickingSlipComponent } from './component/picking-slip/picking-slip.component';
import { ItemDetailsTableComponent } from './component/item-details-table/item-details-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule, DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { PickingSlipSearchComponent } from './container/picking-slip-search/picking-slip-search.component';
import { SearchPickingSlipComponent } from './component/search-picking-slip/search-picking-slip.component';
import { SearchPickingSlipTableComponent } from './component/search-picking-slip-table/search-picking-slip-table.component';


@NgModule({
  declarations: [PickingSlipContainerComponent, PickingSlipComponent, ItemDetailsTableComponent, PickingSlipSearchComponent, SearchPickingSlipComponent, SearchPickingSlipTableComponent],
  imports: [
    CommonModule,
    PickingSlipRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule
  ]
})
export class PickingSlipModule { }
