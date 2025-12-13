import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WarrantyRoutingModule } from './warranty-routing.module';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    WarrantyRoutingModule,
    ToastrModule
  ]
})
export class WarrantyModule { }
