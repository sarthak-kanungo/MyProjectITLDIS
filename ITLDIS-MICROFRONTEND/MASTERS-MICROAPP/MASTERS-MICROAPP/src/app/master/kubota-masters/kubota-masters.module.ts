import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { itldisMastersRoutingModule } from './itldis-masters-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';
import { NgswSearchTableModule } from 'ngsw-search-table';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    itldisMastersRoutingModule,
    ConfirmationBoxModule,
    NgswSearchTableModule
  ]
})
export class itldisMastersModule { }
