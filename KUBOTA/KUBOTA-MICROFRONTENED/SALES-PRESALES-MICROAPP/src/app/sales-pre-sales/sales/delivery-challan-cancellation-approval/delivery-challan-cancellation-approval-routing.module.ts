import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import {DeliveryChallanCancellationApprovalComponent} from './delivery-challan-cancellation-approval.component'
import {DeliveryChallanCreateComponent} from './pages/delivery-challan-create/delivery-challan-create.component'


const routes: Routes = [
                        { path: '', component: DeliveryChallanCancellationApprovalComponent, canActivate: [UserAccessGuard] },
                        { path: 'view/:viewId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] },
                        { path: 'approve/:approveId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] }
                    ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeliveryChallanCancellationApprovalRoutingModule { }
