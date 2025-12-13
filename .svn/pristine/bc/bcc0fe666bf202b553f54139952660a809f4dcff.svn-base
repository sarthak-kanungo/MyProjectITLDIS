import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchTransferRequestSearchComponent } from './pages/branch-transfer-request-search/branch-transfer-request-search.component';
import { BranchTransferRequestCreateComponent } from './pages/branch-transfer-request-create/branch-transfer-request-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: BranchTransferRequestSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: BranchTransferRequestCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferRequestRoutingModule { }
