import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CurrentStockRoutingModule } from './current-stock-routing.module';
import { CurrentStockPageComponent } from './component/current-stock-page/current-stock-page.component';
import { CreateCurrentStockComponent } from './component/create-current-stock/create-current-stock.component';
import { CurrentStockDetailsComponent } from './component/current-stock-details/current-stock-details.component';
import { CurrentStockSearchPageComponent } from './component/current-stock-search-page/current-stock-search-page.component';
import { CurrentStockSearchComponent } from './component/current-stock-search/current-stock-search.component';
import { SearchCurrentStockComponent } from './component/search-current-stock/search-current-stock.component';
import { CurrentStockService } from './component/search-current-stock/current-stock.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ToastrModule } from 'ngx-toastr';
import { UiModule } from '../../../ui/ui.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';


@NgModule({
  declarations: [CurrentStockPageComponent, CreateCurrentStockComponent,CurrentStockDetailsComponent, CurrentStockSearchComponent, CurrentStockSearchPageComponent, SearchCurrentStockComponent],
  imports: [
    CommonModule,
    CurrentStockRoutingModule,
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
    CurrentStockService,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class CurrentStockModule { }
