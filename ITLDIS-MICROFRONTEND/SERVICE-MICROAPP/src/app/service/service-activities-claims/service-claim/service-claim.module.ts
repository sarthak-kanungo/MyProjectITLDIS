import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceClaimRoutingModule } from './service-claim-routing.module';
import { ServiceClaimCreateComponent } from './pages/service-claim-create/service-claim-create.component';
import { ServiceClaimSearchComponent } from './pages/service-claim-search/service-claim-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServiceClaimDetailsComponent } from './component/service-claim-details/service-claim-details.component';
import { ClaimMachineServiceDetailsComponent } from './component/claim-machine-service-details/claim-machine-service-details.component';
import { ClaimJobCardDetailsComponent } from './component/claim-job-card-details/claim-job-card-details.component';
import { InProgressComponent } from './pages/in-progress/in-progress.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { NgswSearchTableModule } from 'ngsw-search-table';



@NgModule({
  declarations: [ServiceClaimCreateComponent, ServiceClaimSearchComponent, ServiceClaimDetailsComponent, ClaimMachineServiceDetailsComponent, ClaimJobCardDetailsComponent, InProgressComponent],
  imports: [
    CommonModule,
    ServiceClaimRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }],
  entryComponents : [InProgressComponent]  
})
export class ServiceClaimModule { }
