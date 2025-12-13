import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { CustomerMasterSearchPageComponent } from './component/customer-master-search-page/customer-master-search-page.component';
import { CustomerMasterCreatePageComponent } from './component/customer-master-create-page/customer-master-create-page.component';


const routes: Routes = [
  { path: '', component: CustomerMasterSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: CustomerMasterCreatePageComponent,canActivate: [UserAccessGuard] },
  { path: 'view', component: CustomerMasterCreatePageComponent,canActivate: [UserAccessGuard] },
  { path: 'edit', component: CustomerMasterCreatePageComponent,canActivate: [UserAccessGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerMasterRoutingModule { }
