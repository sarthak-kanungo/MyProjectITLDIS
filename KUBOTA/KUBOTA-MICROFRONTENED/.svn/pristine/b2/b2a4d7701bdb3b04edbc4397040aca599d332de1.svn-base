import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IncentiveSchemeMasterCreateComponent } from './pages/incentive-scheme-master-create/incentive-scheme-master-create.component';
import { IncentiveSchemeMasterSearchComponent } from './pages/incentive-scheme-master-search/incentive-scheme-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: IncentiveSchemeMasterSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: IncentiveSchemeMasterCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: IncentiveSchemeMasterCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncentiveSchemeMasterRoutingModule { }
