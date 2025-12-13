import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceClaimApprovalRoutingModule } from './service-claim-approval-routing.module';
import { ServiceClaimApprovalComponent } from './pages/service-claim-approval/service-claim-approval.component';
import { ServiceClaimApprovalSearchComponent } from './pages/service-claim-approval-search/service-claim-approval-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceClaimApprovalDetailsComponent } from './component/service-claim-approval-details/service-claim-approval-details.component';
import { ClaimApprovalJobCardDetailsComponent } from './component/claim-approval-job-card-details/claim-approval-job-card-details.component';
import { ClaimApprovalMachineServiceDetailsComponent } from './component/claim-approval-machine-service-details/claim-approval-machine-service-details.component';
import { NgswSearchTableModule } from 'ngsw-search-table';


@NgModule({
  declarations: [ServiceClaimApprovalComponent, ServiceClaimApprovalSearchComponent, ServiceClaimApprovalDetailsComponent, ClaimApprovalJobCardDetailsComponent, ClaimApprovalMachineServiceDetailsComponent],
  imports: [
    CommonModule,
    ServiceClaimApprovalRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule
  ]
})
export class ServiceClaimApprovalModule { }
