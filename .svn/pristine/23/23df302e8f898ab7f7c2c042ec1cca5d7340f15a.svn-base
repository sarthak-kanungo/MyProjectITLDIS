import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HotlineReportCreatePageComponent } from './component/hotline-report-create-page/hotline-report-create-page.component';
import { HotlineReportSearchPageComponent } from './component/hotline-report-search-page/hotline-report-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: HotlineReportSearchPageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: HotlineReportCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: HotlineReportCreatePageComponent, canActivate: [UserAccessGuard]},
  {path:'edit', component:HotlineReportCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotlineReportRoutingModule { }
