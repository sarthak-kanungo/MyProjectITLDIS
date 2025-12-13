import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchSurveySummaryReportComponent } from './component/search-survey-summary-report/search-survey-summary-report.component';
import { SurveySummaryReportSearchResultComponent } from './component/survey-summary-report-search-result/survey-summary-report-search-result.component';
import { SurveySummaryReportRoutingModule } from './survey-summary-report-routing.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';


@NgModule({
  declarations: [SearchSurveySummaryReportComponent,SurveySummaryReportSearchResultComponent,],
  imports: [
    CommonModule,
    SurveySummaryReportRoutingModule,
    NgswSearchTableModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
  ],
  providers:[
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class SurveySummaryReportModule { }
