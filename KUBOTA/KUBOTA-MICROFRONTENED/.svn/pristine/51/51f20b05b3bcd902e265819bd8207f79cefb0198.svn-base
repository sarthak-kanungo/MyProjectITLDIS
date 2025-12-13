import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WarrentyClaimRequestRoutingModule } from './warrenty-claim-request-routing.module';
import { WarrentyClaimRequestSearchPageComponent } from './component/warrenty-claim-request-search-page/warrenty-claim-request-search-page.component';
import { WarrentyClaimRequestCreatePageComponent } from './component/warrenty-claim-request-create-page/warrenty-claim-request-create-page.component';
import { WarrentyClaimRequestComponent } from './component/warrenty-claim-request/warrenty-claim-request.component';
import { WarrentyClaimRequestImplementComponent } from './component/warrenty-claim-request-implement/warrenty-claim-request-implement.component';
import { WarrentyClaimRequestServiceHistoryComponent } from './component/warrenty-claim-request-service-history/warrenty-claim-request-service-history.component';
import { WarrentyClaimRequestFailurePartsComponent } from './component/warrenty-claim-request-failure-parts/warrenty-claim-request-failure-parts.component';
import { WarrentyClaimRequestRemarksComponent } from './component/warrenty-claim-request-remarks/warrenty-claim-request-remarks.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WarrentyClaimRequestConcernComponent } from './component/warrenty-claim-request-concern/warrenty-claim-request-concern.component';
import { WarrentyClaimRequestSearchComponent } from './component/warrenty-claim-request-search/warrenty-claim-request-search.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { ToastrModule } from 'ngx-toastr';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';
import { LabourChargesComponent } from './component/labour-charges/labour-charges.component';
import { OutsideLabourChargesComponent } from './component/outside-labour-charges/outside-labour-charges.component';
import { ModalFileUploadComponent } from '../../modal-file-upload/modal-file-upload.component';
import { WarrantyClaimUploadComponent } from './component/warranty-claim-upload/warranty-claim-upload.component';
import { InvoiceVerifyModalComponent } from './component/invoice-verify-modal/invoice-verify-modal.component';

@NgModule({
  declarations: [WarrentyClaimRequestSearchPageComponent, ApprovalDetailsComponent, 
    LabourChargesComponent,OutsideLabourChargesComponent, WarrentyClaimRequestCreatePageComponent, 
    WarrentyClaimRequestComponent, WarrentyClaimRequestImplementComponent, 
    WarrentyClaimRequestServiceHistoryComponent, WarrentyClaimRequestFailurePartsComponent, 
    WarrentyClaimRequestRemarksComponent, WarrentyClaimRequestConcernComponent, 
    WarrentyClaimRequestSearchComponent, ModalFileUploadComponent, WarrantyClaimUploadComponent, InvoiceVerifyModalComponent],
  imports: [
    CommonModule,
    WarrentyClaimRequestRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ConfirmationBoxModule,
    ToastrModule,
    UiModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
  entryComponents:[WarrantyClaimUploadComponent,InvoiceVerifyModalComponent]
})
export class WarrentyClaimRequestModule { }
