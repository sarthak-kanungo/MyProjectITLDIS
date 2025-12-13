import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SparesSalesInvoiceSearchPageComponent } from './component/spares-sales-invoice-search-page/spares-sales-invoice-search-page.component';
import { SparesSalesInvoiceCreatePageComponent } from './component/spares-sales-invoice-create-page/spares-sales-invoice-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SparesSalesInvoiceSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: SparesSalesInvoiceCreatePageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: SparesSalesInvoiceCreatePageComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:editId', component: SparesSalesInvoiceCreatePageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesInvoiceRoutingModule { }

