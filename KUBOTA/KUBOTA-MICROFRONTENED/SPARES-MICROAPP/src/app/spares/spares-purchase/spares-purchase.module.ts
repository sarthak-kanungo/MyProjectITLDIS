import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SparesPurchaseRoutingModule } from './spares-purchase-routing.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SparesPurchaseRoutingModule,
    ConfirmationBoxModule,
    ReactiveFormsModule,
    FormsModule,
    NgswSearchTableModule
  ]
})
export class SparesPurchaseModule { }
