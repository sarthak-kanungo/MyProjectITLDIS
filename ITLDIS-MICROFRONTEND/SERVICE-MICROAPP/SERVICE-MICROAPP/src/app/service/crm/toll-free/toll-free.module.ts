import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TollFreeRoutingModule } from './toll-free-routing.module';
import { SearchTollFreeComponent } from './component/search-toll-free/search-toll-free.component';
import { CreateTollFreeComponent } from './component/create-toll-free/create-toll-free.component';
import { CustomerDetailComponent } from './component/customer-detail/customer-detail.component';
import { EnquiryComponent } from './component/enquiry/enquiry.component';
import { FreeServiceHistoryComponent } from './component/free-service-history/free-service-history.component';
import { ComplaintComponent } from './component/complaint/complaint.component';
import { ExistingCustomerDetailComponent } from './component/existing-customer-detail/existing-customer-detail.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { DirectSurveyModule } from '../direct-survey/direct-survey.module';
import { DirectSurveyPageStore } from '../direct-survey/component/create-direct-survey/direct-survey-page.store';
import { DirectSurveyPagePresenter } from '../direct-survey/component/create-direct-survey/direct-survey-page.presenter';
import { SurveyHistoryComponent } from './component/survey-history/survey-history.component';
import { TollFreeHistoryComponent } from './component/toll-free-history/toll-free-history.component';
import { MachineDetailComponent } from './component/machine-detail/machine-detail.component';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CreateDirectSurveyComponent } from '../direct-survey/component/create-direct-survey/create-direct-survey.component';
import { ComplaintModalModule } from '../common-utility/complaint-modal/complaint-modal.module';
import { ComplaintAssignPopupComponent } from '../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';


@NgModule({
  declarations: [SearchTollFreeComponent, CreateTollFreeComponent, CustomerDetailComponent,
    EnquiryComponent, FreeServiceHistoryComponent, ComplaintComponent, ExistingCustomerDetailComponent,
    SurveyHistoryComponent, TollFreeHistoryComponent, MachineDetailComponent],
  imports: [
    CommonModule,
    TollFreeRoutingModule,
    NgswSearchTableModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DirectSurveyModule,
    MatDialogModule,
    ComplaintModalModule
  ],
  providers: [DirectSurveyPageStore, DirectSurveyPagePresenter,
  {provide:MAT_DIALOG_DATA, useValue:{}},
  {provide:MatDialogRef, useValue:{}}],
  entryComponents:[CreateDirectSurveyComponent, ComplaintAssignPopupComponent]
 
})
export class TollFreeModule { }
