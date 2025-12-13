import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'order-planning-sheet', loadChildren: () => import('./order-planning-sheet/order-planning-sheet.module').then(mod => mod.OrderPlanningSheetModule) },
  { path: 'newpartsclaim', loadChildren: () => import('./new-parts-claim/new-parts-claim.module').then(mod => mod.NewPartsClaimModule) },
  { path: 'goods-receipt-note', loadChildren: () => import('./spares-grn/spares-grn.module').then(mod => mod.SparesGrnModule) },
  { path: 'sparespurchaseorder', loadChildren: () => import('./spares-purchase-order/spares-purchase-order.module').then(mod => mod.SparesPurchaseOrderModule) },
  { path: 'back-order-cancellation', loadChildren: () => import('./back-order-cancellation-request/back-order-cancellation-request.module').then(mod => mod.BackOrderCancellationRequestModule) },
  { path: 'transit-details', loadChildren: () => import('./view-transit-detail/view-transit-detail.module').then(mod => mod.ViewTransitDetailModule) },
  { path: 'binningslip', loadChildren: () => import('./binning-slip/binning-slip.module').then(mod => mod.BinningSlipModule) },
  {path:'blockpartsforsale',loadChildren:()=>import('./block-part-sale/block-part-sale.module').then(mode=>mode.BlockPartSaleModule)},
  { path: 'back-order-cancellation-approve', loadChildren: () => import('./back-order-cancellation-approval/back-order-cancellation-approval.module').then(mod => mod.BackOrderCancellationApprovalModule) },
  {path:'discrepancy-mmr-claim',loadChildren:()=>import('./spare-descripancy-claim/spare-descripancy-claim.module').then(mod=>mod.SpareDescripancyClaimModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SparesPurchaseRoutingModule { }
