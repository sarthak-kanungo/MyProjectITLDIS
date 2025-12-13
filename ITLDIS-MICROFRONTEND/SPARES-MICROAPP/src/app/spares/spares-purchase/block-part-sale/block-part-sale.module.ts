import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlockPartSaleRoutingModule } from './block-part-sale-routing.module';
import { BlockPartSaleSearchComponent } from './component/block-part-sale-search/block-part-sale-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { UiModule } from 'src/app/ui/ui.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [BlockPartSaleSearchComponent],
  imports: [
    CommonModule,
    BlockPartSaleRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    EditableTableModule,
    UiModule,
    FormsModule,      
    HttpClientModule 
  ]
})
export class BlockPartSaleModule { }
