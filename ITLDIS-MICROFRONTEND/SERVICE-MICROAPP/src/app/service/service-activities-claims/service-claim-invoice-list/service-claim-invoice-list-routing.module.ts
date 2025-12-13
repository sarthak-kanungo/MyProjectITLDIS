import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceClaimInvoiceListComponent } from './pages/service-claim-invoice-list/service-claim-invoice-list.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:ServiceClaimInvoiceListComponent, canActivate:[UserAccessGuard]}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceClaimInvoiceListRoutingModule { }
