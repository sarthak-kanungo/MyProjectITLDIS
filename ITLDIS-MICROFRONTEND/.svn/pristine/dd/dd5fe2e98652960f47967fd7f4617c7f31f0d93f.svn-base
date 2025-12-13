import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotlineReportRoutingModule } from './hotline-report-routing.module';
import { HotlineReportComponent } from './component/hotline-report/hotline-report.component';
import { HotlineReportCreatePageComponent } from './component/hotline-report-create-page/hotline-report-create-page.component';
import { HotlineReportSearchPageComponent } from './component/hotline-report-search-page/hotline-report-search-page.component';
import { HotlineReportMachineDetailsComponent } from './component/hotline-report-machine-details/hotline-report-machine-details.component';
import { HotlineReportPartDetailsComponent } from './component/hotline-report-part-details/hotline-report-part-details.component';
import { HotlineReportVendorResponseComponent } from './component/hotline-report-vendor-response/hotline-report-vendor-response.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HotlineReportSearchComponent } from './component/hotline-report-search/hotline-report-search.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { HotlineReportUploadFileComponent } from './component/hotline-report-upload-file/hotline-report-upload-file.component';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationBoxModule } from 'src/app/confirmation-box/confirmation-box.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';

@NgModule({
  declarations: [HotlineReportComponent, HotlineReportCreatePageComponent, HotlineReportSearchPageComponent, HotlineReportMachineDetailsComponent, HotlineReportPartDetailsComponent, HotlineReportVendorResponseComponent, HotlineReportSearchComponent, HotlineReportUploadFileComponent],
  imports: [
    CommonModule,
    HotlineReportRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    ConfirmationBoxModule,
   
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class HotlineReportModule { }
