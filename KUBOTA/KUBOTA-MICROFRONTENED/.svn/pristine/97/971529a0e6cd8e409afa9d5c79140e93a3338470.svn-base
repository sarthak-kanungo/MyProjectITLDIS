import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EnquiryV2CreateComponent } from './component/enquiry-v2-create/enquiry-v2-create.component';
import { SearchEnquiryV2Component } from './component/search-enquiry-v2/search-enquiry-v2.component';
import { TransferEnquiryComponent } from '../transfer-enquiry/pages/transfer-enquiry/transfer-enquiry.component';
import { EnquiryFollowupCreateComponent } from '../enquiry-followup/pages/enquiry-followup-create/enquiry-followup-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  {path:'', component:SearchEnquiryV2Component, canActivate: [UserAccessGuard]},
  {path:'create', component:EnquiryV2CreateComponent, canActivate: [UserAccessGuard]},
  {path:'view/:enqNo', component:EnquiryV2CreateComponent, canActivate: [UserAccessGuard]},
  {path:'viewMobile/:enqNo', component:EnquiryV2CreateComponent, canActivate: [UserAccessGuard]},
  { path: 'transfer', component: TransferEnquiryComponent, canActivate: [UserAccessGuard] },
  { path: 'followup/:enq', component: EnquiryFollowupCreateComponent, canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnquiryV2RoutingModule { }
