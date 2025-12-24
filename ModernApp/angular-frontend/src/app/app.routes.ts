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
        redirectTo: 'services/pending-pdi',
        pathMatch: 'full'
      },
      {
        path: 'services',
        redirectTo: 'services/pending-pdi',
        pathMatch: 'full'
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
        path: 'spares/invoices',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
      },
      {
        path: 'spares/sales-orders',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
      },
      {
        path: 'spares/purchase-orders',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
      },
      {
        path: 'spares/inventory',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
      },
      {
        path: 'spares/grn',
        loadComponent: () => import('./features/dashboard/pages/spares-module/spares-module.component').then(m => m.SparesModuleComponent)
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

