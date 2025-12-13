import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineDescripancyClaimComponent } from './pages/machine-descripancy-claim/machine-descripancy-claim.component';
import { CreateMachineDescripancyClaimComponent } from './pages/create-machine-descripancy-claim/create-machine-descripancy-claim.component';
import { SearchDescripancyClaimApprovalComponent } from './component/search-descripancy-claim-approval/search-descripancy-claim-approval.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MachineDescripancyClaimComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateMachineDescripancyClaimComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: CreateMachineDescripancyClaimComponent, canActivate: [UserAccessGuard] },
  { path: 'edit', component: CreateMachineDescripancyClaimComponent, canActivate: [UserAccessGuard] },
  { path: 'approve', component: CreateMachineDescripancyClaimComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DescripancyClaimRoutingModule { }
