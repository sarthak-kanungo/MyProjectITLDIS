
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CounterSalesRoutingModule } from './counter-sales-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';
import { PickListComponent } from './sales-pick-list/component/pick-list/pick-list.component';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CounterSalesRoutingModule,
    ConfirmationBoxModule
  ]
})
export class CounterSalesModule { }
