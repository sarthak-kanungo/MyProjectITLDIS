import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceClaimSearchComponent } from './pages/service-claim-search/service-claim-search.component';
import { ServiceClaimCreateComponent } from './pages/service-claim-create/service-claim-create.component';
import { InProgressComponent } from './pages/in-progress/in-progress.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component:ServiceClaimSearchComponent, canActivate:[UserAccessGuard]},
  {path: 'create', component:ServiceClaimCreateComponent, canActivate:[UserAccessGuard]},
  {path: 'view', component:ServiceClaimCreateComponent, canActivate:[UserAccessGuard]},
  {path: 'approval', component:ServiceClaimCreateComponent, canActivate:[UserAccessGuard]},
  {path: 'in-progress', component:InProgressComponent, canActivate:[UserAccessGuard]}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceClaimRoutingModule { }
