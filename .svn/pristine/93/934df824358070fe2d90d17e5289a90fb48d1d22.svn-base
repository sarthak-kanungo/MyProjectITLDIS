import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ServiceMonitoringBoardComponent } from './service-monitoring-board/service-monitoring-board.component';
import { InstallationMonitoringBoardComponent } from './installation-monitoring-board/installation-monitoring-board.component';
import { MaterialModule } from 'src/app/app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { UiModule } from 'src/app/ui/ui.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { FillRatioReportComponent } from './fill-ratio-report/fill-ratio-report.component';
import { CustomerMachineMasterComponent } from './customer-machine-master/customer-machine-master.component';

@NgModule({
  declarations: [ServiceMonitoringBoardComponent, InstallationMonitoringBoardComponent, FillRatioReportComponent, CustomerMachineMasterComponent],
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
