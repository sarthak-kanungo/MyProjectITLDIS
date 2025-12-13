import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AllServiceCheckListMasterRoutingModule } from './all-service-check-list-master-routing.module';
import { AllServiceCheckListMasterSearchComponent } from './pages/all-service-check-list-master-search/all-service-check-list-master-search.component';


@NgModule({
  declarations: [AllServiceCheckListMasterSearchComponent],
  imports: [
    CommonModule,
    AllServiceCheckListMasterRoutingModule
  ]
})
export class AllServiceCheckListMasterModule { }
