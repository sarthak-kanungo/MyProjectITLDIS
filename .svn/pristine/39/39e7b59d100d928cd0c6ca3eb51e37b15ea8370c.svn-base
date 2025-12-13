import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SoPageComponent } from './component/so-page/so-page.component';
import { SoSearchPageComponent } from './component/so-search-page/so-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SoSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: SoPageComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:id', component: SoPageComponent , canActivate: [UserAccessGuard]},
  { path: 'view/:id', component: SoPageComponent, canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerOrderRoutingModule { }
