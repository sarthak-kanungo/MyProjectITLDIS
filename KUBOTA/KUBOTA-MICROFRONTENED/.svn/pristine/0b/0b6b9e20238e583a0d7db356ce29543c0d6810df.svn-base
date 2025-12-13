import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { CustomerMachineMasterComponent } from './customer-machine-master/customer-machine-master.component';
import { FillRatioReportComponent } from './fill-ratio-report/fill-ratio-report.component';
import { InstallationMonitoringBoardComponent } from './installation-monitoring-board/installation-monitoring-board.component';
import { ServiceMonitoringBoardComponent } from './service-monitoring-board/service-monitoring-board.component';


const routes: Routes = [
  {path:'smb', component:ServiceMonitoringBoardComponent, canActivate:[UserAccessGuard]},
  {path:'imb', component:InstallationMonitoringBoardComponent, canActivate:[UserAccessGuard]},
  {path:'fillRatio', component:FillRatioReportComponent, canActivate:[UserAccessGuard]},
  {path:'customerMachineMaster', component:CustomerMachineMasterComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
