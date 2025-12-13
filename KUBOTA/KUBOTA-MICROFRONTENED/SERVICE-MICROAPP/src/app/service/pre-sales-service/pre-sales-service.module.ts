import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreSalesServiceRoutingModule } from './pre-sales-service-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PreSalesServiceRoutingModule,
    ConfirmationBoxModule,
  ]
})
export class PreSalesServiceModule { }
