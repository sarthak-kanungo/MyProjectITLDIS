import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DealerMastersRoutingModule } from './dealer-masters-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';
import { NgswSearchTableModule } from 'ngsw-search-table';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    DealerMastersRoutingModule,
    ConfirmationBoxModule,
    NgswSearchTableModule
  ]
})
export class DealerMastersModule { }
