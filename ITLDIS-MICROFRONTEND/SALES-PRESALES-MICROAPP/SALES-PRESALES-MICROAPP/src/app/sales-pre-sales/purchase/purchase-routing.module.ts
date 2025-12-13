import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';

const routes: Routes = [
  { path: 'purchase-order', loadChildren: () => import('./purchase-order/purchase-order.module').then(mod => mod.PurchaseOrderModule), canActivate: [UserAccessGuard] },
  { path: 'dealer-po-approval', loadChildren: () => import('./dealer-po-approval/dealer-po-approval.module').then(mod => mod.DealerPoApprovalModule), canActivate: [UserAccessGuard] },
  { path: 'channel-finance-indent', loadChildren: () => import('./channel-finance-indent/channel-finance-indent.module').then(mod => mod.ChannelFinanceIndentModule), canActivate: [UserAccessGuard] },
  // { path: 'channel-finance-indent-list',loadChildren:() =>import('./channel-finance-indent-list/channel-finance-indent-list.module').then(mod =>mod.ChannelFinanceIndentListModule) },
  { path: 'goods-receipt-note', loadChildren: () => import('./goods-receipt-note/goods-receipt-note.module').then(mod => mod.GoodsReceiptNoteModule), canActivate: [UserAccessGuard] },
  { path: 'machine-descripancy-complaint', loadChildren: () => import('./machine-descripancy-complaint/machine-descripancy-complaint.module').then(mod => mod.MachineDescripancyComplaintModule), canActivate: [UserAccessGuard] },
  { path: 'descripancy-claim', loadChildren: () => import('./descripancy-claim/descripancy-claim.module').then(mod => mod.DescripancyClaimModule), canActivate: [UserAccessGuard] },
  { path: 'machine-transfer-request', loadChildren: () => import('./machine-transfer-request/machine-transfer-request.module').then(mod => mod.MachineTransferRequestModule), canActivate: [UserAccessGuard] },
  {path:  'machinetransportation',loadChildren:()=>import('./machine-transport/machine-transport.module').then(mod=>mod.MachineTransportModule),canActivate:[UserAccessGuard]},
  // {path:'bulk-approval',loadChildren:()=>import('./machine-po-bulk-approval/machine-po-bulk-approval.module').then(mod=>mod.MachinePoBulkApprovalModule),canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PurchaseRoutingModule { }
