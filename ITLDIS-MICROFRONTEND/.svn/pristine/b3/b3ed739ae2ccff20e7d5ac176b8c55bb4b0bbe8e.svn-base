import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { SearchSurveySummaryReportComponent } from '../survey-summary-report/component/search-survey-summary-report/search-survey-summary-report.component';
import { DirectSurveyCreateComponent } from './pages/direct-survey-create/direct-survey-create.component';


const routes: Routes = [
  { path: '', component: SearchSurveySummaryReportComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: DirectSurveyCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: DirectSurveyCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'edit', component: DirectSurveyCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DirectSurveyRoutingModule { }
