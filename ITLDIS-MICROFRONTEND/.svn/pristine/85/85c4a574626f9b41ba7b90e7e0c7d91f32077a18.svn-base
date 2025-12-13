import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { MarketingActivityApprovalComponent } from './component/marketing-activity-approval/marketing-activity-approval.component';
import { MarketingActivityClaimApprovalComponent } from './component/marketing-activity-claim-approval/marketing-activity-claim-approval.component';


const routes: Routes = [
  { path: 'activityProposalApproval', component: MarketingActivityApprovalComponent, canActivate: [UserAccessGuard] },
  { path: 'activityClaimApproval', component: MarketingActivityClaimApprovalComponent, canActivate: [UserAccessGuard] }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HoActivityApprovalRoutingModule { }
