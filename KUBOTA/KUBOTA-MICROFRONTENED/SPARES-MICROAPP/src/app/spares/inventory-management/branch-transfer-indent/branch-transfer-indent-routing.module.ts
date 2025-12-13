import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateBranchIndentComponent } from './component/create-branch-indent/create-branch-indent.component';
import { SearchBranchTransferIndentComponent } from './component/search-branch-transfer-indent/search-branch-transfer-indent.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { BranchTransferPageComponent } from './component/branch-transfer-page/branch-transfer-page.component';


const routes: Routes = [
  {
    path:'',
    component:SearchBranchTransferIndentComponent,
    canActivate: [UserAccessGuard]
  },
  {
    path:'create',
    component:BranchTransferPageComponent,
    canActivate:[UserAccessGuard]
  },
  {
    path:'edit',
    component:BranchTransferPageComponent,
    canActivate:[UserAccessGuard]
  },
  {
    path:'view',
    component:BranchTransferPageComponent,
    canActivate:[UserAccessGuard]
  }
]
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferIndentRoutingModule { }
