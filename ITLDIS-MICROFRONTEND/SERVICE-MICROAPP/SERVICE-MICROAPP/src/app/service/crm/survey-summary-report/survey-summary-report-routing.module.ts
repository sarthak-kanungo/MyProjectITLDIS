import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { SearchSurveySummaryReportComponent } from './component/search-survey-summary-report/search-survey-summary-report.component';


const routes: Routes = [
  { path: '', component: SearchSurveySummaryReportComponent, canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveySummaryReportRoutingModule { }
