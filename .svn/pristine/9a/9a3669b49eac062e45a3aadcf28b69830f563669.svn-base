import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceCancellationApprovalSearchComponent } from './pages/invoice-cancellation-approval-search/invoice-cancellation-approval-search.component';
import { InvoiceCancellationApprovalCreateComponent } from './pages/invoice-cancellation-approval-create/invoice-cancellation-approval-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: InvoiceCancellationApprovalSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: InvoiceCancellationApprovalCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve/:approveId', component: InvoiceCancellationApprovalCreateComponent, canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoiceCancellationApprovalRoutingModule { }
