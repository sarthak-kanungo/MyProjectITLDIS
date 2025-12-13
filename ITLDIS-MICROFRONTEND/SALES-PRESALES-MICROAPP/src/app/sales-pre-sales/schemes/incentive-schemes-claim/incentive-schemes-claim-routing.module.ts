import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IncentiveSchemesClaimSearchComponent } from './pages/incentive-schemes-claim-search/incentive-schemes-claim-search.component';
import { IncentiveSchemesClaimCreateComponent } from './pages/incentive-schemes-claim-create/incentive-schemes-claim-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: IncentiveSchemesClaimSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: IncentiveSchemesClaimCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: IncentiveSchemesClaimCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve', component: IncentiveSchemesClaimCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncentiveSchemesClaimRoutingModule { }
