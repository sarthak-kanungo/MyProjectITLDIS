import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivitiesClaimsRoutingModule } from './service-activities-claims-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ServiceActivitiesClaimsRoutingModule,
    ConfirmationBoxModule
  ]
})
export class ServiceActivitiesClaimsModule { }
