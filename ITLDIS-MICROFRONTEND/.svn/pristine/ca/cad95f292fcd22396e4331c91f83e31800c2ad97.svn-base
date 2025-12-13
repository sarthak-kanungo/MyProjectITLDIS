import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BarComponent } from './bar/bar.component';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DrillDownComponent } from './drill-down/drill-down.component';

import { DxChartModule, DxButtonModule } from 'devextreme-angular';
import { DrillDownService } from './drill-down/drill-down.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS, MAT_RADIO_DEFAULT_OPTIONS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../date.adapter';

@NgModule({
  declarations: [BarComponent, DashboardComponent, DrillDownComponent],
  imports: [
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    DashboardRoutingModule,
    NgswSearchTableModule,
    DxChartModule,
    DxButtonModule,
  ],
  providers:[
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class DashboardModule { }
