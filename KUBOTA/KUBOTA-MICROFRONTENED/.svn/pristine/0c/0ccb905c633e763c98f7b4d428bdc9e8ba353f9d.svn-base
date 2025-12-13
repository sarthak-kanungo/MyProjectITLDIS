import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingnominationRoutingModule } from './training-nomination-routing.module';
import { TrainingnominationCreatePageComponent } from './component/training-nomination-create-page/training-nomination-create-page.component';
import { TrainingnominationSearchPageComponent } from './component/training-nomination-search-page/training-nomination-search-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { TrainingNominationSearchTableComponent } from './component/training-nomination-search-table/training-nomination-search-table.component';
import { TrainingProgrammeCalenderModule } from '../training-programme-calender/training-prog-calender.module';

@NgModule({
  declarations: [TrainingnominationCreatePageComponent, TrainingnominationSearchPageComponent, TrainingNominationSearchTableComponent],
  imports: [
    CommonModule,
    TrainingnominationRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ConfirmationBoxModule,
    ToastrModule,
    TrainingProgrammeCalenderModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class TrainingnominationModule { }
