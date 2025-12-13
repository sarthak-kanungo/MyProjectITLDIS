import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceBookingSearchPageComponent } from './component/service-booking-search-page/service-booking-search-page.component';
import { ServiceBookingPageComponent } from './component/service-booking-page/service-booking-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ServiceBookingSearchPageComponent, canActivate:[UserAccessGuard] },
  { path: 'create', component: ServiceBookingPageComponent, canActivate:[UserAccessGuard] },
  { path: 'view/:id', component: ServiceBookingPageComponent, canActivate:[UserAccessGuard] },
  { path: 'edit/:id', component: ServiceBookingPageComponent, canActivate:[UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceBookingRoutingModule { }
