import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WpdcPageComponent } from './component/wpdc-page/wpdc-page.component';
import { WpdcSearchPageComponent } from './component/wpdc-search-page/wpdc-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: WpdcSearchPageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: WpdcPageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: WpdcPageComponent, canActivate: [UserAccessGuard]},
  {path: 'edit', component: WpdcPageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WarrantyPartsDeliveryChallanRoutingModule { }
