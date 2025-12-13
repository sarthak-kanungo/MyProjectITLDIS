import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'store-master', loadChildren: () => import('./store-master/store-master.module').then(mod => mod.StoreMasterModule) },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparesMasterRoutingModule { }
