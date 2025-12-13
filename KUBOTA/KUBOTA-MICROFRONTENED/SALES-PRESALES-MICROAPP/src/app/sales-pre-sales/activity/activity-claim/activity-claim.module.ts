import { NgswSearchTableModule } from 'ngsw-search-table';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActivityClaimRoutingModule } from './activity-claim-routing.module';
import { ActivityClaimSearchComponent } from './pages/activity-claim-search/activity-claim-search.component';
import { ActivityClaimCreateComponent } from './pages/activity-claim-create/activity-claim-create.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivityClaimCreationComponent } from './component/activity-claim-creation/activity-claim-creation.component';
import { SearchActivityClaimComponent } from './component/search-activity-claim/search-activity-claim.component';
import { UiModule } from '../../../ui/ui.module';
import { ClaimApprovalDetailsComponent } from './component/claim-approval-details/claim-approval-details.component';
import { ClaimApprovalDealerInfoComponent } from './component/claim-approval-dealer-info/claim-approval-dealer-info.component';

@NgModule({
  declarations: [ActivityClaimSearchComponent,ClaimApprovalDealerInfoComponent, ActivityClaimCreateComponent, ActivityClaimCreationComponent, SearchActivityClaimComponent, ClaimApprovalDetailsComponent],
  imports: [
    CommonModule,
    ActivityClaimRoutingModule,
    MaterialModule,
     FormsModule, 
     ReactiveFormsModule,
     NgswSearchTableModule,
     UiModule
  ]
})
export class ActivityClaimModule { }
