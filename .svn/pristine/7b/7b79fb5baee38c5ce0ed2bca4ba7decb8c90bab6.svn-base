import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path :'bintobintransfer', loadChildren : () => import('./bin-to-bin-transfer/bin-to-bin-transfer.module').then(mod =>mod.BinToBinTransferModule)},
  {path:'branch-transfer-indent',loadChildren:()=>import('./branch-transfer-indent/branch-transfer-indent.module').then(mod=>mod.BranchTransferIndentModule)},
  {path:'branch-transfer-issue',loadChildren:()=>import('./branch-transfer-issue/branch-transfer-issue.module').then(mod=>mod.BranchTransferIssueModule)},
  {path:'branch-transfer-receipt',loadChildren:()=>import('./branch-transfer-reciept/branch-transfer-reciept.module').then(mod=>mod.BranchTransferRecieptModule)},
  {path :'stockadjustment',loadChildren:() => import('./stock-adjustment/stock-adjustment.module').then(mod=>mod.StockAdjustmentModule)},
  {path :'currentstock',loadChildren:() => import('./current-stock/current-stock.module').then(mod=>mod.CurrentStockModule)},
  {path :'machinestock',loadChildren:() => import('./machine-stock/machine-stock.module').then(mod=>mod.MachineStockModule)},
  {path:'non-mov-invt',loadChildren:()=>import('./non-movment-inventory/non-movment-inventory.module').then(mod=>mod.NonMovmentInventoryModule)},
  {path:'allDealerActionpart',loadChildren:()=>import('./dealerall-auction-part-details/dealerall-auction-part-details.module').then(mod=>mod.DealerallAuctionPartDetailsModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InventoryManagementRoutingModule { }
