import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PartReturnRoutingModule } from './part-return-routing.module';
import { PartReturnPageComponent } from './component/part-return-page/part-return-page.component';
import { PartReturnSearchPageComponent } from './component/part-return-search-page/part-return-search-page.component';
import { PartReturnComponent } from './component/part-return/part-return.component';
import { PartReturnSearchComponent } from './component/part-return-search/part-return-search.component';
import { MaterialModule } from '../../../app.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { PartReturnTableComponent } from './component/part-return-table/part-return-table.component';
import {  NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from '../../../ui/ui.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { EditableTableModule } from 'editable-table';


@NgModule({
  declarations: [
    PartReturnPageComponent, 
    PartReturnSearchPageComponent, 
    PartReturnComponent, 
    PartReturnSearchComponent, 
    PartReturnTableComponent
  ],
  imports: [
    CommonModule,
    PartReturnRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    EditableTableModule,
    UiModule
  ],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class PartReturnModule { }
