import { Routes } from '@angular/router';
import { ServicesModuleComponent } from './services-module.component';

export const routes: Routes = [
  {
    path: '',
    component: ServicesModuleComponent
  },
  {
    path: 'pending-installation',
    loadComponent: () => import('./installation/pending-installation/pending-installation.component').then(m => m.PendingInstallationComponent)
  }
];

