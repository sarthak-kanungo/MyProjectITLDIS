import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FlatRateScheduleCodeMasterSearchComponent } from './pages/flat-rate-schedule-code-master-search/flat-rate-schedule-code-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: FlatRateScheduleCodeMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FlatRateScheduleCodeMasterRoutingModule { }
