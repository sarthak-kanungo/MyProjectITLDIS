import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateNonMovmentComponent } from './create-non-movment/create-non-movment.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:CreateNonMovmentComponent,canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NonMovmentInventoryRoutingModule { }
