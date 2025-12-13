import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EnquirySearchComponent } from './pages/enquiry-search/enquiry-search.component';
import { EnquiryCreateComponent } from './pages/enquiry-create/enquiry-create.component';
import { EnquiryViewMobileComponent } from './component/enquiry-view-mobile/enquiry-view-mobile.component';
import { TransferEnquiryComponent } from '../transfer-enquiry/pages/transfer-enquiry/transfer-enquiry.component';
import { EnquiryFollowupCreateComponent } from '../enquiry-followup/pages/enquiry-followup-create/enquiry-followup-create.component';
import { EnquiryEditComponent } from './component/enquiry-edit/enquiry-edit.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';

const routes: Routes = [
  { path: '', component: EnquirySearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: EnquiryCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:enqNo', component: EnquiryEditComponent, canActivate: [UserAccessGuard] },
  { path: 'viewMobile/:mobenqNo', component: EnquiryViewMobileComponent, canActivate: [UserAccessGuard] },
  { path: 'transfer', component: TransferEnquiryComponent, canActivate: [UserAccessGuard] },
  { path: 'followup/:enq', component: EnquiryFollowupCreateComponent, canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnquiryRoutingModule { }
