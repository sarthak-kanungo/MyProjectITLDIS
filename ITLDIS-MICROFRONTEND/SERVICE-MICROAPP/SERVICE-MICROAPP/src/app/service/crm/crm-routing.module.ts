import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";



const routes: Routes = [
    { path: 'survey-summary-report-telephonic', loadChildren: () => import('./survey-summary-report/survey-summary-report.module').then(mod => mod.SurveySummaryReportModule) },
    { path: 'survey-summary-report-direct', loadChildren: () => import('./survey-summary-report/survey-summary-report.module').then(mod => mod.SurveySummaryReportModule) },
    { path: 'survey-telephonic', loadChildren: () => import('./direct-survey/direct-survey.module').then(mod => mod.DirectSurveyModule) },
    { path: 'survey-direct', loadChildren: () => import('./direct-survey/direct-survey.module').then(mod => mod.DirectSurveyModule) },
    { path: 'complaint-or-query-resolution', loadChildren: ()=> import('./complaint-or-query-resolution/complaint-or-query-resolution.module').then(mod => mod.ComplaintOrQueryResolutionModule)},
    { path: 'customer-care-executive-calls', loadChildren: ()=> import ('./delear-customer-care-ex-call/delear-customer-care-ex-call.module').then(mod=> mod.DelearCustomerCareExCallModule)},
    { path: 'toll-free-call', loadChildren:()=> import('./toll-free/toll-free.module').then(mod=> mod.TollFreeModule)},
    { path: 'reports', loadChildren:()=> import('./report/report.module').then(mod=> mod.ReportModule)}
    // { path: 'survey-summary-qa-report', loadChildren:()=> import('./survey-summary-qa-report/qa-report.module').then(mod=> mod.QaReportModule)}
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class CRMRoutingModule { }