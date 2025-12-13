import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineInventoryReportComponent } from './component/machine-inventory-report/machine-inventory-report.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:MachineInventoryReportComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineInventoryReportRoutingModule { }
