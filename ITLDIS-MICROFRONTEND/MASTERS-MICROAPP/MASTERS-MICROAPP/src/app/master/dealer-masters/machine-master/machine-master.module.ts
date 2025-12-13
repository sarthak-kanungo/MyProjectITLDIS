import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineMasterRoutingModule } from './machine-master-routing.module';
import { MachineMasterSearchComponent } from './pages/machine-master-search/machine-master-search.component';


@NgModule({
  declarations: [MachineMasterSearchComponent],
  imports: [
    CommonModule,
    MachineMasterRoutingModule
  ]
})
export class MachineMasterModule { }
