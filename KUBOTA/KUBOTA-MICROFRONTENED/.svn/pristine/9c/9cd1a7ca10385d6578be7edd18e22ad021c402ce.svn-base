import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityClaimApprovalSearchComponent } from './pages/activity-claim-approval-search/activity-claim-approval-search.component';
import { ActivityClaimApprovalCreateComponent } from './pages/activity-claim-approval-create/activity-claim-approval-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: ':claimId', component: ActivityClaimApprovalCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityClaimApprovalRoutingModule { }
