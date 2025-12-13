import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [

  { path: 'incentive-scheme-master', loadChildren: () => import('./incentive-scheme-master/incentive-scheme-master.module').then(mod => mod.IncentiveSchemeMasterModule), canActivate: [UserAccessGuard] },
  { path: 'incentive-schemes-approval', loadChildren: () => import('./incentive-schemes-approval/incentive-schemes-approval.module').then(mod => mod.IncentiveSchemesApprovalModule), canActivate: [UserAccessGuard] },
  { path: 'incentive-schemes-claim', loadChildren: () => import('./incentive-schemes-claim/incentive-schemes-claim.module').then(mod => mod.IncentiveSchemesClaimModule), canActivate: [UserAccessGuard] },
  { path: 'incentive-claim-approval', loadChildren: () => import('./incentive-claim-approval/incentive-claim-approval.module').then(mod => mod.IncentiveClaimApprovalModule), canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SchemesRoutingModule {
}



