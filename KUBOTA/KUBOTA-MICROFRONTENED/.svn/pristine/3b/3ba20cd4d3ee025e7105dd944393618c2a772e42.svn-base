import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RetroFitmentCampaignRoutingModule } from './retro-fitment-campaign-routing.module';
import { RetroFitmentCampaignCreatePageComponent } from './component/retro-fitment-campaign-create-page/retro-fitment-campaign-create-page.component';
import { RetroFitmentCampaignSearchPageComponent } from './component/retro-fitment-campaign-search-page/retro-fitment-campaign-search-page.component';
import { RetroFitmentCampaignComponent } from './component/retro-fitment-campaign/retro-fitment-campaign.component';
import { RetroFitmentCampaignMaterialDetailsComponent } from './component/retro-fitment-campaign-material-details/retro-fitment-campaign-material-details.component';
import { RetroFitmentCampaignLabourChargesComponent } from './component/retro-fitment-campaign-labour-charges/retro-fitment-campaign-labour-charges.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RetroFitmentCampaignSearchComponent } from './component/retro-fitment-campaign-search/retro-fitment-campaign-search.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { ToastrModule } from 'ngx-toastr';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';

@NgModule({
  declarations: [RetroFitmentCampaignCreatePageComponent, RetroFitmentCampaignSearchPageComponent, RetroFitmentCampaignComponent, RetroFitmentCampaignMaterialDetailsComponent, RetroFitmentCampaignLabourChargesComponent, RetroFitmentCampaignSearchComponent],
  imports: [
    CommonModule,
    RetroFitmentCampaignRoutingModule,
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
export class RetroFitmentCampaignModule { }
