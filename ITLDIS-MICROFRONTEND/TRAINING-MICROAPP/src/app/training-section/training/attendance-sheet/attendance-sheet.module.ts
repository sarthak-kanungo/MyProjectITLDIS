import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingattendanceSheetRoutingModule } from './attendance-sheet-routing.module';
import { TrainingattendanceSheetCreatePageComponent } from './component/attendance-sheet-create-page/attendance-sheet-create-page.component';
import { TrainingattendanceSheetSearchPageComponent } from './component/attendance-sheet-search-page/attendance-sheet-search-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { AttendanceSheetSearchTableComponent } from './component/attendance-sheet-search-table/attendance-sheet-search-table.component';

@NgModule({
  declarations: [TrainingattendanceSheetCreatePageComponent, TrainingattendanceSheetSearchPageComponent, AttendanceSheetSearchTableComponent],
  imports: [
    CommonModule,
    TrainingattendanceSheetRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ConfirmationBoxModule,
    ToastrModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class TrainingattendanceSheetModule { }
