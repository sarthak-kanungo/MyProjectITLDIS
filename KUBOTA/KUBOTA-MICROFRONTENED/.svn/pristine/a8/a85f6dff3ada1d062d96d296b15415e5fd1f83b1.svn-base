import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingnominationSearchPageComponent } from './component/training-nomination-search-page/training-nomination-search-page.component';
import { TrainingnominationCreatePageComponent } from './component/training-nomination-create-page/training-nomination-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { TrainingNominationSearchTableComponent } from './component/training-nomination-search-table/training-nomination-search-table.component';
import { TrainingProgrammeCalenderSearchTableComponent } from '../training-programme-calender/component/training-programme-calender-search-table/training-programme-calender-search-table.component';


const routes: Routes = [
  {path: '', component: TrainingNominationSearchTableComponent, canActivate: [UserAccessGuard]},
  // {path: '', component: TrainingProgrammeCalenderSearchTableComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: TrainingnominationCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'edit', component: TrainingnominationCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: TrainingnominationCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingnominationRoutingModule { }
