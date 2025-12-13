import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { itldisEmpolyeeMasterSearchComponent } from './pages/itldis-empolyee-master-search/itldis-empolyee-master-search.component';
import { itldisEmpolyeeMasterCreateComponent } from './pages/itldis-empolyee-master-create/itldis-empolyee-master-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: itldisEmpolyeeMasterSearchComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: itldisEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: itldisEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'edit/:editId', component: itldisEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class itldisEmpolyeeMasterRoutingModule { }
