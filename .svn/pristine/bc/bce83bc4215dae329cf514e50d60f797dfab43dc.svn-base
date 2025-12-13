import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserAccessGuard } from "src/app/auth/user-access.guard";
import { QaReportTableComponent } from "./component/qa-report-table/qa-report-table.component";

const routes: Routes = [
    { path: '', component: QaReportTableComponent, canActivate: [UserAccessGuard] },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class QaReportRoutingModule { }