import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchMachineDescripancyComplaintsComponent } from './pages/search-machine-descripancy-complaints/search-machine-descripancy-complaints.component';
import { CreateMachineDescripancyComplaintsComponent } from './pages/create-machine-descripancy-complaints/create-machine-descripancy-complaints.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SearchMachineDescripancyComplaintsComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateMachineDescripancyComplaintsComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: CreateMachineDescripancyComplaintsComponent, canActivate: [UserAccessGuard] },
  { path:'edit', component:CreateMachineDescripancyComplaintsComponent,canActivate: [UserAccessGuard] },
  { path: 'approve', component: CreateMachineDescripancyComplaintsComponent, canActivate: [UserAccessGuard] }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineDescripancyComplaintRoutingModule { }
