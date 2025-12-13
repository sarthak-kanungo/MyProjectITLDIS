import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AuthGuard } from '../auth/auth.guard';
import { NotFoundDataComponent } from './pages/not-found-data/not-found-data.component';


const routes: Routes = [
  { path: '', component: DashboardComponent, canActivate :[AuthGuard] },
  { path: 'notdata', component: NotFoundDataComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
