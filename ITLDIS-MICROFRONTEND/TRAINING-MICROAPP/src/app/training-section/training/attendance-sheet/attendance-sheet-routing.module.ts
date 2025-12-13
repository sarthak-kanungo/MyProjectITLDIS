import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingattendanceSheetSearchPageComponent } from './component/attendance-sheet-search-page/attendance-sheet-search-page.component';
import { TrainingattendanceSheetCreatePageComponent } from './component/attendance-sheet-create-page/attendance-sheet-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AttendanceSheetSearchTableComponent } from './component/attendance-sheet-search-table/attendance-sheet-search-table.component';


const routes: Routes = [
  {path: '', component: AttendanceSheetSearchTableComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: TrainingattendanceSheetCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: TrainingattendanceSheetCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'edit', component: TrainingattendanceSheetCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingattendanceSheetRoutingModule { }
