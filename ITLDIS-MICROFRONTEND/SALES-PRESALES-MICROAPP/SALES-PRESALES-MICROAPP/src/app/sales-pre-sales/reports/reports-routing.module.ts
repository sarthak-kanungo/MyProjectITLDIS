import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [
  { path: 'activityReportStatus',loadChildren: () => import('./marketing-claim-report/marketing-claim-report.module').then(mod => mod.MarketingClaimReportModule) },
  { path: 'activityProposalStatus',loadChildren: () => import('./marketing-claim-report/marketing-claim-report.module').then(mod => mod.MarketingClaimReportModule) },
  { path: 'activityClaimStatus',loadChildren: () => import('./marketing-claim-report/marketing-claim-report.module').then(mod => mod.MarketingClaimReportModule) },
  { path: 'marketingClaimReport',loadChildren: () => import('./marketing-claim-report/marketing-claim-report.module').then(mod => mod.MarketingClaimReportModule) },
  { path: 'machineMasterReport',loadChildren: () => import('./machine-master-report/machine-master-report.module').then(mod => mod.MachineMasterReportModule) },
  { path: 'activityEnquiryReport',loadChildren: () => import('./activity-enquiry-report/activity-enquiry-report.module').then(mod => mod.ActivityEnquiryReportModule) },
  { path: 'enquiryReport',loadChildren: () => import('./enquiry-report/enquiry-report.module').then(mod => mod.EnquiryReportModule) },
  { path: 'machine-inventory-report',loadChildren: () => import('./machine-inventory-report/machine-inventory-report.module').then(mode=>mode.MachineInventoryReportModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportsRoutingModule { }
