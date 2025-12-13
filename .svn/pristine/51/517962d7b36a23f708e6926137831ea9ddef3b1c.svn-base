import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealerDepartmentMasterSearchPageComponent } from './component/dealer-department-master-search-page/dealer-department-master-search-page.component';
import { DealerDepartmentMasterPageComponent } from './component/dealer-department-master-page/dealer-department-master-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DealerDepartmentMasterSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: DealerDepartmentMasterPageComponent,canActivate: [UserAccessGuard] },
  { path: 'view', component: DealerDepartmentMasterPageComponent ,canActivate: [UserAccessGuard]},
  { path: 'edit', component: DealerDepartmentMasterPageComponent,canActivate: [UserAccessGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerDepartmentMasterRoutingModule { }
