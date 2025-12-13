import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreSalesRoutingModule } from './pre-sales-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PreSalesRoutingModule,
    ConfirmationBoxModule
  ]
})
export class PreSalesModule { }
