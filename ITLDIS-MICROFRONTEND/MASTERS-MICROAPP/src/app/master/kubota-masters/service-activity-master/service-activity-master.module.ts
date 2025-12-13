import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivityMasterRoutingModule } from './service-activity-master-routing.module';
import { ServiceActivityMasterSearchComponent } from './pages/service-activity-master-search/service-activity-master-search.component';


@NgModule({
  declarations: [ServiceActivityMasterSearchComponent],
  imports: [
    CommonModule,
    ServiceActivityMasterRoutingModule
  ]
})
export class ServiceActivityMasterModule { }
