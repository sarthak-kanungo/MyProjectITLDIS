import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SparesGrnSearchPageComponent } from './component/spares-grn-search-page/spares-grn-search-page.component';
import { SparesGrnPageComponent } from './component/spares-grn-page/spares-grn-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SparesGrnSearchPageComponent , canActivate: [UserAccessGuard]},
  { path: 'create', component: SparesGrnPageComponent , canActivate: [UserAccessGuard]},
  { path: 'view/:viewId', component: SparesGrnPageComponent , canActivate: [UserAccessGuard]},
  { path: 'update/:updateId', component: SparesGrnPageComponent , canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparesGrnRoutingModule { }
