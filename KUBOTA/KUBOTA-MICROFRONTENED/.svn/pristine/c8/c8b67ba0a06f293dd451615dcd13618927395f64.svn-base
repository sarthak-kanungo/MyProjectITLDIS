import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HelpComponent } from './help/help.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { EmptyComponent } from './empty/empty.component';
import { spareRoutes } from './routes/spare-routes';
import { warrantyRoutes } from './routes/warranty-routes';
import { salePreSaleRoutes } from './routes/sale-pre-sale-routes';
import { masterRoutes } from './routes/master-routes';
import { serviceRoutes } from './routes/service-routes';
import { IframeComponent } from './iframe/iframe.component';
import { IFrameLayoutComponent } from './i-frame-layout/i-frame-layout.component';
import { SessionExpiredComponent } from './session-expired/session-expired.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'help', component: HelpComponent },
  { path: 'access-denied', component: AccessDeniedComponent },
  { path: 'Session-Expired', component: SessionExpiredComponent },
  
  { path: 'login', loadChildren: () => import('./login/login.module').then(mod => mod.LoginModule) },
  //{ path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(mod => mod.DashboardModule) },
  {
    path: 'dashboard', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'spares', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'sales-pre-sales', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'service', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'warranty', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'master', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'crm-training', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  {
    path: 'training', component: IFrameLayoutComponent, pathMatch: 'prefix',
    children: [
      { path: '**', component: IframeComponent },
    ]
  },
  // ...masterRoutes,
  // ...salePreSaleRoutes,
  // ...serviceRoutes,
  // ...spareRoutes,
  // ...warrantyRoutes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
