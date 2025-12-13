import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DutyStructureMasterRoutingModule } from './duty-structure-master-routing.module';
import { DutyStructureMasterSearchComponent } from './pages/duty-structure-master-search/duty-structure-master-search.component';


@NgModule({
  declarations: [DutyStructureMasterSearchComponent],
  imports: [
    CommonModule,
    DutyStructureMasterRoutingModule
  ]
})
export class DutyStructureMasterModule { }
