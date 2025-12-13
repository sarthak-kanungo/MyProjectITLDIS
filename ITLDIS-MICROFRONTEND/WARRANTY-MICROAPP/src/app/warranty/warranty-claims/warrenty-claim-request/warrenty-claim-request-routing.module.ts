import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WarrentyClaimRequestCreatePageComponent } from './component/warrenty-claim-request-create-page/warrenty-claim-request-create-page.component';
import { WarrentyClaimRequestSearchPageComponent } from './component/warrenty-claim-request-search-page/warrenty-claim-request-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '',component: WarrentyClaimRequestSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:WarrentyClaimRequestCreatePageComponent, canActivate: [UserAccessGuard]},
  {path:'view', component:WarrentyClaimRequestCreatePageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WarrentyClaimRequestRoutingModule { }
