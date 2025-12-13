import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  {path:'pickingslip', loadChildren: () => import('./picking-slip/picking-slip.module').then(mod => mod.PickingSlipModule)},
  {path:'salesinvoice', loadChildren: () => import('./sales-invoice/sales-invoice.module').then(mod => mod.SalesInvoiceModule)},
  {path:'salesinvoicecancel', loadChildren: () => import('./sales-invoice-cancellation/sales-invoice-cancellation.module').then(mod => mod.SalesInvoiceCancellationModule)},
  {path:'paymentreceipt',loadChildren:()=> import('./spare-payment-receipt/spare-payment-receipt.module').then(mod=>mod.SparePaymentReceiptModule)},
  {path:'deliverychallan', loadChildren:()=> import('./spare-delivery-challan/spare-delivery-challan.module').then(mod=>mod.SpareDeliveryChallanModule)},
  {path:'deliverychallanreturn',loadChildren:()=>import('./spare-delivery-challan-return/spare-delivery-challan-return.module').then(mod=>mod.SpareDeliveryChallanReturnModule)},
  {path:'customerorder', loadChildren: () => import('./customer-order/customer-order.module').then(mod => mod.CustomerOrderModule) },
  {path:'partquotation', loadChildren: () => import('./parts-quotation/parts-quotation.module').then(mod => mod.PartsQuotationModule)},
  {path:'salesinvoicecancel', loadChildren: () => import('./sales-invoice-cancellation/sales-invoice-cancellation.module').then(mod => mod.SalesInvoiceCancellationModule)},
  {path:'picklist', loadChildren: () => import('./sales-pick-list/pick-list.module').then(mod => mod.PickListModule) },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CounterSalesRoutingModule { }