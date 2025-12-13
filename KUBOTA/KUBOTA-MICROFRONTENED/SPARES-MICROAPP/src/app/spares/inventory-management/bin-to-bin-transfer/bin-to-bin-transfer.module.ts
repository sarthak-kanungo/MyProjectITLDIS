import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BinToBinTransferRoutingModule } from './bin-to-bin-transfer-routing.module';
import { CreateBintobinTransferComponent } from './component/create-bintobin-transfer/create-bintobin-transfer.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { BinItemDetailsComponent } from './component/bin-item-details/bin-item-details.component';
import { SearchBontobinTransferComponent } from './component/search-bontobin-transfer/search-bontobin-transfer.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { BintobinTransferSearchService } from './component/search-bontobin-transfer/bintobin-transfer-search.service';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { BtbtPageComponent } from './component/btbt-page/btbt-page.component';
import { BtbtSearchPageComponent } from './component/btbt-search-page/btbt-search-page.component';
import { BtbtSearchComponent } from './component/btbt-search/btbt-search.component';

import { ToastrModule } from 'ngx-toastr';
import { UiModule } from '../../../ui/ui.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
@NgModule({
  declarations: [CreateBintobinTransferComponent, BinItemDetailsComponent, SearchBontobinTransferComponent, BtbtPageComponent, BtbtSearchPageComponent, BtbtSearchComponent],
  imports: [
    CommonModule,
    BinToBinTransferRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,

    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  providers: [
    BintobinTransferSearchService,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class BinToBinTransferModule { }
