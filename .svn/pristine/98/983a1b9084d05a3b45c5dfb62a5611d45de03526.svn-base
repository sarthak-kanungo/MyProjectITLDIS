import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { CreateDelearCustomerCareExCallComponent } from './component/create-delear-customer-care-ex-call/create-delear-customer-care-ex-call.component';
import { SearchDelearCustomerCareExCallComponent } from './component/search-delear-customer-care-ex-call/search-delear-customer-care-ex-call.component';


const routes: Routes = [
  { path: '', component: SearchDelearCustomerCareExCallComponent, canActivate: [UserAccessGuard]},
  { path: 'create', component: CreateDelearCustomerCareExCallComponent, canActivate: [UserAccessGuard]},
  { path: 'view', component: CreateDelearCustomerCareExCallComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DelearCustomerCareExCallRoutingModule { }
