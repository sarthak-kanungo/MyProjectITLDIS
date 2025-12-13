import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PdcSearchPageComponent } from './component/pdc-search-page/pdc-search-page.component';
import { PdcPageComponent } from './component/pdc-page/pdc-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component: PdcSearchPageComponent, canActivate:[UserAccessGuard]},
  {path:'create', component: PdcPageComponent, canActivate:[UserAccessGuard]},
  {path:'view/:id', component: PdcPageComponent, canActivate:[UserAccessGuard]},
  {path:'edit/:id', component: PdcPageComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreDeliveryChecklistRoutingModule { }
