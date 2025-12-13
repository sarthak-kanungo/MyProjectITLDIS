import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferReceiptRoutingModule } from './branch-transfer-receipt-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BranchTransferReceiptComponent } from './pages/branch-transfer-receipt/branch-transfer-receipt.component';
import { BranchTransferReceiptCreateComponent } from './pages/branch-transfer-receipt-create/branch-transfer-receipt-create.component';
import { CreateBranchTransferReceiptComponent } from './component/create-branch-transfer-receipt/create-branch-transfer-receipt.component';
import { BranchTransferReceiptItemDetailsComponent } from './component/branch-transfer-receipt-item-details/branch-transfer-receipt-item-details.component';
import { MaterialModule } from '../../../app.module';
import { SearchBranchTransferReceiptComponent } from './component/search-branch-transfer-receipt/search-branch-transfer-receipt.component';
import { CreateBranchTransReceiptContainerComponent } from './component/create-branch-trans-receipt-container/create-branch-trans-receipt-container.component';


@NgModule({
  declarations: [BranchTransferReceiptComponent, BranchTransferReceiptCreateComponent, CreateBranchTransferReceiptComponent, BranchTransferReceiptItemDetailsComponent,SearchBranchTransferReceiptComponent, CreateBranchTransReceiptContainerComponent],
  imports: [
    CommonModule,
    BranchTransferReceiptRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
  ]
})
export class BranchTransferReceiptModule { }
