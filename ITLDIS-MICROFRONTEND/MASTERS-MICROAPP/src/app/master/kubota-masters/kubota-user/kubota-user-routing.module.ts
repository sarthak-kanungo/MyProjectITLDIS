import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { itldisUserSearchPageComponent } from './component/itldis-user-search-page/itldis-user-search-page.component';
import { itldisUserCreatePageComponent } from './component/itldis-user-create-page/itldis-user-create-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
// import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: itldisUserSearchPageComponent,canActivate: [UserAccessGuard]},
  { path: 'create', component: itldisUserCreatePageComponent,canActivate: [UserAccessGuard]},
  { path: 'view/:id', component: itldisUserCreatePageComponent,canActivate: [UserAccessGuard]},
  { path: 'edit/:id', component: itldisUserCreatePageComponent,canActivate: [UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class itldisUserRoutingModule { }
