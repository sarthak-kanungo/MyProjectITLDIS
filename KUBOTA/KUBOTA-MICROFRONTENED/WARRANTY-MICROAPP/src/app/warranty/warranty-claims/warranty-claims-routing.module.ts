import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  {path:'product-concern-report',loadChildren:()=> import('./product-concern-report/product-concern-report.module').then(mod=> mod.ProductConcernReportModule)},
  {path:'log-sheet',loadChildren:()=>import('./log-sheet/log-sheet.module').then(mod=>mod.LogSheetModule)},
  {path:'warrenty-claim-request',loadChildren:()=>import('./warrenty-claim-request/warrenty-claim-request.module').then(mod=>mod.WarrentyClaimRequestModule)},
  {path:'goodwill',loadChildren:()=>import('./goodwill/goodwill.module').then(mod=>mod.GoodwillModule)},
  {path:'kai-inspection-sheet',loadChildren:()=>import('./kai-inspection-sheet/kai-inspection-sheet.module').then(mod=>mod.KaiInspectionSheetModule)},
  {path:'retro-fitment-campaign',loadChildren:()=>import('./retro-fitment-campaign/retro-fitment-campaign.module').then(mod=>mod.RetroFitmentCampaignModule)},
  {path:'hotline-report',loadChildren:()=>import('./hotline-report/hotline-report.module').then(mod=>mod.HotlineReportModule)},
  {path:'wcr-report',loadChildren:()=>import('./wcr-report/wcr-report.module').then(mod=>mod.WcrReportModule)},
  {path:'warranty-delivery-challan',loadChildren:()=>import('./warranty-parts-delivery-challan/warranty-parts-delivery-challan.module').then(mod=>mod.WarrantyPartsDeliveryChallanModule)},
  {path:'wcr-final-status',loadChildren:()=>import('./wcr-parts-status/wcr-parts-status.module').then(mod=>mod.WcrPartsStatusModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WarrantyClaimsRoutingModule { }
