import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerServiceRoutingModule } from './customer-service-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CustomerServiceRoutingModule,
    ConfirmationBoxModule
  ]
})
export class CustomerServiceModule { }
