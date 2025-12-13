import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PscPageComponent } from './component/psc-page/psc-page.component';
import { PscSearchPageComponent } from './component/psc-search-page/psc-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component: PscSearchPageComponent, canActivate:[UserAccessGuard]},
  {path:'create', component: PscPageComponent, canActivate:[UserAccessGuard]},
  {path:'edit/:id', component: PscPageComponent, canActivate:[UserAccessGuard]},
  {path:'view/:id', component: PscPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductStorageChecksheetRoutingModule { }
