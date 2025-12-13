import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SapPageComponent } from './component/sap-page/sap-page.component';
import { SapSearchPageComponent } from './component/sap-search-page/sap-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component:SapSearchPageComponent, canActivate:[UserAccessGuard]},
  {path: 'create', component:SapPageComponent, canActivate:[UserAccessGuard]},
  {path: 'edit', component:SapPageComponent, canActivate:[UserAccessGuard]},
  {path: 'view', component:SapPageComponent, canActivate:[UserAccessGuard]},
  {path: 'approval', component:SapPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceActivityTrainingProposalRoutingModule { }
