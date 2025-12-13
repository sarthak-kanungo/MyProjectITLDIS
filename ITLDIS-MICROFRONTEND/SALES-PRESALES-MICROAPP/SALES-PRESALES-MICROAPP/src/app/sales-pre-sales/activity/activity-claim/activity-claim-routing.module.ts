import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityClaimSearchComponent } from './pages/activity-claim-search/activity-claim-search.component';
import { ActivityClaimCreateComponent } from './pages/activity-claim-create/activity-claim-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { SearchActivityClaimComponent } from './component/search-activity-claim/search-activity-claim.component';


const routes: Routes = [
  { path: '', component: SearchActivityClaimComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: ActivityClaimCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:claimId', component: ActivityClaimCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve/:claimId', component: ActivityClaimCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:claimId', component: ActivityClaimCreateComponent, canActivate: [UserAccessGuard] },
  // { path: 'approve', loadChildren: () => import('../activity-claim-approval/activity-claim-approval.module').then(mod => mod.ActivityClaimApprovalModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityClaimRoutingModule { }
