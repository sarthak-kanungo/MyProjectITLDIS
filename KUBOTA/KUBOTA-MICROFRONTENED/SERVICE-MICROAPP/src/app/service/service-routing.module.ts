import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'pre-sales-service', loadChildren: () => import('./pre-sales-service/pre-sales-service.module').then(mod => mod.PreSalesServiceModule) },
  { path: 'customerService', loadChildren: () => import('./customer-service/customer-service.module').then(mod => mod.CustomerServiceModule) },
  { path: 'service-activities-claims', loadChildren: () => import('./service-activities-claims/service-activities-claims.module').then(mod => mod.ServiceActivitiesClaimsModule) },
  { path: 'crm', loadChildren: () => import('./crm/crm.module').then(mod => mod.CRMModule) },
  { path: 'report', loadChildren: () => import('./report/report.module').then(mod => mod.ReportModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceRoutingModule { }
