import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InstallationSearchPageComponent } from './component/installation-search-page/installation-search-page.component';
import { InstallationPageComponent } from './component/installation-page/installation-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component: InstallationSearchPageComponent, canActivate:[UserAccessGuard]},
  {path:'create', component: InstallationPageComponent, canActivate:[UserAccessGuard]},
  {path:'view/:id', component: InstallationPageComponent, canActivate:[UserAccessGuard]},
  {path:'edit/:id', component: InstallationPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstallationRoutingModule { }
