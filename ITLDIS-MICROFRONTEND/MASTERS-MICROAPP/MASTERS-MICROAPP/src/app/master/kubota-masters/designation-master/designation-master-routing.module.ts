import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DesignationMasterComponent } from './pages/designation-master/designation-master.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AddDesignationComponent } from './component/add-designation/add-designation.component';


const routes: Routes = [
  { path: '', component: DesignationMasterComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: AddDesignationComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: AddDesignationComponent,canActivate: [UserAccessGuard] },
  { path: 'update/:updateId', component: AddDesignationComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DesignationMasterRoutingModule { }
