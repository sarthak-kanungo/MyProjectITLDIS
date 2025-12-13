import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlocksMachineSaleRoutingModule } from './blocks-machine-sale-routing.module';
import { BlockMachineSaleSearchComponent } from './component/block-machine-sale-search/block-machine-sale-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { UiModule } from 'src/app/ui/ui.module';

@NgModule({
  declarations: [BlockMachineSaleSearchComponent],
  imports: [
    CommonModule,
    BlocksMachineSaleRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    EditableTableModule,
    UiModule,FormsModule      
  ]
})
export class BlocksMachineSaleModule { }
