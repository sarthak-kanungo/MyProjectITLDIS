import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { itldisSourceMasterRoutingModule } from './itldis-source-master-routing.module';
import { itldisSourceMasterSearchComponent } from './pages/itldis-source-master-search/itldis-source-master-search.component';


@NgModule({
  declarations: [itldisSourceMasterSearchComponent],
  imports: [
    CommonModule,
    itldisSourceMasterRoutingModule
  ]
})
export class itldisSourceMasterModule { }
