import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
// import { DelearCustomerCareExCallRoutingModule } from './delear-customer-care-ex-call-routing.module';
// import { SearchDelearCustomerCareExCallComponent } from './component/search-delear-customer-care-ex-call/search-delear-customer-care-ex-call.component';
// import { CreateDelearCustomerCareExCallComponent } from './component/create-delear-customer-care-ex-call/create-delear-customer-care-ex-call.component';
// import { CustomerDetailComponent } from './component/customer-detail/customer-detail.component';
// import { EnquiryComponent } from './component/enquiry/enquiry.component';
// import { FreeServiceHistoryComponent } from './component/free-service-history/free-service-history.component';
// import { ComplaintComponent } from './component/complaint/complaint.component';
// import { ExistingCustomerDetailComponent } from './component/existing-customer-detail/existing-customer-detail.component';
// import { MachineDetailComponent } from './component/machine-detail/machine-detail.component';
// import { DirectSurveyModule } from '../direct-survey/direct-survey.module';
// import { ComplaintModalModule } from '../common-utility/complaint-modal/complaint-modal.module';
// import { ComplaintAssignPopupComponent } from '../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
// import { EnquiryDetailsComponent } from './component/enquiry-details/enquiry-details.component';
// import { PostServiceFeedbackComponent } from './component/post-service-feedback/post-service-feedback.component';
// import { PostSalesFeedbackComponent } from './component/post-sales-feedback/post-sales-feedback.component';
// import { ServiceBookingDetailsComponent } from './component/service-booking-details/service-booking-details.component';
// import { ServiceJobcardDetailsComponent } from './component/service-jobcard-details/service-jobcard-details.component';
import { ServiceJobCardPostSalesComponent } from './component/service-job-card-post-sales/service-job-card-post-sales.component';
import { HoursPopUpServiceBookingComponent } from '../../customer-service/service-booking/component/hours-pop-up/hours-pop-up-service-booking.component';
import { DelearCustomerCareExCallRoutingModule } from './delear-customer-care-ex-call-routing.module';
import { SearchDelearCustomerCareExCallComponent } from './component/search-delear-customer-care-ex-call/search-delear-customer-care-ex-call.component';
import { MachineDetailComponent } from './component/machine-detail/machine-detail.component';
import { CreateDelearCustomerCareExCallComponent } from './component/create-delear-customer-care-ex-call/create-delear-customer-care-ex-call.component';
import { ComplaintComponent } from './component/complaint/complaint.component';
import { FreeServiceHistoryComponent } from './component/free-service-history/free-service-history.component';
import { EnquiryComponent } from './component/enquiry/enquiry.component';
import { CustomerDetailComponent } from './component/customer-detail/customer-detail.component';
import { ServiceJobcardDetailsComponent } from './component/service-jobcard-details/service-jobcard-details.component';
import { ServiceBookingDetailsComponent } from './component/service-booking-details/service-booking-details.component';
import { PostServiceFeedbackComponent } from './component/post-service-feedback/post-service-feedback.component';
import { EnquiryDetailsComponent } from './component/enquiry-details/enquiry-details.component';
import { ExistingCustomerDetailComponent } from './component/existing-customer-detail/existing-customer-detail.component';
import { PostSalesFeedbackComponent } from './component/post-sales-feedback/post-sales-feedback.component';
import { DirectSurveyModule } from '../direct-survey/direct-survey.module';
import { ComplaintModalModule } from '../common-utility/complaint-modal/complaint-modal.module';
import { ComplaintAssignPopupComponent } from '../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
import { ServiceJobCardHourPopComponent } from './component/service-job-card-hour-pop/service-job-card-hour-pop.component';

@NgModule({
  declarations: [SearchDelearCustomerCareExCallComponent, MachineDetailComponent, CreateDelearCustomerCareExCallComponent, CustomerDetailComponent, EnquiryComponent, FreeServiceHistoryComponent, ComplaintComponent, ExistingCustomerDetailComponent, EnquiryDetailsComponent, PostServiceFeedbackComponent, PostSalesFeedbackComponent, ServiceBookingDetailsComponent, ServiceJobcardDetailsComponent, ServiceJobCardPostSalesComponent, ServiceJobCardHourPopComponent],
  imports: [
    CommonModule,
    DelearCustomerCareExCallRoutingModule,
    NgswSearchTableModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DirectSurveyModule,
    ComplaintModalModule
  ],
  entryComponents:[ComplaintAssignPopupComponent,ServiceJobCardHourPopComponent]
  
})
export class DelearCustomerCareExCallModule { }
