import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'service-booking', loadChildren: () => import('./service-booking/service-booking.module').then(mod => mod.ServiceBookingModule) },
  { path: 'job-card', loadChildren: () => import('./job-card/job-card.module').then(mod => mod.JobCardModule) },
  // { path: 'job-card-list', loadChildren: () => import('./job-card-list/job-card-list.module').then(mod => mod.JobCardListModule) },
  // { path: 'job-card-close', loadChildren: () => import('./job-card-close/job-card-close.module').then(mod => mod.JobCardCloseModule) },
  // { path: 'job-card-cancel', loadChildren: () => import('./job-card-cancel/job-card-cancel.module').then(mod => mod.JobCardCancelModule) },
  // { path: 'job-card-reopen', loadChildren: () => import('./job-card-reopen/job-card-reopen.module').then(mod => mod.JobCardReopenModule) },
  { path: 'job-card-reopen-approval', loadChildren: () => import('./job-card-reopen-approval/job-card-reopen-approval.module').then(mod => mod.JobCardReopenApprovalModule) },
  { path: 'workshop-bill', loadChildren: () => import('./workshop-bill/workshop-bill.module').then(mod => mod.WorkshopBillModule) },
  { path: 'machine-service-history', loadChildren: () => import('./machine-service-history/machine-service-history.module').then(mod => mod.MachineServiceHistoryModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerServiceRoutingModule { }
