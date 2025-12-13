import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KubotaEmpolyeeMasterSearchComponent } from './pages/kubota-empolyee-master-search/kubota-empolyee-master-search.component';
import { KubotaEmpolyeeMasterCreateComponent } from './pages/kubota-empolyee-master-create/kubota-empolyee-master-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: KubotaEmpolyeeMasterSearchComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: KubotaEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: KubotaEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'edit/:editId', component: KubotaEmpolyeeMasterCreateComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KubotaEmpolyeeMasterRoutingModule { }
