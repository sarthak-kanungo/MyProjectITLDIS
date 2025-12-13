import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'part-requisition', loadChildren: () => import('./part-requisition/part-requisition.module').then(mod => mod.PartRequisitionModule) },
  { path: 'part-issue', loadChildren: () => import('./part-issue/part-issue.module').then(mod => mod.PartIssueModule) },
  { path: 'part-return', loadChildren: () => import('./part-return/part-return.module').then(mod => mod.PartReturnModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkshopSalesRoutingModule { }
