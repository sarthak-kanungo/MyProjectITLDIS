import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBranchTransferIssueComponent } from './component/search-branch-transfer-issue/search-branch-transfer-issue.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { CreateBranchTransferIssueComponent } from './component/create-branch-transfer-issue/create-branch-transfer-issue.component';
import { BranchTransferIssuePageComponent } from './component/branch-transfer-issue-page/branch-transfer-issue-page.component';


const routes: Routes = [
  {path:'',component:SearchBranchTransferIssueComponent,canActivate: [UserAccessGuard]},
  {path:'create',component:BranchTransferIssuePageComponent,canActivate: [UserAccessGuard]},
  {path:'edit',component:BranchTransferIssuePageComponent,canActivate: [UserAccessGuard]},
  {path:'view',component:BranchTransferIssuePageComponent,canActivate: [UserAccessGuard]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferIssueRoutingModule { }
