import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RetroFitmentCampaignSearchPageComponent } from './component/retro-fitment-campaign-search-page/retro-fitment-campaign-search-page.component';
import { RetroFitmentCampaignCreatePageComponent } from './component/retro-fitment-campaign-create-page/retro-fitment-campaign-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: RetroFitmentCampaignSearchPageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: RetroFitmentCampaignCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: RetroFitmentCampaignCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RetroFitmentCampaignRoutingModule { }
