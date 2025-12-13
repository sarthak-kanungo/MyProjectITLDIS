import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchMachineTransferRequestComponent } from './pages/search-machine-transfer-request/search-machine-transfer-request.component';
import { CreateMachineTransferRequestComponent } from './pages/create-machine-transfer-request/create-machine-transfer-request.component';

import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SearchMachineTransferRequestComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateMachineTransferRequestComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:requestNumber', component: CreateMachineTransferRequestComponent, canActivate: [UserAccessGuard] },

  { path: 'approve/:requestNumber', component: CreateMachineTransferRequestComponent, canActivate: [UserAccessGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineTransferRequestRoutingModule { }
