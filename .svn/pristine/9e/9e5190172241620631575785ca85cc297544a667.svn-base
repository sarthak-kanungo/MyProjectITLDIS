import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PurchaseOrderCreatePageComponent } from './component/purchase-order-create-page/purchase-order-create-page.component';
import { PurchaseOrderSearchPageComponent } from './component/purchase-order-search-page/purchase-order-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  { path: '', component: PurchaseOrderSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: PurchaseOrderCreatePageComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: PurchaseOrderCreatePageComponent, canActivate: [UserAccessGuard] },
  { path: 'update', component: PurchaseOrderCreatePageComponent, canActivate: [UserAccessGuard] },
  { path: 'approve', component: PurchaseOrderCreatePageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparesPurchaseOrderRoutingModule { }
