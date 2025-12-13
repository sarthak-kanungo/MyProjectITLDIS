import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductConcernReportRoutingModule } from './product-concern-report-routing.module';
import { PcrPageComponent } from './component/pcr-page/pcr-page.component';
import { PcrComponent } from './component/pcr/pcr.component';
import { PcrSearchPageComponent } from './component/pcr-search-page/pcr-search-page.component';
import { PcrSearchComponent } from './component/pcr-search/pcr-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImplementsComponent } from './component/implements/implements.component';
import { ServiceHistoryComponent } from './component/service-history/service-history.component';
import { FailurePartsComponent } from './component/failure-parts/failure-parts.component';
import { LabourChargesComponent } from './component/labour-charges/labour-charges.component';
import { OutsideLabourChargesComponent } from './component/outside-labour-charges/outside-labour-charges.component';
import { PcrConcernComponent } from './component/pcr-concern/pcr-concern.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { ToastrModule } from 'ngx-toastr';
import { FileUploadComponent } from './component/file-upload/file-upload.component';
import { UiModule } from '../../../ui/ui.module';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';


@NgModule({
  declarations: [ApprovalDetailsComponent, PcrPageComponent, PcrComponent, PcrSearchPageComponent, PcrSearchComponent, ImplementsComponent, ServiceHistoryComponent, FailurePartsComponent, LabourChargesComponent, OutsideLabourChargesComponent, PcrConcernComponent, FileUploadComponent],
  imports: [
    CommonModule,
    ProductConcernReportRoutingModule,
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
  ]
})
export class ProductConcernReportModule { }
