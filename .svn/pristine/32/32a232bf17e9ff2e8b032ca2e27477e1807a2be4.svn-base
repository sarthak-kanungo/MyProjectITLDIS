import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SalesInvoiceCancellationContainerCreateComponent } from './container/sales-invoice-cancellation-container-create/sales-invoice-cancellation-container-create.component';
import { SalesInvoiceCancellationContainerSearchComponent } from './container/sales-invoice-cancellation-container-search/sales-invoice-cancellation-container-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  {path:'', component: SalesInvoiceCancellationContainerSearchComponent, canActivate: [UserAccessGuard]},
  {path:'create',component: SalesInvoiceCancellationContainerCreateComponent, canActivate: [UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesInvoiceCancellationRoutingModule { }
