import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActualReportSearchComponent } from './pages/actual-report-search/actual-report-search.component';
import { ActualReportCreateComponent } from './pages/actual-report-create/actual-report-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ActualReportSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: ActualReportCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: ActualReportCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActualReportRoutingModule { }
