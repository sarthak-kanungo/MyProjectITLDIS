import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SparePaymentReceiptSearchPageComponent } from './component/spare-payment-receipt-search-page/spare-payment-receipt-search-page.component';
import { SparePaymentReceiptPageComponent } from './component/spare-payment-receipt-page/spare-payment-receipt-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  { path: '', component: SparePaymentReceiptSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: SparePaymentReceiptPageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: SparePaymentReceiptPageComponent, canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparePaymentReceiptRoutingModule { }
