import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HoActivityApprovalRoutingModule } from './ho-activity-approval-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarketingActivityApprovalComponent } from './component/marketing-activity-approval/marketing-activity-approval.component';
import { MarketingActivityClaimApprovalComponent } from './component/marketing-activity-claim-approval/marketing-activity-claim-approval.component';


@NgModule({
  declarations: [MarketingActivityApprovalComponent, MarketingActivityClaimApprovalComponent],
  imports: [
    CommonModule,
    HoActivityApprovalRoutingModule,
    MaterialModule, FormsModule, ReactiveFormsModule

  ]
})
export class ActivityApprovalModule { }
