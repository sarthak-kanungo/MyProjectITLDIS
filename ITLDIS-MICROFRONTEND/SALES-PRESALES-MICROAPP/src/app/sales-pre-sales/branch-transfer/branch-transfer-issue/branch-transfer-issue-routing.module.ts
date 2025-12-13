import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchTransferIssueComponent } from './pages/branch-transfer-issue/branch-transfer-issue.component';
import { BranchTransferIssueCreateComponent } from './pages/branch-transfer-issue-create/branch-transfer-issue-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { CreateBranchTransIssueContainerComponent } from './component/create-branch-trans-issue-container/create-branch-trans-issue-container.component';


const routes: Routes = [
  { path: '', component: BranchTransferIssueComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component:CreateBranchTransIssueContainerComponent , canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferIssueRoutingModule { }
