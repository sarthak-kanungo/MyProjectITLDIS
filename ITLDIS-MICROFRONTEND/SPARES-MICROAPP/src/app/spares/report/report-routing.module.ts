import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { BackOrderPartReportComponent } from './back-order-part-report/back-order-part-report.component';
import { ClosingStockReportComponent } from './closing-stock-report/closing-stock-report.component';
import { InventoryMovementReportComponent } from './inventory-movement-report/inventory-movement-report.component';
import { ItemMovementReportComponent } from './item-movement-report/item-movement-report.component';
import { NonMovingPartStockReportComponent } from './non-moving-part-stock-report/non-moving-part-stock-report.component';


const routes: Routes = [
  {path:'closingStockReport', component: ClosingStockReportComponent, canActivate:[UserAccessGuard]},
  {path:'nonMovingPartsStockReport', component: NonMovingPartStockReportComponent, canActivate:[UserAccessGuard]},
  {path:'backOrderPartsReport', component: BackOrderPartReportComponent, canActivate:[UserAccessGuard]},
  {path:'itemMovementReport', component: ItemMovementReportComponent, canActivate:[UserAccessGuard]},
  {path:'inventoryMovementReport', component: InventoryMovementReportComponent, canActivate:[UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
