import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PaymentRecieptSearchComponent } from './pages/payment-reciept-search/payment-reciept-search.component';
import { PaymentRecieptCreateComponent } from './pages/payment-reciept-create/payment-reciept-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: PaymentRecieptSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: PaymentRecieptCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:receiptNumber', component: PaymentRecieptCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentReceiptRoutingModule { }
