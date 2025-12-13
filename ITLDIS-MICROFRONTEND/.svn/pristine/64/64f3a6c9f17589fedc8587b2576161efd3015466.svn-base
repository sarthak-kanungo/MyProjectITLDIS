import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserAccessGuard } from './auth/user-access.guard';

const routes: Routes = [{path:'dashboard', loadChildren:()=> import ('./dashboard/dashboard.module').then(mod=>mod.DashboardModule)}];
//, canActivate: [UserAccessGuard]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
