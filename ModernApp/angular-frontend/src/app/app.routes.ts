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
        path: 'services/job-cards',
        loadComponent: () => import('./features/dashboard/pages/services-module/services-module.component').then(m => m.ServicesModuleComponent)
      },
      {
        path: 'services/invoices',
        loadComponent: () => import('./features/dashboard/pages/services-module/services-module.component').then(m => m.ServicesModuleComponent)
      },
      {
        path: 'services/schedules',
        loadComponent: () => import('./features/dashboard/pages/services-module/services-module.component').then(m => m.ServicesModuleComponent)
      },
      {
        path: 'services/warranty',
        loadComponent: () => import('./features/dashboard/pages/services-module/services-module.component').then(m => m.ServicesModuleComponent)
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

