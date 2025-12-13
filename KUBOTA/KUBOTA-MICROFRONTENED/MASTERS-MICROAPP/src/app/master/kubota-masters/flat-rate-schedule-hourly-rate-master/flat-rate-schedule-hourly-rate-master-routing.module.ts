import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FlatRateScheduleHourlyRateMasterSearchComponent } from './pages/flat-rate-schedule-hourly-rate-master-search/flat-rate-schedule-hourly-rate-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: FlatRateScheduleHourlyRateMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FlatRateScheduleHourlyRateMasterRoutingModule { }
