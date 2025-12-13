import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { CustomerMasterChangeRequestComponent } from './customer-master-change-request.component';
import { CustomerMasterChangeRequestApprovalPageComponent } from './customer-master-change-request-approval-page/customer-master-change-request-approval-page.component';

const routes: Routes = [
  { path: '', component: CustomerMasterChangeRequestComponent,canActivate: [UserAccessGuard] },
  { path: 'approve', component: CustomerMasterChangeRequestApprovalPageComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerMasterChangeRequestRoutingModule { }
