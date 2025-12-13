import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceClaimApprovalSearchComponent } from './pages/service-claim-approval-search/service-claim-approval-search.component';
import { ServiceClaimApprovalComponent } from './pages/service-claim-approval/service-claim-approval.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:ServiceClaimApprovalSearchComponent, canActivate:[UserAccessGuard]},
  {path:'view', component:ServiceClaimApprovalComponent, canActivate:[UserAccessGuard]},
  {path:'create', component:ServiceClaimApprovalComponent, canActivate:[UserAccessGuard]},
  {path:'approval', component:ServiceClaimApprovalComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceClaimApprovalRoutingModule { }
