import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'itldismasters', loadChildren: () => import('./itldis-masters/itldis-masters.module').then(mod => mod.itldisMastersModule) },
  { path: 'dealermasters', loadChildren: () => import('./dealer-masters/dealer-masters.module').then(mod => mod.DealerMastersModule) },
  { path: 'salesmasters', loadChildren: () => import('./sales-masters/sales-masters.module').then(mod => mod.SalesMastersModule) },
  { path: 'service-masters', loadChildren: () => import('./service-masters/service-masters.module').then(mod => mod.ServiceMastersModule) },
  { path: 'spare-masters', loadChildren: () => import('./spares-master/spares-master.module').then(mod => mod.SparesMasterModule) },
  { path: 'kfmmasters', loadChildren: () => import('./kfm-masters/kfm-masters.module').then(mod => mod.KfmMastersModule)},
  { path: 'crm-masters', loadChildren: () => import('./crm-masters/crm-masters.module').then(mod => mod.CrmMasterModule)},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterRoutingModule { }
