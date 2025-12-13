import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingProgrammeCalenderCreatePageComponent } from './component/training-prog-calender-create-page/training-prog-calender-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { TrainingProgrammeCalenderSearchTableComponent } from './component/training-programme-calender-search-table/training-programme-calender-search-table.component';


const routes: Routes = [
  {path: '', component: TrainingProgrammeCalenderSearchTableComponent,canActivate: [UserAccessGuard]},
  {path: 'create', component: TrainingProgrammeCalenderCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: TrainingProgrammeCalenderCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'edit', component: TrainingProgrammeCalenderCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingProgrammeCalenderRoutingModule { }
