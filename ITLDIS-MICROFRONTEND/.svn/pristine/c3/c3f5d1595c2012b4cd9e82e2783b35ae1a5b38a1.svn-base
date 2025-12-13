import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { StockAdjustmentPageComponent } from './component/stock-adjustment-page/stock-adjustment-page.component';
import { SearchStockAdjustmentPageComponent } from './component/search-stock-adjustment-page/search-stock-adjustment-page.component';


const routes: Routes = [

  { path: '', component: SearchStockAdjustmentPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: StockAdjustmentPageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: StockAdjustmentPageComponent, canActivate: [UserAccessGuard] },
  { path: 'approve/:id', component: StockAdjustmentPageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StockAdjustmentRoutingModule { }
