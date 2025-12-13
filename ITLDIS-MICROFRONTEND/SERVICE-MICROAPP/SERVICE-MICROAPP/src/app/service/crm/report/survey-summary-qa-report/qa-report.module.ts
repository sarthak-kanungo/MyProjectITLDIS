import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DateAdapter, MAT_DATE_FORMATS } from "@angular/material";
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MaterialModule } from "src/app/app.module";
import { AppDateAdapter, APP_DATE_FORMATS } from "src/app/date.adapter";
import { QaReportPageComponent } from "./component/qa-report-page/qa-report-page.component";
import { QaReportTableComponent } from "./component/qa-report-table/qa-report-table.component";
import { QaReportRoutingModule } from "./qa-report-routing.module";

@NgModule({
    declarations: [QaReportPageComponent,QaReportTableComponent,],
    imports: [
      CommonModule,
      QaReportRoutingModule,
      NgswSearchTableModule,
      FormsModule,
      ReactiveFormsModule,
      MaterialModule,
    ],
    providers: [
      { provide: DateAdapter, useClass: AppDateAdapter },
      { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
  })
  export class QaReportModule { }