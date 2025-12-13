import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelwiseServiceScheduleMasterSearchComponent } from './pages/modelwise-service-schedule-master-search/modelwise-service-schedule-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  { path: '', component: ModelwiseServiceScheduleMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelwiseServiceScheduleMasterRoutingModule { }
