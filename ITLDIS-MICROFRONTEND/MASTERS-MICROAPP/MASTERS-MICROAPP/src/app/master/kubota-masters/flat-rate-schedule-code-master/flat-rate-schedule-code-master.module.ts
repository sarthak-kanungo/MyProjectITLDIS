import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FlatRateScheduleCodeMasterRoutingModule } from './flat-rate-schedule-code-master-routing.module';
import { FlatRateScheduleCodeMasterSearchComponent } from './pages/flat-rate-schedule-code-master-search/flat-rate-schedule-code-master-search.component';


@NgModule({
  declarations: [FlatRateScheduleCodeMasterSearchComponent],
  imports: [
    CommonModule,
    FlatRateScheduleCodeMasterRoutingModule
  ]
})
export class FlatRateScheduleCodeMasterModule { }
