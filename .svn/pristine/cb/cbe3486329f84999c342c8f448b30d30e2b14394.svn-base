import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: 'bay-master', loadChildren: () => import('./bay-master/bay-master.module').then(mod => mod.BayMasterModule)},
  {path: 'testssr', loadChildren: () => import('./testssr/testssr.module').then(mod => mod.TestssrModule)},
  {path: 'transporter-master', loadChildren: () => import('../service-masters/transporter-master/transporter-master.module').then(mod => mod.TransporterMasterModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceMastersRoutingModule { }
