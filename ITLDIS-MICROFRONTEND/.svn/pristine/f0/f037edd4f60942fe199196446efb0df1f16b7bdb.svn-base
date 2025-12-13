import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceActivityReportCreatePageComponent } from './component/service-activity-report-create-page/service-activity-report-create-page.component';
import { ServiceActivityReportSearchPageComponent } from './component/service-activity-report-search-page/service-activity-report-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  {path: '', component:ServiceActivityReportSearchPageComponent, canActivate:[UserAccessGuard]},
  {path: 'create', component:ServiceActivityReportCreatePageComponent, canActivate:[UserAccessGuard]},
  {path: 'view/:id', component:ServiceActivityReportCreatePageComponent, canActivate:[UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceActivityReportRoutingModule { }
