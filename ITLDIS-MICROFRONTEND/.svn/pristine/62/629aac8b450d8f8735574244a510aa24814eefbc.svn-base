import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceSearchComponent } from './pages/invoice-search/invoice-search.component';
import { InvoiceCreateComponent } from './pages/invoice-create/invoice-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: InvoiceSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: InvoiceCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'cancel/:cancelInvoiceId', component: InvoiceCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: InvoiceCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'edit/:editId', component: InvoiceCreateComponent,canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoiceRoutingModule { }
