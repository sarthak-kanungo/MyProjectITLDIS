import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBranchTransferRecieptComponent } from './component/search-branch-transfer-reciept/search-branch-transfer-reciept.component';
// import { CreateBranchTransferRecieptComponent } from './component/create-branch-transfer-reciept/create-branch-transfer-reciept.component';
import { BranchTransferRecieptPageComponent } from './component/branch-transfer-reciept-page/branch-transfer-reciept-page.component';
import { BranchTransferRecieptSearchPageComponent } from './component/branch-transfer-reciept-search-page/branch-transfer-reciept-search-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {
    path:'',
    component:BranchTransferRecieptSearchPageComponent,
    canActivate: [UserAccessGuard]
},
{
   path:'create',
   component:BranchTransferRecieptPageComponent,
   canActivate: [UserAccessGuard]
},
{
  path:'view',
  component:BranchTransferRecieptPageComponent,
  canActivate: [UserAccessGuard]
  
},
{
  path:'edit',
  component:BranchTransferRecieptPageComponent,
  canActivate: [UserAccessGuard]
  
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchTransferRecieptRoutingModule { }
