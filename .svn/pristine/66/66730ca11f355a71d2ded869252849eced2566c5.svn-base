import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EnquiryReportComponent } from './enquiry-report.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [{path:'', component:EnquiryReportComponent,canActivate: [UserAccessGuard]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnquiryReportRoutingModule { }
