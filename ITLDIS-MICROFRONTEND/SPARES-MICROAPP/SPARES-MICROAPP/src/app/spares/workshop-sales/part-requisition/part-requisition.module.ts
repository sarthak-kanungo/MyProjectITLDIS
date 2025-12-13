import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PartRequisitionRoutingModule } from './part-requisition-routing.module';
import { PartRequisitionSearchComponent } from './component/part-requisition-search/part-requisition-search.component';
import { MaterialModule } from '../../../app.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { PartRequisitionComponent } from './component/part-requisition/part-requisition.component';
import { PartRequisitionItemDetailsComponent } from './component/part-requisition-item-details/part-requisition-item-details.component';
import { PartRequisitionPageComponent } from './component/part-requisition-page/part-requisition-page.component';
import { PartRequisitionSearchPageComponent } from './component/part-requisition-search-page/part-requisition-search-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { UiModule } from '../../../ui/ui.module';
// import { NgswSearchTableModule } from '../../../../../projects/ngsw-search-table/src/public-api';

@NgModule({
  declarations: [
    PartRequisitionPageComponent,
    PartRequisitionSearchPageComponent,
    PartRequisitionSearchComponent,
    PartRequisitionComponent,
    PartRequisitionItemDetailsComponent],
  imports: [
    CommonModule,
    PartRequisitionRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    EditableTableModule,
    NgswSearchTableModule,
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
export class PartRequisitionModule { }
