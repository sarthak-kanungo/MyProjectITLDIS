import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IncentiveSchemesApprovalSearchComponent } from '../incentive-schemes-approval/pages/incentive-schemes-approval-search/incentive-schemes-approval-search.component';
import { IncentiveClaimApprovalSearchComponent } from './pages/incentive-claim-approval-search/incentive-claim-approval-search.component';
import { IncentiveClaimApprovalCreateComponent } from './pages/incentive-claim-approval-create/incentive-claim-approval-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: IncentiveClaimApprovalSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: IncentiveClaimApprovalCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncentiveClaimApprovalRoutingModule { }
