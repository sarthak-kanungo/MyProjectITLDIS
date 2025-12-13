import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WcrReportRoutingModule } from './wcr-report-routing.module';
import { WcrReportCreatePageComponent } from './component/wcr-report-create-page/wcr-report-create-page.component';
import { WcrReportSearchPageComponent } from './component/wcr-report-search-page/wcr-report-search-page.component';
import { WcrReportComponent } from './component/wcr-report/wcr-report.component';
import { WcrReportDetailsComponent } from './component/wcr-report-details/wcr-report-details.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { WcrReportSearchComponent } from './component/wcr-report-search/wcr-report-search.component';
import { WcrReportCreditDetailsComponent } from './component/wcr-report-credit-details/wcr-report-credit-details.component';
import { ToastrModule } from 'ngx-toastr';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
@NgModule({
  declarations: [WcrReportCreatePageComponent, WcrReportSearchPageComponent, WcrReportComponent, WcrReportDetailsComponent, WcrReportSearchComponent, WcrReportCreditDetailsComponent],
  imports: [
    CommonModule,
    WcrReportRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class WcrReportModule { }
