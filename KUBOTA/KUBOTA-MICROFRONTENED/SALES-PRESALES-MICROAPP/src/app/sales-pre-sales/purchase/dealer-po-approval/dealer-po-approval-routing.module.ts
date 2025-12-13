import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealerPoApprovalComponent } from './pages/dealer-po-approval/dealer-po-approval.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DealerPoApprovalComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerPoApprovalRoutingModule { }
