import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FlatRateScheduleHourlyRateMasterRoutingModule } from './flat-rate-schedule-hourly-rate-master-routing.module';
import { FlatRateScheduleHourlyRateMasterSearchComponent } from './pages/flat-rate-schedule-hourly-rate-master-search/flat-rate-schedule-hourly-rate-master-search.component';


@NgModule({
  declarations: [FlatRateScheduleHourlyRateMasterSearchComponent],
  imports: [
    CommonModule,
    FlatRateScheduleHourlyRateMasterRoutingModule
  ]
})
export class FlatRateScheduleHourlyRateMasterModule { }
