import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReInstallationSearchPageComponent } from './component/re-installation-search-page/re-installation-search-page.component';
import { ReInstallationPageComponent } from './component/re-installation-page/re-installation-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component: ReInstallationSearchPageComponent, canActivate:[UserAccessGuard]},
  {path:'create', component: ReInstallationPageComponent, canActivate:[UserAccessGuard]},
  {path:'view/:id', component: ReInstallationPageComponent, canActivate:[UserAccessGuard]},
  {path:'edit/:id', component: ReInstallationPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReInstallationRoutingModule { }
