import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceClaimListComponent } from './pages/service-claim-list/service-claim-list.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:ServiceClaimListComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceClaimListRoutingModule { }
