import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchTransferIssueComponent } from '../branch-transfer-issue/pages/branch-transfer-issue/branch-transfer-issue.component';
import { BranchTransferReceiptComponent } from './pages/branch-transfer-receipt/branch-transfer-receipt.component';
import { BranchTransferReceiptCreateComponent } from './pages/branch-transfer-receipt-create/branch-transfer-receipt-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: BranchTransferReceiptComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: BranchTransferReceiptCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferReceiptRoutingModule { }
