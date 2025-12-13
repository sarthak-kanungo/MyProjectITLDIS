import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceClaimInvoiceSearchComponent } from './pages/service-claim-invoice-search/service-claim-invoice-search.component';
import { ServiceClaimInvoiceCreateComponent } from './pages/service-claim-invoice-create/service-claim-invoice-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component:ServiceClaimInvoiceSearchComponent, canActivate:[UserAccessGuard]},
  {path: 'view', component:ServiceClaimInvoiceCreateComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceClaimInvoiceRoutingModule { }
