import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MaterialTreeDemoComponent } from './material-tree-demo/material-tree-demo.component';

const routes: Routes = [
  { path: 'purchase', loadChildren: () => import('./spares-purchase/spares-purchase.module').then(mod => mod.SparesPurchaseModule) },
  { path: 'inventorymanagement', loadChildren: () => import('./inventory-management/inventory-management.module').then(mod => mod.InventoryManagementModule) },
  { path: 'countersales', loadChildren: () => import('./counter-sales/counter-sales.module').then(mod => mod.CounterSalesModule) },
  { path: 'workshop-sales', loadChildren: () => import('./workshop-sales/workshop-sales.module').then(mod => mod.WorkshopSalesModule) },
  { path: 'reports', loadChildren: () => import('./report/report.module').then(mod => mod.ReportModule) },
  { path: 'treeTesting', component: MaterialTreeDemoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparesRoutingModule { }
