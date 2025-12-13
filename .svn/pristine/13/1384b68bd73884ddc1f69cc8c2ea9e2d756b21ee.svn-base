import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { CreateNewRoleComponent } from './component/create-new-role/create-new-role.component';
import { SearchRoleComponent } from './component/search-role/search-role.component';


const routes: Routes = [
  { path: '', component: SearchRoleComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateNewRoleComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: CreateNewRoleComponent,canActivate: [UserAccessGuard] },
  { path: 'edit/:id', component: CreateNewRoleComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoleMasterRoutingModule { }
