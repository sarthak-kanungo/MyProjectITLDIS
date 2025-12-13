import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserAccessGuard } from "src/app/auth/user-access.guard";
import { MachineMasterReportTableComponent } from "./component/machine-master-report-table/machine-master-report-table.component";

const routes: Routes = [
    { path: '', component: MachineMasterReportTableComponent, canActivate: [UserAccessGuard] },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class MachineMasterReportRoutingModule { }