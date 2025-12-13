import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncentiveSchemesClaimRoutingModule } from './incentive-schemes-claim-routing.module';
import { IncentiveSchemesClaimCreateComponent } from './pages/incentive-schemes-claim-create/incentive-schemes-claim-create.component';
import { IncentiveSchemesClaimSearchComponent } from './pages/incentive-schemes-claim-search/incentive-schemes-claim-search.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { WholesaleIncentiveSchemeDetailsComponent } from './component/wholesale-incentive-scheme-details/wholesale-incentive-scheme-details.component';
import { IncentiveSchemeClaimCreateComponent } from './component/incentive-scheme-claim-create/incentive-scheme-claim-create.component';
import { RetailIncentSchemeDetailComponent } from './component/retail-incent-scheme-detail/retail-incent-scheme-detail.component';
import { IncentiveSchemesClaimSearchCriteriaComponent } from './component/incentive-schemes-claim-search-criteria/incentive-schemes-claim-search-criteria.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { IncentiveSchemeClaimApprovalDetailsComponent } from './component/incentive-scheme-claim-approval-details/incentive-scheme-claim-approval-details.component';
import { ModalFileUploadComponent } from '../../modal-file-upload/modal-file-upload.component';



@NgModule({
  declarations: [RetailIncentSchemeDetailComponent,ModalFileUploadComponent,IncentiveSchemesClaimCreateComponent, IncentiveSchemesClaimSearchComponent, WholesaleIncentiveSchemeDetailsComponent, IncentiveSchemeClaimCreateComponent, IncentiveSchemesClaimSearchCriteriaComponent, IncentiveSchemeClaimApprovalDetailsComponent],
  imports: [
    CommonModule,
    IncentiveSchemesClaimRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    DynamicTableModule,
    NgswSearchTableModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class IncentiveSchemesClaimModule { }
