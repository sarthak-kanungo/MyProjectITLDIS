import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssignUserToBranchSearchComponent } from './pages/assign-user-to-branch-search-result/assign-user-to-branch-search.component';
import { AssignUserToBranchCreateComponent } from './pages/assign-user-to-branch-create/assign-user-to-branch-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: AssignUserToBranchSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: AssignUserToBranchCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: AssignUserToBranchCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'edit', component: AssignUserToBranchCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignUserToBranchRoutingModule { }
