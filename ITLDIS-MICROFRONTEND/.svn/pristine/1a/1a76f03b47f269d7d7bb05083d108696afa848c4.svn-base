import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MarketingClaimReportComponent } from './marketing-claim-report.component';
import { MarketingClaimReportRoutingModule } from './marketing-claim-report-routing.module';
import { MaterialModule } from 'src/app/app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';



@NgModule({
  declarations: [MarketingClaimReportComponent],
  imports: [
    CommonModule,
    MarketingClaimReportRoutingModule ,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule
  ],
  providers:[
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class MarketingClaimReportModule { }
