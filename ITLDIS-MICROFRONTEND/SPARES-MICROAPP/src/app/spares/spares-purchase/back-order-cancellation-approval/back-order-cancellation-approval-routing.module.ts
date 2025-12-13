import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBackOrderCancellationApprovalComponent } from './component/search-back-order-cancellation-approval/search-back-order-cancellation-approval.component';
import { BackOrderCancellationApprovalPageComponent } from './component/back-order-cancellation-approval-page/back-order-cancellation-approval-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchBackOrderCancellationApprovalComponent,canActivate: [UserAccessGuard]},
  {path:'approve',component:BackOrderCancellationApprovalPageComponent,canActivate: [UserAccessGuard]},
  //  {path:'edit',component:BackOrderCancellationApprovalPageComponent,canActivate: [UserAccessGuard]},
   {path:'view',component:BackOrderCancellationApprovalPageComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackOrderCancellationApprovalRoutingModule { }
