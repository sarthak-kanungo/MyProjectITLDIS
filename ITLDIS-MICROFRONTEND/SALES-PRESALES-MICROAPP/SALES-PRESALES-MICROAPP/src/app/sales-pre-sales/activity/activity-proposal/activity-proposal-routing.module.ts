import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityProposalSearchComponent } from './pages/activity-proposal-search/activity-proposal-search.component';
import { ActivityProposalCreateComponent } from './pages/activity-proposal-create/activity-proposal-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ActivityProposalSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: ActivityProposalCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:actPrpId', component: ActivityProposalCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:actPrpId', component: ActivityProposalCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve',loadChildren: () => import('../activity-approval/activity-approval.module').then(mod => mod.ActivityApprovalModule), canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityProposalRoutingModule { }
