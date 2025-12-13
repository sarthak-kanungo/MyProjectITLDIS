import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingMasterRoutingModule } from './training-master-routing.module';
import { TrainingMasterSearchComponent } from './pages/training-master-search/training-master-search.component';


@NgModule({
  declarations: [TrainingMasterSearchComponent],
  imports: [
    CommonModule,
    TrainingMasterRoutingModule
  ]
})
export class TrainingMasterModule { }
