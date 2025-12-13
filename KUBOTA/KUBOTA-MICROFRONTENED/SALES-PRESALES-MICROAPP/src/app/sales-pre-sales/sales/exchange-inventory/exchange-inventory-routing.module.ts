import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { ExchangeInventoryCreateSaleComponent } from './pages/exchange-inventory-create-sale/exchange-inventory-create-sale.component';
import { ExchangeInventorySearchComponent } from './pages/exchange-inventory-search/exchange-inventory-search.component';

const routes: Routes = [
  { path: '', component: ExchangeInventorySearchComponent, canActivate: [UserAccessGuard] },
  // { path: 'create', component: ExchangeInventoryCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:receiptNumber', component: ExchangeInventoryCreateSaleComponent, canActivate: [UserAccessGuard] },
  { path: 'sale/:enq', component: ExchangeInventoryCreateSaleComponent, canActivate: [UserAccessGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExchangeInventoryRoutingModule { }
