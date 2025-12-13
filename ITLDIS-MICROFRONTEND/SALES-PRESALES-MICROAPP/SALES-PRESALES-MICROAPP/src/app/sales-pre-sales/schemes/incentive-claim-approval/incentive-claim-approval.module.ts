import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncentiveClaimApprovalRoutingModule } from './incentive-claim-approval-routing.module';
import { IncentiveClaimApprovalCreateComponent } from './pages/incentive-claim-approval-create/incentive-claim-approval-create.component';
import { IncentiveClaimApprovalSearchComponent } from './pages/incentive-claim-approval-search/incentive-claim-approval-search.component';
import { SearchIncentiveClaimComponent } from './component/search-incentive-claim/search-incentive-claim.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { IncentiveSchemeClaimComponent } from './component/incentive-scheme-claim/incentive-scheme-claim.component';
import { WholesaleIncentiveSchemeDetailsComponent } from './component/wholesale-incentive-scheme-details/wholesale-incentive-scheme-details.component';
import { RetailIncentiveSchemeDetailsComponent } from './component/retail-incentive-scheme-details/retail-incentive-scheme-details.component';
import { SearchResultsIncentiveClaimApprovalComponent } from './component/search-results-incentive-claim-approval/search-results-incentive-claim-approval.component';


@NgModule({
  declarations: [IncentiveClaimApprovalCreateComponent, IncentiveClaimApprovalSearchComponent, SearchIncentiveClaimComponent, IncentiveSchemeClaimComponent, WholesaleIncentiveSchemeDetailsComponent, RetailIncentiveSchemeDetailsComponent, SearchResultsIncentiveClaimApprovalComponent],
  imports: [
    CommonModule,
    IncentiveClaimApprovalRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule
  ]
})
export class IncentiveClaimApprovalModule { }
