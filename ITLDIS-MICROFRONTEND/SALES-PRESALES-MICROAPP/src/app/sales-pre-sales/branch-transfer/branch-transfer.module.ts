import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BranchTransferRoutingModule } from './branch-transfer-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../../app.module';
import { ConfirmationBoxModule } from '../../confirmation-box/confirmation-box.module';




@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BranchTransferRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    ConfirmationBoxModule

  ]
})
export class BranchTransferModule { }
