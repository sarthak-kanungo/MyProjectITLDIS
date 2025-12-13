import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FunctionMasterPageComponent } from './component/function-master-page/function-master-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path :'', component:FunctionMasterPageComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FunctionRoutingModule { }
