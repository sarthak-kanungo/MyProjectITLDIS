import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [

  { path: 'quotation', loadChildren: () => import('./quotation/quotation.module').then(mod => mod.QuotationModule), canActivate: [UserAccessGuard] },
  { path: 'payment-receipt', loadChildren: () => import('./payment-receipt/payment-receipt.module').then(mod => mod.PaymentReceiptModule), canActivate: [UserAccessGuard] },
  { path: 'allot-deallot', loadChildren: () => import('./de-allotment/de-allotment.module').then(mod => mod.DeAllotmentModule), canActivate: [UserAccessGuard] },
  { path: 'delivery-challan', loadChildren: () => import('./delivery-challan/delivery-challan.module').then(mod => mod.DeliveryChallanModule), canActivate: [UserAccessGuard] },
  { path: 'delivery-challan-cancel-approval', loadChildren: () => import('./delivery-challan-cancellation-approval/delivery-challan-cancellation-approval.module').then(mod => mod.DeliveryChallanCancellationApprovalModule), canActivate: [UserAccessGuard] },
  { path: 'invoice', loadChildren: () => import('./invoice/invoice.module').then(mod => mod.InvoiceModule), canActivate: [UserAccessGuard] },
  { path: 'invoice-cancel-approval', loadChildren: () => import('./invoice-cancellation-approval/invoice-cancellation-approval.module').then(mod => mod.InvoiceCancellationApprovalModule), canActivate: [UserAccessGuard] },
  { path: 'exchange-inventory', loadChildren: () => import('./exchange-inventory/exchange-inventory.module').then(mod => mod.ExchangeInventoryModule), canActivate: [UserAccessGuard] },
  { path: 'marketIntelligence', loadChildren: () => import('./market-intelligence/market-intelligence.module').then(mod => mod.MarketIntelligenceModule), canActivate: [UserAccessGuard] },
  { path: 'blockmachinesforsale', loadChildren: () => import('./blocks-machine-sale/blocks-machine-sale.module').then(mod => mod.BlocksMachineSaleModule)},
  // {path:'changeName',loadChildren:()=>import('./change-chassis-owner-name/change-chassis-owner-name.module').then(mod=>mod.ChangeChassisOwnerNameModule)},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesRoutingModule { }
