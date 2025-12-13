import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { CustomerSatisfactionReportComponent } from './customer-satisfaction-report/customer-satisfaction-report.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { EditableTableModule } from 'editable-table';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { MaterialModule } from 'src/app/app.module';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { UiModule } from 'src/app/ui/ui.module';
import { MonthlyFailureCodeSummaryReportComponent } from './monthly-failure-code-summary-report/monthly-failure-code-summary-report.component';


@NgModule({
  declarations: [CustomerSatisfactionReportComponent, MonthlyFailureCodeSummaryReportComponent],
  imports: [
    CommonModule,
    ReportRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class ReportModule { }
