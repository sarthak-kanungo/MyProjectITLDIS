import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WcrReportCreatePageComponent } from './component/wcr-report-create-page/wcr-report-create-page.component';
import { WcrReportSearchPageComponent } from './component/wcr-report-search-page/wcr-report-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: WcrReportCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: WcrReportCreatePageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WcrReportRoutingModule { }
