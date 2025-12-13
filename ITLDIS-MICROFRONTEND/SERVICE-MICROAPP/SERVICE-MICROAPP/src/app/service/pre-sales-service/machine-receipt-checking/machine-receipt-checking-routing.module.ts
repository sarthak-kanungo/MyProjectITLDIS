import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MrcDetailsComponent } from './component/mrc-details-page/mrc-details.component';
import { MrcSearchPageComponent } from './component/mrc-search-page/mrc-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component: MrcSearchPageComponent, canActivate:[UserAccessGuard]},
  {path:'create', component: MrcDetailsComponent, canActivate:[UserAccessGuard]},
  {path:'edit/:id', component: MrcDetailsComponent, canActivate:[UserAccessGuard] },
  {path:'view/:id', component: MrcDetailsComponent, canActivate:[UserAccessGuard]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineReceiptCheckingRoutingModule { }
