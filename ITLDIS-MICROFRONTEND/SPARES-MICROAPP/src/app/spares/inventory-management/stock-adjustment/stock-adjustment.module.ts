import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StockAdjustmentRoutingModule } from './stock-adjustment-routing.module';

import { InventoryAdjustmentComponent } from './component/inventory-adjustment/inventory-adjustment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchInventoryAdjustmentComponent } from './component/search-inventory-adjustment/search-inventory-adjustment.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { StockAdjustItemDetailsComponent } from './component/stock-adjust-item-details/stock-adjust-item-details.component';
import { StockAdjustmentPageComponent } from './component/stock-adjustment-page/stock-adjustment-page.component';
import { SearchStockAdjustmentPageComponent } from './component/search-stock-adjustment-page/search-stock-adjustment-page.component';
import { ToastrModule } from 'ngx-toastr';
import { UiModule } from '../../../ui/ui.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';

@NgModule({
  declarations: [InventoryAdjustmentComponent, SearchInventoryAdjustmentComponent, StockAdjustItemDetailsComponent, StockAdjustmentPageComponent, SearchStockAdjustmentPageComponent],
  imports: [
    CommonModule,
    StockAdjustmentRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,

    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ]
})
export class StockAdjustmentModule { }
