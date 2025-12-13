import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivityReportRoutingModule } from './service-activity-report-routing.module';
import { ServiceActivityReportCreatePageComponent } from './component/service-activity-report-create-page/service-activity-report-create-page.component';
import { ServiceActivityReportSearchComponent } from './component/service-activity-report-search/service-activity-report-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceActivityReportDetailsComponent } from './component/service-activity-report-details/service-activity-report-details.component';
import { ServiceMachineDetailsComponent } from './component/service-machine-details/service-machine-details.component';
import { ActivityReportJobCardDetailsComponent } from './component/activity-report-job-card-details/activity-report-job-card-details.component';
import { ServiceActivityReportUploadFileComponent } from './component/service-activity-report-upload-file/service-activity-report-upload-file.component';
import { ServiceActivityReportSearchPageComponent } from './component/service-activity-report-search-page/service-activity-report-search-page.component';
import { ToastrModule } from 'ngx-toastr';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { EditableTableModule } from 'editable-table';
import { ServiceActivityReportCommonWebService } from './service/service-activity-report-common-web.service';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [ServiceActivityReportCreatePageComponent, ServiceActivityReportSearchComponent, ServiceActivityReportDetailsComponent, ServiceMachineDetailsComponent, ActivityReportJobCardDetailsComponent, ServiceActivityReportUploadFileComponent, ServiceActivityReportSearchPageComponent],
  imports: [
    CommonModule,
    ServiceActivityReportRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule
  ],
  providers:[ServiceActivityReportCommonWebService,
    { provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}]
})
export class ServiceActivityReportModule { }
