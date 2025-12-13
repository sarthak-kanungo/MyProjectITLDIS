import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceActivityTrainingProposalRoutingModule } from './service-activity-training-proposal-routing.module';

import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivityTrainingProposalDetailsComponent } from './component/activity-training-proposal-details/activity-training-proposal-details.component';
import { HeadsComponent } from './component/heads/heads.component';
import { SapPageComponent } from './component/sap-page/sap-page.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { EditableTableModule } from 'editable-table';
import { SapSearchPageComponent } from './component/sap-search-page/sap-search-page.component';
import { SapSearchComponent } from './component/sap-search/sap-search.component';
import { SubActivityComponent } from './component/sub-activity/sub-activity.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { SapCommonWebService } from './service/sap-common-web.service';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [ActivityTrainingProposalDetailsComponent, HeadsComponent, SapPageComponent, SapSearchPageComponent, SapSearchComponent, SubActivityComponent,],
  imports: [
    CommonModule,
    ServiceActivityTrainingProposalRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule
  ],
  providers: [ { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }, SapCommonWebService]
})
export class ServiceActivityTrainingProposalModule { }
