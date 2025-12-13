import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivityProposalBulkApprovalRoutingModule } from './service-activity-proposal-bulk-approval-routing.module';
import { ServiceActivityProposalBulkApprovalComponent } from './service-activity-proposal-bulk-approval/service-activity-proposal-bulk-approval.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';


@NgModule({
  declarations: [ServiceActivityProposalBulkApprovalComponent],
  imports: [
    CommonModule,
    ServiceActivityProposalBulkApprovalRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ]
})
export class ServiceActivityProposalBulkApprovalModule { }
