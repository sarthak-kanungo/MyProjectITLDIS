import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { loadRemoteModule } from '@angular-architects/module-federation';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [authGuard],
    children: [
      {
        path: '',
        redirectTo: 'services',
        pathMatch: 'full'
      },
      {
        path: 'services',
        loadComponent: () => import('./features/dashboard/pages/services-module/services-module.component').then(m => m.ServicesModuleComponent)
      },
      {
        path: 'services/pending-pdi',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/pending-pdi/pending-pdi.component').then(m => m.PendingPdiComponent)
      },
      {
        path: 'services/view-pdi',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/view-pdi/view-pdi.component').then(m => m.ViewPdiComponent)
      },
      {
        path: 'services/pending-installation',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/pending-installation/pending-installation.component').then(m => m.PendingInstallationComponent)
      },
      {
        path: 'services/view-installation',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/view-installation/view-installation.component').then(m => m.ViewInstallationComponent)
      },
      {
        path: 'services/create-job-card',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/create-job-card/create-job-card.component').then(m => m.CreateJobCardComponent)
      },
      {
        path: 'services/view-job-cards',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/view-job-cards/view-job-cards.component').then(m => m.ViewJobCardsComponent)
      },
      {
        path: 'services/pending-closure',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/pending-closure/pending-closure.component').then(m => m.PendingClosureComponent)
      },
      {
        path: 'services/chassis-validation',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/chassis-validation/chassis-validation.component').then(m => m.ChassisValidationComponent)
      },
      {
        path: 'services/reopen-job-card',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/reopen-job-card/reopen-job-card.component').then(m => m.ReopenJobCardComponent)
      },
      {
        path: 'services/generate-invoice',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/generate-invoice/generate-invoice.component').then(m => m.GenerateInvoiceComponent)
      },
      {
        path: 'services/search-history',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/search-history/search-history.component').then(m => m.SearchHistoryComponent)
      },
      {
        path: 'services/reminder',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/service-reminder/service-reminder.component').then(m => m.ServiceReminderComponent)
      },
      {
        path: 'services/due-date',
        loadComponent: () => import('./features/dashboard/pages/services-module/pages/service-due-date/service-due-date.component').then(m => m.ServiceDueDateComponent)
      },
      {
        path: 'spares',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
      },
      {
        path: 'spares/view-inventory',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-inventory/view-inventory.component').then(m => m.ViewInventoryComponent)
      },
      {
        path: 'spares/counter-sale',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/counter-sale/counter-sale.component').then(m => m.CounterSaleComponent)
      },
      {
        path: 'spares/add-inventory',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/add-inventory/add-inventory.component').then(m => m.AddInventoryComponent)
      },
      {
        path: 'spares/counter-sales-return',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/counter-sales-return/counter-sales-return.component').then(m => m.CounterSalesReturnComponent)
      },
      {
        path: 'spares/transaction-details',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/transaction-details/transaction-details.component').then(m => m.TransactionDetailsComponent)
      },
      {
        path: 'spares/view-invoice',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-invoice/view-invoice.component').then(m => m.ViewInvoiceComponent)
      },
      {
        path: 'spares/view-stock-ledger',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-stock-ledger/view-stock-ledger.component').then(m => m.ViewStockLedgerComponent)
      },
      {
        path: 'spares/manage-reorder-level',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/manage-reorder-level/manage-reorder-level.component').then(m => m.ManageReorderLevelComponent)
      },
      {
        path: 'spares/view-reorder-level',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-reorder-level/view-reorder-level.component').then(m => m.ViewReorderLevelComponent)
      },
      {
        path: 'spares/add-reorder-parts',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/add-reorder-parts/add-reorder-parts.component').then(m => m.AddReorderPartsComponent)
      },
      {
        path: 'spares/create-new-order',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/create-new-order/create-new-order.component').then(m => m.CreateNewOrderComponent)
      },
      {
        path: 'spares/create-new-order-vor',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/create-new-order-vor/create-new-order-vor.component').then(m => m.CreateNewOrderVorComponent)
      },
      {
        path: 'spares/view-order',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-order/view-order.component').then(m => m.ViewOrderComponent)
      },
      {
        path: 'spares/pending-cancelled-lines',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/pending-cancelled-lines/pending-cancelled-lines.component').then(m => m.PendingCancelledLinesComponent)
      },
      {
        path: 'spares/view-purchase-order-invoice',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/view-purchase-order-invoice/view-purchase-order-invoice.component').then(m => m.ViewPurchaseOrderInvoiceComponent)
      },
      {
        path: 'spares/create-grn',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/create-grn/create-grn.component').then(m => m.CreateGrnComponent)
      },
      {
        path: 'spares/back-order-report',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/back-order-report/back-order-report.component').then(m => m.BackOrderReportComponent)
      },
      {
        path: 'spares/order-details-report',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/order-details-report/order-details-report.component').then(m => m.OrderDetailsReportComponent)
      },
      {
        path: 'spares/invoice-details-report',
        loadComponent: () => import('./features/dashboard/pages/spares-module/pages/invoice-details-report/invoice-details-report.component').then(m => m.InvoiceDetailsReportComponent)
      }
    ]
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/login'
  }
];

