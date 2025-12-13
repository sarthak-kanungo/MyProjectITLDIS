import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [
  { path: 'employeemasters', loadChildren: () => import('./dealer-employee-master/dealer-employee-master.module').then(mod => mod.DealerEmployeeMasterModule) },
  { path: 'dealeruser', loadChildren: () => import('./dealer-user/dealer-user.module').then(mod => mod.DealerUserModule) },
  { path: 'assignuserbranch', loadChildren: () => import('./assign-user-to-branch/assign-user-to-branch.module').then(mod => mod.AssignUserToBranchModule) },
  { path: 'partymaster', loadChildren: () => import('./party-master/party-master.module').then(mod => mod.PartyMasterModule) },
  { path: 'customermaster', loadChildren: () => import('./customer-master/customer-master.module').then(mod => mod.CustomerMasterModule) },
  { path: 'customerMasterApproval', loadChildren: () => import('./customer-master-change-request/customer-master-change-request.module').then(mod => mod.CustomerMasterChangeRequestModule) },
  { path: 'dealerdepartmentmaster', loadChildren: () => import('./dealer-department-master/dealer-department-master.module').then(mod => mod.DealerDepartmentMasterModule) },
  { path: 'dealerdesignationmaster', loadChildren: () => import('./dealer-designation-master/dealer-designation-master.module').then(mod => mod.DealerDesignationMasterModule) },
  { path: 'branchsubdealermaster', loadChildren: () => import('./branch-sub-dealer-master/branch-sub-dealer-master.module').then(mod => mod.BranchSubDealerMasterModule) },
  { path: 'local-item-master', loadChildren: () => import('./local-item-master/local-item-master.module').then(mod => mod.LocalItemMasterModule) },
  { path: 'machine-master', loadChildren: () => import('./machine-master/machine-master.module').then(mod => mod.MachineMasterModule) },
  // { path: 'employee-bank-details', loadChildren: () => import('./dealer-employee-bank-detail/dealer-employee-bank-detail.module').then(mod => mod.DealerEmployeeBankDetailModule) },
  {path:'employeebankdetail',loadChildren:()=>import('./employee-bank-detail/employee-bank-detail.module').then(mod=>mod.EmployeeBankDetailModule)},
  {path:'employeebankdetailapproval',loadChildren:()=>import('./bank-detail-approval/bank-detail-approval.module').then(mod=>mod.BankDetailApprovalModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerMastersRoutingModule { }
