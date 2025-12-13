import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActivityEnquiryReportRoutingModule } from './activity-enquiry-report-routing.module';
import { ActivityEnquiryReportComponent } from './activity-enquiry-report.component';
import { MaterialModule } from 'src/app/app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';


@NgModule({
  declarations: [ActivityEnquiryReportComponent],
  imports: [
    CommonModule,
    ActivityEnquiryReportRoutingModule ,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule
  ],
  providers:[
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class ActivityEnquiryReportModule { }
