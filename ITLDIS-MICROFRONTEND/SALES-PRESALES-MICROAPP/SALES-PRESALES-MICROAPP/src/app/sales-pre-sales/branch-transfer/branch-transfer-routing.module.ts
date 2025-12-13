import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';

const routes: Routes = [
  { path: 'branch-transfer-indent', loadChildren: () => import('./branch-transfer-request/branch-transfer-request.module').then(mod => mod.BranchTransferRequestModule), canActivate: [UserAccessGuard] },
  { path: 'branch-transfer-issue', loadChildren: () => import('./branch-transfer-issue/branch-transfer-issue.module').then(mod => mod.BranchTransferIssueModule), canActivate: [UserAccessGuard] },
  { path: 'branch-transfer-receipt', loadChildren: () => import('./branch-transfer-receipt/branch-transfer-receipt.module').then(mod => mod.BranchTransferReceiptModule), canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class BranchTransferRoutingModule { }

