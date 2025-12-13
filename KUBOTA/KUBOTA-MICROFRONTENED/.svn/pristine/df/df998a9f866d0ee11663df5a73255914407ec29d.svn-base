import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: 'service-activity-training-proposal', loadChildren: () => import('./service-activity-training-proposal/service-activity-training-proposal.module').then(mod => mod.ServiceActivityTrainingProposalModule)},
  {path: 'service-activity-report', loadChildren: () => import('./service-activity-report/service-activity-report.module').then(mod => mod.ServiceActivityReportModule)},
  {path: 'service-claim', loadChildren: () => import('./service-claim/service-claim.module').then(mod => mod.ServiceClaimModule)},
  {path: 'service-activity-claim', loadChildren: () => import('./service-activity-claim/service-activity-claim.module').then(mod => mod.ServiceActivityClaimModule)},
  {path: 'service-activity-claim-invoice', loadChildren: () => import('./service-claim-invoice/service-claim-invoice.module').then(mod => mod.ServiceClaimInvoiceModule)},
  {path: 'service-claim-invoice', loadChildren: () => import('./service-claim-invoice/service-claim-invoice.module').then(mod => mod.ServiceClaimInvoiceModule)},
  {path: 'marketing-activity-claim-invoice', loadChildren: () => import('./service-claim-invoice/service-claim-invoice.module').then(mod => mod.ServiceClaimInvoiceModule)},
 
  {path: 'service-claim-approval', loadChildren: () => import('./service-claim-approval/service-claim-approval.module').then(mod => mod.ServiceClaimApprovalModule)},
  {path: 'service-activity-claim-approval', loadChildren: () => import('./service-claim-approval/service-claim-approval.module').then(mod => mod.ServiceClaimApprovalModule)},
  {path: 'marketing-activity-claim-approval', loadChildren: () => import('./service-claim-approval/service-claim-approval.module').then(mod => mod.ServiceClaimApprovalModule)},
  {path: 'wcr-claim-approval', loadChildren: () => import('./service-claim-approval/service-claim-approval.module').then(mod => mod.ServiceClaimApprovalModule)},
  // {path: 'service-activity-training-proposal-approval', loadChildren: () => import('./service-activity-proposal-bulk-approval/service-activity-proposal-bulk-approval.module').then(mod=>mod.ServiceActivityProposalBulkApprovalModule)},
   {path: 'serviceactivitybulkApproval', loadChildren: () => import('./service-activity-proposal-bulk-approval/service-activity-proposal-bulk-approval.module').then(mod=>mod.ServiceActivityProposalBulkApprovalModule)},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceActivitiesClaimsRoutingModule { }
