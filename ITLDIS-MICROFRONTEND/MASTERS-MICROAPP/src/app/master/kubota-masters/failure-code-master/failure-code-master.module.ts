import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FailureCodeMasterRoutingModule } from './failure-code-master-routing.module';
import { FailureCodeMasterSearchComponent } from './pages/failure-code-master-search/failure-code-master-search.component';


@NgModule({
  declarations: [FailureCodeMasterSearchComponent],
  imports: [
    CommonModule,
    FailureCodeMasterRoutingModule
  ]
})
export class FailureCodeMasterModule { }
