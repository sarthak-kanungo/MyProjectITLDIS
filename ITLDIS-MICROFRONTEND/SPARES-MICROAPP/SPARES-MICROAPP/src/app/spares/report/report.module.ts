import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ClosingStockReportComponent } from './closing-stock-report/closing-stock-report.component';
import { NonMovingPartStockReportComponent } from './non-moving-part-stock-report/non-moving-part-stock-report.component';
import { MaterialModule } from 'src/app/app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WebserviceConfigModule } from 'src/app/webservice-config/webservice-config.module';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { UiModule } from 'src/app/ui/ui.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { BackOrderPartReportComponent } from './back-order-part-report/back-order-part-report.component';
import { ItemMovementReportComponent } from './item-movement-report/item-movement-report.component';
import { InventoryMovementReportComponent } from './inventory-movement-report/inventory-movement-report.component';


@NgModule({
  declarations: [ClosingStockReportComponent, NonMovingPartStockReportComponent, BackOrderPartReportComponent, ItemMovementReportComponent, InventoryMovementReportComponent],
  imports: [
    CommonModule,
    ReportRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    WebserviceConfigModule,
    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    UiModule
  ],
  providers:[
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class ReportModule { }
