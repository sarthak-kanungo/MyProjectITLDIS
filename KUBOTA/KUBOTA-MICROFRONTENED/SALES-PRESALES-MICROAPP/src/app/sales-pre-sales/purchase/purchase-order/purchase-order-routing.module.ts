import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PurchaseOrderSearchComponent } from './pages/purchase-order-search/purchase-order-search.component';
import { PurchaseOrderCreateComponent } from './pages/purchase-order-create/purchase-order-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  { path: '', component: PurchaseOrderSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: PurchaseOrderCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve/:approveId', component: PurchaseOrderCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: PurchaseOrderCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'update/:editId', component: PurchaseOrderCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PurchaseOrderRoutingModule { }
