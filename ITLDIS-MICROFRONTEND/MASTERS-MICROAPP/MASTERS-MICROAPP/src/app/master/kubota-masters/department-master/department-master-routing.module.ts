import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DepartmentMasterComponent } from './pages/department-master/department-master.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AddDepartmentComponent } from './components/add-department/add-department.component';


const routes: Routes = [
  { path: '', component: DepartmentMasterComponent },
  { path: 'create', component: AddDepartmentComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: AddDepartmentComponent,canActivate: [UserAccessGuard] },
  { path: 'update/:updateId', component: AddDepartmentComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentMasterRoutingModule { }
