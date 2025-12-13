import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineNdpMasterSearchComponent } from './pages/machine-ndp-master-search/machine-ndp-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MachineNdpMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineNdpMasterRoutingModule { }
