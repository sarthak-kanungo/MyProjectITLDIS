import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GoodwillRoutingModule } from './goodwill-routing.module';
import { GoodwillCreatePageComponent } from './component/goodwill-create-page/goodwill-create-page.component';
import { GoodwillSearchPageComponent } from './component/goodwill-search-page/goodwill-search-page.component';
import { GoodwillComponent } from './component/goodwill/goodwill.component';
import { GoodwillImplementComponent } from './component/goodwill-implement/goodwill-implement.component';
import { GoodwillServiceHistoryComponent } from './component/goodwill-service-history/goodwill-service-history.component';
import { GoodwillConcernComponent } from './component/goodwill-concern/goodwill-concern.component';
import { GoodwillFailurePartsComponent } from './component/goodwill-failure-parts/goodwill-failure-parts.component';
import { GoodwillRemarksComponent } from './component/goodwill-remarks/goodwill-remarks.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GoodwillSearchComponent } from './component/goodwill-search/goodwill-search.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { GoodwillUploadComponent } from './component/goodwill-upload/goodwill-upload.component';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { LabourChargesComponent } from './component/labour-charges/labour-charges.component';
import { OutsideLabourChargesComponent } from './component/outside-labour-charges/outside-labour-charges.component';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';

@NgModule({
  declarations: [GoodwillCreatePageComponent,ApprovalDetailsComponent, GoodwillSearchPageComponent, GoodwillComponent, LabourChargesComponent, OutsideLabourChargesComponent, GoodwillImplementComponent, GoodwillServiceHistoryComponent, GoodwillConcernComponent, GoodwillFailurePartsComponent, GoodwillRemarksComponent, GoodwillSearchComponent, GoodwillUploadComponent],
  imports: [
    CommonModule,
    GoodwillRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ConfirmationBoxModule,
    ToastrModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class GoodwillModule { }
