import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActivityClaimApprovalRoutingModule } from './activity-claim-approval-routing.module';
import { ActivityClaimApprovalSearchComponent } from './pages/activity-claim-approval-search/activity-claim-approval-search.component';
import { ActivityClaimApprovalCreateComponent } from './pages/activity-claim-approval-create/activity-claim-approval-create.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClaimApprovalSearchComponent } from './component/claim-approval-search/claim-approval-search.component';
import { ClaimApprovalCreateComponent } from './component/claim-approval-create/claim-approval-create.component';
import { ClaimApprovalHeadsComponent } from './component/claim-approval-heads/claim-approval-heads.component';
import { ActivityClaimApprovalCommonWebService } from './activity-claim-approval-common-web.service';
import { ClaimApprovalDealerInfoComponent } from './component/claim-approval-dealer-info/claim-approval-dealer-info.component';


@NgModule({
  declarations: [ActivityClaimApprovalSearchComponent, ActivityClaimApprovalCreateComponent, ClaimApprovalSearchComponent, ClaimApprovalCreateComponent, ClaimApprovalHeadsComponent, ClaimApprovalDealerInfoComponent],
  imports: [
    CommonModule,
    ActivityClaimApprovalRoutingModule,
    MaterialModule, FormsModule, ReactiveFormsModule

  ],
  providers:[ActivityClaimApprovalCommonWebService]
})
export class ActivityClaimApprovalModule { }
