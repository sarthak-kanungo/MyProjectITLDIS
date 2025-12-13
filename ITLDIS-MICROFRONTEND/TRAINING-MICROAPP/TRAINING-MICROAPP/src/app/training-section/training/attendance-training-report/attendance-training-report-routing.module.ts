import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingattendanceTrainingReportSearchPageComponent } from './component/attendance-training-report-search-page/attendance-training-report-search-page.component';
import { TrainingattendanceTrainingReportCreatePageComponent } from './component/attendance-training-report-create-page/attendance-training-report-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AttendanceTrainingReportSearchTableComponent } from './component/attendance-training-report-search-table/attendance-training-report-search-table.component';


const routes: Routes = [
  // {path: '', component: AttendanceTrainingReportSearchTableComponent, canActivate: [UserAccessGuard]},
  // {path: 'create', component: TrainingattendanceTrainingReportCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: '', component: TrainingattendanceTrainingReportCreatePageComponent, canActivate: [UserAccessGuard]},
  // {path: 'view', component: TrainingattendanceTrainingReportCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingattendanceTrainingReportRoutingModule { }
