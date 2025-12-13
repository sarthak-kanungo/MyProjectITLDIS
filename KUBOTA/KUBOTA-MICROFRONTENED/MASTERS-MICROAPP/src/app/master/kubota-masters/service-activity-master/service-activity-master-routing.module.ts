import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceActivityMasterSearchComponent } from './pages/service-activity-master-search/service-activity-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ServiceActivityMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceActivityMasterRoutingModule { }
