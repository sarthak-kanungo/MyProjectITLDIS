import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnquiryFollowupRoutingModule } from './enquiry-followup-routing.module';
import { EnquiryFollowupCreateComponent } from './pages/enquiry-followup-create/enquiry-followup-create.component';
import { AddEnquiryFollowupComponent } from './component/add-enquiry-followup/add-enquiry-followup.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CurrentEnquiryFollowupComponent } from './component/current-enquiry-followup/current-enquiry-followup.component';
import { LostDropEnquiryComponent } from './component/lost-drop-enquiry/lost-drop-enquiry.component';
import { FollowupHistoryComponent } from './component/followup-history/followup-history.component';
import { WebserviceConfigModule } from '../../../webservice-config/webservice-config.module';
import { EnquiryCommonServiceModule } from '../enquiry-common-service/enquiry-common-service.module';
import { AddEnquiryFollowupContainerComponent } from './component/add-enquiry-followup-container/add-enquiry-followup-container.component';
import { CurrentEnquiryFollowupContainerComponent } from './component/current-enquiry-followup-container/current-enquiry-followup-container.component';
import { LostDropEnquiryContainerComponent } from './component/lost-drop-enquiry-container/lost-drop-enquiry-container.component';
import { EnquiryFollowupService } from './enquiry-followup.service';


@NgModule({
  declarations: [EnquiryFollowupCreateComponent, AddEnquiryFollowupComponent, CurrentEnquiryFollowupComponent, LostDropEnquiryComponent, FollowupHistoryComponent, AddEnquiryFollowupContainerComponent, CurrentEnquiryFollowupContainerComponent, LostDropEnquiryContainerComponent],
  imports: [
    CommonModule,
    EnquiryFollowupRoutingModule,
    MaterialModule, 
    FormsModule, 
    ReactiveFormsModule,
    WebserviceConfigModule,
    EnquiryCommonServiceModule
  ],
  exports:[EnquiryFollowupCreateComponent],
  providers: [EnquiryFollowupService]
})
export class EnquiryFollowupModule { }
