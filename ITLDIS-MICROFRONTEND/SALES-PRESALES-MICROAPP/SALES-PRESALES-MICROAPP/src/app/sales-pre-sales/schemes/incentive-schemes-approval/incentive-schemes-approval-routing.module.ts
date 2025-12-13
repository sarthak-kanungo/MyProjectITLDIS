import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IncentivesSchemesApprovalCreateComponent } from './pages/incentives-schemes-approval-create/incentives-schemes-approval-create.component';
import { IncentiveSchemesApprovalSearchComponent } from './pages/incentive-schemes-approval-search/incentive-schemes-approval-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: 'create', component: IncentivesSchemesApprovalCreateComponent, canActivate: [UserAccessGuard] },
  { path: '', component: IncentiveSchemesApprovalSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncentiveSchemesApprovalRoutingModule { }
IncentivesSchemesApprovalCreateComponent