import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [
  { path: 'activity-approval', loadChildren: () => import('./ho-activity-approval/ho-activity-approval.module').then(mod => mod.ActivityApprovalModule), canActivate: [UserAccessGuard] },
  { path: 'activity-claim', loadChildren: () => import('./activity-claim/activity-claim.module').then(mod => mod.ActivityClaimModule), canActivate: [UserAccessGuard] },
  { path: 'activity-claim-approval', loadChildren: () => import('./activity-claim-approval/activity-claim-approval.module').then(mod => mod.ActivityClaimApprovalModule), canActivate: [UserAccessGuard] },
  //{ path: 'activity-claim-list', loadChildren: () => import('./activity-claim-list/activity-claim-list.module').then(mod => mod.ActivityClaimListModule), canActivate: [UserAccessGuard] },
  { path: 'activity-proposal', loadChildren: () => import('./activity-proposal/activity-proposal.module').then(mod => mod.ActivityProposalModule), canActivate: [UserAccessGuard] },
  // {path: 'activity-proposal-list', loadChildren: () => import('./activity-proposal-list/activity-proposal-list.module').then(mod => mod.ActivityProposalListModule), canActivate: [UserAccessGuard]},
  { path: 'actual-report', loadChildren: () => import('./actual-report/actual-report.module').then(mod => mod.ActivityReportModule), canActivate: [UserAccessGuard] },
  // {path: 'actual-report-list', loadChildren: () => import('./actual-report-list/actual-report-list.module').then(mod => mod.ActualReportListModule), canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityRoutingModule { }
