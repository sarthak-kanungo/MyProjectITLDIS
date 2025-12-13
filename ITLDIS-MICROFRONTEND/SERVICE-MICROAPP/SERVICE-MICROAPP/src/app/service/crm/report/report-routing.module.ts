import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { CustomerSatisfactionReportComponent } from './customer-satisfaction-report/customer-satisfaction-report.component';
import { MonthlyFailureCodeSummaryReportComponent } from './monthly-failure-code-summary-report/monthly-failure-code-summary-report.component';


const routes: Routes = [
  { path: 'survey-summary-qa-report', loadChildren:()=> import('./survey-summary-qa-report/qa-report.module').then(mod=> mod.QaReportModule)},
  { path: 'customerSatisfactionReport', component:CustomerSatisfactionReportComponent, canActivate:[UserAccessGuard]},
  { path: 'monthlyFailureCodeSummaryReport', component:MonthlyFailureCodeSummaryReportComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
