import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LogoutGuard } from '../auth/logout.guard';


const routes: Routes = [
  {path:'',component:LoginComponent, canActivate:[LogoutGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
