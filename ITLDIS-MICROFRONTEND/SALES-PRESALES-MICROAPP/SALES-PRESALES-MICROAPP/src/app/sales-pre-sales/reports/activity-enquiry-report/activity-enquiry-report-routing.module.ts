import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityEnquiryReportComponent } from './activity-enquiry-report.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [{path:'', component: ActivityEnquiryReportComponent,canActivate: [UserAccessGuard]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityEnquiryReportRoutingModule { }
