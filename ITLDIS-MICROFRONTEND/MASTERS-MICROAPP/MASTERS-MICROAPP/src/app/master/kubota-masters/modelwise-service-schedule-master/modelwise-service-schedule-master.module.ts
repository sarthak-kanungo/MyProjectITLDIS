import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModelwiseServiceScheduleMasterRoutingModule } from './modelwise-service-schedule-master-routing.module';
import { ModelwiseServiceScheduleMasterSearchComponent } from './pages/modelwise-service-schedule-master-search/modelwise-service-schedule-master-search.component';


@NgModule({
  declarations: [ModelwiseServiceScheduleMasterSearchComponent],
  imports: [
    CommonModule,
    ModelwiseServiceScheduleMasterRoutingModule
  ]
})
export class ModelwiseServiceScheduleMasterModule { }
