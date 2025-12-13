import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchDirectSurveyComponent } from './component/search-direct-survey/search-direct-survey.component';
import { DirectSurveySearchResultComponent } from './component/direct-survey-search-result/direct-survey-search-result.component';
import { DirectSurveySearchComponent } from './pages/direct-survey-search/direct-survey-search.component';
import { CreateDirectSurveyComponent } from './component/create-direct-survey/create-direct-survey.component';
import { DirectSurveyCustomerDetailComponent } from './component/direct-survey-customer-detail/direct-survey-customer-detail.component';
import { DirectSurveyMachineDetailComponent } from './component/direct-survey-machine-detail/direct-survey-machine-detail.component';
import { DirectSurveyCreateComponent } from './pages/direct-survey-create/direct-survey-create.component';
import { DirectSurveyRoutingModule } from './direct-survey-routing.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { CurrentSurveyComponent } from './component/current-survey/current-survey/current-survey.component';
import { OtherMachineDetailsComponent } from './component/other-machine-details/other-machine-details.component';
import { CallAttemptDetailsComponent } from './component/call-attempt-details/call-attempt-details.component';
import { ComplainComponent } from './component/complain/complain.component';
import { ContactMachineImplementCropComponent } from './component/contact-machine-implement-crop/contact-machine-implement-crop.component';
import { DirectSurveyServiceHistoryComponent } from './component/direct-survey-service-history/direct-survey-service-history.component';
import { JobCardViewModalComponent } from './component/job-card-view-modal/job-card-view-modal.component';
import { JobCardModule } from '../../customer-service/job-card/job-card.module';
import { PcrViewModalComponent } from './component/pcr-view-modal/pcr-view-modal.component';
import { WcrViewModalComponent } from './component/wcr-view-modal/wcr-view-modal.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SurveySummaryReportModule } from '../survey-summary-report/survey-summary-report.module';
import { ComplaintModalModule } from '../common-utility/complaint-modal/complaint-modal.module';
import { ComplaintAssignPopupComponent } from '../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
import { CallAttemptHistoryComponent } from './component/call-attempt-history/call-attempt-history.component';
import { OrderbyPipe } from './component/current-survey/current-survey/orderby.pipe';

@NgModule({
  declarations: [
    SearchDirectSurveyComponent,
    DirectSurveySearchResultComponent,
    DirectSurveySearchComponent,
    CreateDirectSurveyComponent,
    DirectSurveyCustomerDetailComponent,
    DirectSurveyMachineDetailComponent,
    DirectSurveyCreateComponent,
    CurrentSurveyComponent,
    OtherMachineDetailsComponent,
    CallAttemptDetailsComponent,
    ComplainComponent,
    ContactMachineImplementCropComponent,
    DirectSurveyServiceHistoryComponent,
    JobCardViewModalComponent,
    PcrViewModalComponent,
    WcrViewModalComponent,
    CallAttemptHistoryComponent,
    OrderbyPipe,
  ],
  imports: [
    CommonModule,
    NgswSearchTableModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DirectSurveyRoutingModule,
    JobCardModule,
    SurveySummaryReportModule,
    ComplaintModalModule
  ],
  // exports:[
  //   DirectSurveyCustomerDetailComponent,
  //   DirectSurveyMachineDetailComponent,
  // ],
  entryComponents: [
    JobCardViewModalComponent,
    PcrViewModalComponent,
    WcrViewModalComponent,
    CreateDirectSurveyComponent,
    ComplaintAssignPopupComponent
  ],
  providers :[ {provide:MAT_DIALOG_DATA, useValue:{}},
    {provide:MatDialogRef, useValue:{}}],
})
export class DirectSurveyModule { }
