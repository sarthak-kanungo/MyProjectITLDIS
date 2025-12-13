import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: 'machine-receipt-checking', loadChildren: () => import('./machine-receipt-checking/machine-receipt-checking.module').then(mod => mod.MachineReceiptCheckingModule) },
  { path: 'pre-delivery-inspection', loadChildren: () => import('./pre-delivery-inspection/pre-delivery-inspection.module').then(mod => mod.PreDeliveryInspectionModule) },
  { path: 'product-storage-checksheet', loadChildren: () => import('./product-storage-checksheet/product-storage-checksheet.module').then(mod => mod.ProductStorageChecksheetModule) },
  { path: 'pre-delivery-checklist', loadChildren: () => import('./pre-delivery-checklist/pre-delivery-checklist.module').then(mod => mod.PreDeliveryChecklistModule) },
  { path: 'installation', loadChildren: () => import('./installation/installation.module').then(mod => mod.InstallationModule) },
  { path: 're-installation', loadChildren: () => import('./re-installation/re-installation.module').then(mod => mod.ReInstallationModule) },
  { path: 'machineFormf', loadChildren: () => import('./machine-formf/machine-formf.module').then(mod => mod.MachineFormfModule), canActivate:[UserAccessGuard] },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreSalesServiceRoutingModule { }
