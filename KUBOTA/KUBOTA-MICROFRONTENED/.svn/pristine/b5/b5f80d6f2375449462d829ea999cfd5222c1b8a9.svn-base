import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnquiryV2RoutingModule } from './enquiry-v2-routing.module';
import { EnquiryV2CreateComponent } from './component/enquiry-v2-create/enquiry-v2-create.component';
import { GeneralInfoComponent } from './component/general-info/general-info.component';
import { ProductInterestedV2Component } from './component/product-interested-v2/product-interested-v2.component';
import { ProspectDetailsV2Component } from './component/prospect-details-v2/prospect-details-v2.component';
import { ProspectBackgroundV2Component } from './component/prospect-background-v2/prospect-background-v2.component';
import { CurrentMachineryDetailsV2Component } from './component/current-machinery-details-v2/current-machinery-details-v2.component';
import { RetailFinanceDetailsV2Component } from './component/retail-finance-details-v2/retail-finance-details-v2.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchEnquiryV2Component } from './component/search-enquiry-v2/search-enquiry-v2.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { DateFormat } from '../../../utils/date-format-util';
import { EnquiryFollowupModule } from '../enquiry-followup/enquiry-followup.module';
import { TransferEnquiryModule } from '../transfer-enquiry/transfer-enquiry.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FollowupHistoryDialogComponent } from './component/followup-history-dialog/followup-history-dialog.component';
import { UiModule } from '../../../ui/ui.module';
import { EnquiryAttachmentsComponent } from './component/enquiry-attachments/enquiry-attachments.component';


@NgModule({
  declarations: [EnquiryV2CreateComponent, GeneralInfoComponent, ProductInterestedV2Component, ProspectDetailsV2Component, ProspectBackgroundV2Component, CurrentMachineryDetailsV2Component, RetailFinanceDetailsV2Component, SearchEnquiryV2Component, FollowupHistoryDialogComponent, EnquiryAttachmentsComponent],
  imports: [
    CommonModule,
    EnquiryV2RoutingModule,
    MaterialModule, 
    FormsModule, 
    ReactiveFormsModule,
    DynamicTableModule,
    NgswSearchTableModule,
    EnquiryFollowupModule,
    TransferEnquiryModule,
    UiModule
  ],
  entryComponents: [FollowupHistoryDialogComponent],
  providers:[DateFormat]
})
export class EnquiryV2Module { }
