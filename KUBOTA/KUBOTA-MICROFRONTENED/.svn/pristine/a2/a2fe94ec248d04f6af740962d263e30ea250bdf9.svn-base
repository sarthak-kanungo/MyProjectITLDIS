import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingattendanceTrainingReportRoutingModule } from './attendance-training-report-routing.module';
import { TrainingattendanceTrainingReportCreatePageComponent } from './component/attendance-training-report-create-page/attendance-training-report-create-page.component';
import { TrainingattendanceTrainingReportSearchPageComponent } from './component/attendance-training-report-search-page/attendance-training-report-search-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { AttendanceTrainingReportSearchTableComponent } from './component/attendance-training-report-search-table/attendance-training-report-search-table.component';

@NgModule({
  declarations: [TrainingattendanceTrainingReportCreatePageComponent, TrainingattendanceTrainingReportSearchPageComponent, AttendanceTrainingReportSearchTableComponent],
  imports: [
    CommonModule,
    TrainingattendanceTrainingReportRoutingModule,
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
export class TrainingattendanceTrainingReportModule { }
