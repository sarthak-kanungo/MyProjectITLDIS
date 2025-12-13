import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceActivityClaimSearchPageComponent } from './component/service-activity-claim-search-page/service-activity-claim-search-page.component';
import { ServiceActivityClaimPageComponent } from './component/service-activity-claim-page/service-activity-claim-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component:ServiceActivityClaimSearchPageComponent, canActivate:[UserAccessGuard]},
  {path: 'create', component:ServiceActivityClaimPageComponent, canActivate:[UserAccessGuard]},
  {path: 'view/:id', component:ServiceActivityClaimPageComponent, canActivate:[UserAccessGuard]},
  {path: 'approval/:id', component:ServiceActivityClaimPageComponent, canActivate:[UserAccessGuard]},
  {path: 'edit/:id', component:ServiceActivityClaimPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceActivityClaimRoutingModule { }
