import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivityClaimRoutingModule } from './service-activity-claim-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceActivityClaimDetailsComponent } from './component/service-activity-claim-details/service-activity-claim-details.component';
import { ServiceActivityClaimHeadsComponent } from './component/service-activity-claim-heads/service-activity-claim-heads.component';
import { ServiceActivityClaimSearchPageComponent } from './component/service-activity-claim-search-page/service-activity-claim-search-page.component';
import { ServiceActivityClaimPageComponent } from './component/service-activity-claim-page/service-activity-claim-page.component';
import { ServiceActivityClaimSearchComponent } from './component/service-activity-claim-search/service-activity-claim-search.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ServiceActivityClaimSubActivityComponent } from './component/service-activity-claim-sub-activity/service-activity-claim-sub-activity.component';
import { ServiceActivityClaimPhotoComponent } from './component/service-activity-claim-photo/service-activity-claim-photo.component';
import { UiModule } from '../../../ui/ui.module';
import { ClaimApprovalDetailsComponent } from './component/claim-approval-details/claim-approval-details.component';

@NgModule({
  declarations: [ServiceActivityClaimSearchComponent,ClaimApprovalDetailsComponent, ServiceActivityClaimDetailsComponent, ServiceActivityClaimHeadsComponent, ServiceActivityClaimSearchPageComponent, ServiceActivityClaimPageComponent, ServiceActivityClaimSubActivityComponent, ServiceActivityClaimPhotoComponent],
  imports: [
    CommonModule,
    ServiceActivityClaimRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule
  ],
  providers:[
    {provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}]
})
export class ServiceActivityClaimModule { }
