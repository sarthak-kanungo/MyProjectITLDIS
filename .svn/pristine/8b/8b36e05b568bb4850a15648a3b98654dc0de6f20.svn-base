import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { JobCardRoutingModule } from './job-card-routing.module';
import { JobCardCreateComponent } from './component/create-job-card-page/create-job-card-page.component';
import { JobCardSearchComponent } from './component/job-card-search/job-card-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JobCardDetailsComponent } from './component/job-card-details/job-card-details.component';
import { ServiceJobCardComponent } from './component/service-job-card/service-job-card.component';
import { PartRequisitionComponent } from './component/part-requisition/part-requisition.component';
import { LabourChargesComponent } from './component/labour-charges/labour-charges.component';
import { OutsideJobChargesComponent } from './component/outside-job-charges/outside-job-charges.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { CustomerConcernComponent } from './component/customer-concern/customer-concern.component';
import { SearchJobCardPageComponent } from './component/search-job-card-page/search-job-card-page.component';
import { HoursPopUpComponent } from './component/hours-pop-up/hours-pop-up.component';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { UiModule } from '../../../ui/ui.module';
import { APP_DATE_FORMATS, AppDateAdapter } from '../../../date.adapter';
import { RetrofitMentPageComponent } from './component/retrofit-ment-page/retrofit-ment-page.component';
import { KubotaImplementComponent } from './component/kubota-implement/kubota-implement.component';


@NgModule({
  declarations: [JobCardSearchComponent, JobCardDetailsComponent, ServiceJobCardComponent, PartRequisitionComponent, LabourChargesComponent, OutsideJobChargesComponent, CustomerConcernComponent, JobCardCreateComponent, SearchJobCardPageComponent, HoursPopUpComponent, RetrofitMentPageComponent, KubotaImplementComponent],
  imports: [
    CommonModule,
    JobCardRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ], entryComponents: [HoursPopUpComponent],
  exports: [HoursPopUpComponent],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class JobCardModule { }
