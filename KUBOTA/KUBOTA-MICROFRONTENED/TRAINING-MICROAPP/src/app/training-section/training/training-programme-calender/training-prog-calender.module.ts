import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingProgrammeCalenderRoutingModule } from './training-prog-calender-routing.module';
import { TrainingProgrammeCalenderCreatePageComponent } from './component/training-prog-calender-create-page/training-prog-calender-create-page.component';
import { TrainingProgrammeCalenderSearchPageComponent } from './component/training-prog-calender-search-page/training-prog-calender-search-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { TrainingProgrammeCalenderSearchTableComponent } from './component/training-programme-calender-search-table/training-programme-calender-search-table.component';

@NgModule({
  declarations: [TrainingProgrammeCalenderCreatePageComponent, TrainingProgrammeCalenderSearchPageComponent, TrainingProgrammeCalenderSearchTableComponent],
  imports: [
    CommonModule,
    TrainingProgrammeCalenderRoutingModule,
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
export class TrainingProgrammeCalenderModule { }
