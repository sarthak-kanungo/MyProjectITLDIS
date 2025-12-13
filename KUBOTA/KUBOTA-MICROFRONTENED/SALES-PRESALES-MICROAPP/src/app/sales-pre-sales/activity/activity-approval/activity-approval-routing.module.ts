import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { MarketingActivityApprovalComponent } from './component/marketing-activity-approval/marketing-activity-approval.component';


const routes: Routes = [
  // { path: '', component: ActivityApprovalSearchComponent, canActivate: [UserAccessGuard] },
  { path: ':actPrpId', component: MarketingActivityApprovalComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityApprovalRoutingModule { }
