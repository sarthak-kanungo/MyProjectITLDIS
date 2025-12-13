import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineNdpMasterRoutingModule } from './machine-ndp-master-routing.module';
import { MachineNdpMasterSearchComponent } from './pages/machine-ndp-master-search/machine-ndp-master-search.component';


@NgModule({
  declarations: [MachineNdpMasterSearchComponent],
  imports: [
    CommonModule,
    MachineNdpMasterRoutingModule
  ]
})
export class MachineNdpMasterModule { }
