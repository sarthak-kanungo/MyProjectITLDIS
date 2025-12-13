import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'schemes', loadChildren: () => import('./schemes/schemes.module').then(mod => mod.SchemesModule) },
  { path: 'activity', loadChildren: () => import('./activity/activity.module').then(mod => mod.ActivityModule) },
  { path: 'pre-sales', loadChildren: () => import('./pre-sales/pre-sales.module').then(mod => mod.PreSalesModule) },
  { path: 'branch-transfer', loadChildren: () => import('./branch-transfer/branch-transfer.module').then(mod => mod.BranchTransferModule) },
  { path: 'purchase', loadChildren: () => import('./purchase/purchase.module').then(mod => mod.PurchaseModule) },
  { path: 'sales',loadChildren: () => import('./sales/sales.module').then(mod => mod.SalesModule) },
  { path: 'reports',loadChildren: () => import('./reports/reports.module').then(mod => mod.ReportsModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesPreSalesRoutingModule { }
