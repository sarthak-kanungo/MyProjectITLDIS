import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserAccessGuard } from '../auth/user-access.guard';
import { DashboardComponent } from './dashboard.component';

const routes: Routes = [{path:'', component: DashboardComponent},
  {path:'Sales', component: DashboardComponent, canActivate: [UserAccessGuard]},
  {path:'Service', component: DashboardComponent, canActivate: [UserAccessGuard]},
  {path:'Warranty', component: DashboardComponent, canActivate: [UserAccessGuard]},
  {path:'Marketing', component: DashboardComponent, canActivate: [UserAccessGuard]},
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
  
 }
