import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [
  { path: 'item-master', loadChildren: () => import('./item-master/item-master.module').then(mod => mod.ItemMasterModule) },
  { path: 'department-master', loadChildren: () => import('./department-master/department-master.module').then(mod => mod.DepartmentMasterModule) },
  { path: 'designation-master', loadChildren: () => import('./designation-master/designation-master.module').then(mod => mod.DesignationMasterModule) },
  { path: 'designation-level-master', loadChildren: () => import('./designation-level-master/designation-level-master.module').then(mod => mod.DesignationLevelMasterModule) },
  { path: 'employee-master', loadChildren: () => import('./kubota-empolyee-master/kubota-empolyee-master.module').then(mod => mod.KubotaEmpolyeeMasterModule) },
  { path: 'role-master', loadChildren: () => import('./role-master/role-master.module').then(mod => mod.RoleMasterModule) },
  { path: 'kubotauser', loadChildren: () => import('./kubota-user/kubota-user.module').then(mod => mod.KubotaUserModule) },
  { path: 'dealer-master', loadChildren: () => import('./dealer-master/dealer-master.module').then(mod => mod.DealerMasterModule) },
  { path: 'kai-branch-depot-master', loadChildren: () => import('./kai-branch-depot-master/kai-branch-depot-master.module').then(mod => mod.KaiBranchDepotMasterModule) },
  { path: 'modelwise-service-schedule-master', loadChildren: () => import('./modelwise-service-schedule-master/modelwise-service-schedule-master.module').then(mod => mod.ModelwiseServiceScheduleMasterModule) },
  { path: 'all-service-check-list-master', loadChildren: () => import('./all-service-check-list-master/all-service-check-list-master.module').then(mod => mod.AllServiceCheckListMasterModule) },
  { path: 'service-delay-reason', loadChildren: () => import('./service-delay-reason/service-delay-reason.module').then(mod => mod.ServiceDelayReasonModule) },
  { path: 'flat-rate-schedule-code-master', loadChildren: () => import('./flat-rate-schedule-code-master/flat-rate-schedule-code-master.module').then(mod => mod.FlatRateScheduleCodeMasterModule) },
  { path: 'flat-rate-schedule-hourly-rate-master', loadChildren: () => import('./flat-rate-schedule-hourly-rate-master/flat-rate-schedule-hourly-rate-master.module').then(mod => mod.FlatRateScheduleHourlyRateMasterModule) },
  { path: 'failure-code-master', loadChildren: () => import('./failure-code-master/failure-code-master.module').then(mod => mod.FailureCodeMasterModule) },
  { path: 'local-labour-master', loadChildren: () => import('./local-labour-master/local-labour-master.module').then(mod => mod.LocalLabourMasterModule) },
  { path: 'budget-head-master', loadChildren: () => import('./budget-head-master/budget-head-master.module').then(mod => mod.BudgetHeadMasterModule) },
  { path: 'source-master', loadChildren: () => import('./kubota-source-master/kubota-source-master.module').then(mod => mod.KubotaSourceMasterModule) },
  { path: 'marketing-activity-master', loadChildren: () => import('./marketing-activity-master/marketing-activity-master.module').then(mod => mod.MarketingActivityMasterModule) },
  { path: 'service-activity-master', loadChildren: () => import('./service-activity-master/service-activity-master.module').then(mod => mod.ServiceActivityMasterModule) },
  { path: 'vendor-supplier-master', loadChildren: () => import('./vendor-supplier-master/vendor-supplier-master.module').then(mod => mod.VendorSupplierMasterModule) },
  { path: 'product-wise-purchase-depot-master', loadChildren: () => import('./product-wise-purchase-depot-master/product-wise-purchase-depot-master.module').then(mod => mod.ProductWisePurchaseDepotMasterModule) },
  { path: 'psi-field-master', loadChildren: () => import('./psi-field-master/psi-field-master.module').then(mod => mod.PsiFieldMasterModule) },
  { path: 'product-master', loadChildren: () => import('./product-master/product-master.module').then(mod => mod.ProductMasterModule) },
  { path: 'exchange-rate-master', loadChildren: () => import('./exchange-rate-master/exchange-rate-master.module').then(mod => mod.ExchangeRateMasterModule) },
  { path: 'mail-master', loadChildren: () => import('./mail-master/mail-master.module').then(mod => mod.MailMasterModule) },
  { path: 'duty-structure-master', loadChildren: () => import('./duty-structure-master/duty-structure-master.module').then(mod => mod.DutyStructureMasterModule) },
  { path: 'item-price-master', loadChildren: () => import('./item-price-master/item-price-master.module').then(mod => mod.ItemPriceMasterModule) },
  { path: 'machine-ndp-master', loadChildren: () => import('./machine-ndp-master/machine-ndp-master.module').then(mod => mod.MachineNdpMasterModule) },
  { path: 'training-master', loadChildren: () => import('./training-master/training-master.module').then(mod => mod.TrainingMasterModule) },
  { path: 'function-master', loadChildren: () => import('./function/function.module').then(mod => mod.FunctionModule) },
  { path: 'transporter-master', loadChildren: () => import('./transporter-master/transporter-master.module').then(mod => mod.TransporterMasterModule) },
  { path: 'assign-org-hierarchy-to-dealer', loadChildren: () => import('./assign-org-hierarchy-to-dealer/assign-org-hierarchy-to-dealer.module').then(mod => mod.AssignOrgHierarchyToDealerModule) },
  { path: 'manage-organization-hierarchy', loadChildren: () => import('./manage-organization-hierarchy/manage-organization-hierarchy.module').then(mod => mod.ManageOrganizationHierarchyModule) },
  { path: 'dealer-territory-mapping', loadChildren: () => import('./dealer-territory-mapping/dealer-territory-mapping.module').then(mod=> mod.DealerTerritoryMappingModule) },
  {path:'trans-hier-mngmnt',loadChildren:()=>import('./manage-approval-hierarchy/manage-approval-hierarchy.module').then(mod=>mod.ManageApprovalHierarchyModule)},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KubotaMastersRoutingModule { }
