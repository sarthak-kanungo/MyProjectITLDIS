import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainingSectionRoutingModule } from './training-section-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { NumberToWordsPipe } from '../pipe/number-to-words.pipe';


@NgModule({
  declarations: [NumberToWordsPipe],
  imports: [
    CommonModule,
    TrainingSectionRoutingModule,
    ToastrModule
  ]
})
export class TrainingSectionModule { }
