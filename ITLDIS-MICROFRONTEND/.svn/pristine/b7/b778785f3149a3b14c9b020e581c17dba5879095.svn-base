import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchManageApprovalHierarchyComponent } from './search-manage-approval-hierarchy/search-manage-approval-hierarchy.component';
import { CreateManageApprovalHierarchyComponent } from './create-manage-approval-hierarchy/create-manage-approval-hierarchy.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchManageApprovalHierarchyComponent,canActivate: [UserAccessGuard]},
  {path:'update/:transactionName',component:CreateManageApprovalHierarchyComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageApprovalHierarchyRoutingModule { }
