import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MarketingClaimReportComponent } from './marketing-claim-report.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';



const routes: Routes = [{path:'', component:MarketingClaimReportComponent,canActivate: [UserAccessGuard]}]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MarketingClaimReportRoutingModule {
  
 }
