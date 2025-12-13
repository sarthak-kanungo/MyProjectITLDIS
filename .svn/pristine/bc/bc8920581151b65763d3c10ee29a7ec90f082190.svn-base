import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActivityProposalRoutingModule } from './activity-proposal-routing.module';
import { ActivityProposalSearchComponent } from './pages/activity-proposal-search/activity-proposal-search.component';
import { ActivityProposalCreateComponent } from './pages/activity-proposal-create/activity-proposal-create.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarketingActivitySearchComponent } from './component/marketing-activity-search/marketing-activity-search.component';
import { MarketingActivityCreateComponent } from './component/marketing-activity-create/marketing-activity-create.component';
import { MarketingActivityAddproductComponent } from './component/marketing-activity-addproduct/marketing-activity-addproduct.component';
import { SearchActivityProposalComponent } from './component/search-activity-proposal/search-activity-proposal.component';
import { MarketingActivityCreateContainerComponent } from './component/marketing-activity-create-container/marketing-activity-create-container.component';
import { MarketingActivityAddproductContainerComponent } from './component/marketing-activity-addproduct-container/marketing-activity-addproduct-container.component';
import { MarketingActivitySearchContainerComponent } from './component/marketing-activity-search-container/marketing-activity-search-container.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ActivityProposalService } from './activity-proposal.service';
import { EnquiryDetailsProposalComponent } from './component/enquiry-details-proposal/enquiry-details-proposal.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [ActivityProposalSearchComponent,SearchActivityProposalComponent, ActivityProposalCreateComponent, MarketingActivitySearchComponent, MarketingActivityCreateComponent, MarketingActivityAddproductComponent, MarketingActivityCreateContainerComponent, MarketingActivityAddproductContainerComponent, MarketingActivitySearchContainerComponent, EnquiryDetailsProposalComponent, ApprovalDetailsComponent],
  imports: [
    CommonModule,
    ActivityProposalRoutingModule,
    MaterialModule, FormsModule, ReactiveFormsModule,DynamicTableModule,
    NgswSearchTableModule,
    UiModule
  ],
  providers:[ActivityProposalService]
})
export class ActivityProposalModule { }
