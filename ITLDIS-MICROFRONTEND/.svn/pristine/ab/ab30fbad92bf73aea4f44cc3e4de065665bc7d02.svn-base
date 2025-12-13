import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { EmployeeMasterCreatePageComponent } from './component/employee-master-create-page/employee-master-create-page.component';
import { EmployeeMasterSearchPageComponent } from './component/employee-master-search-page/employee-master-search-page.component';


const routes: Routes = [
  { path: '', component: EmployeeMasterSearchPageComponent , canActivate:[UserAccessGuard]},
  { path: 'create', component: EmployeeMasterCreatePageComponent, canActivate:[UserAccessGuard]},
  { path: 'view', component: EmployeeMasterCreatePageComponent, canActivate:[UserAccessGuard]},
  { path: 'edit', component: EmployeeMasterCreatePageComponent, canActivate:[UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerEmployeeMasterRoutingModule { }
