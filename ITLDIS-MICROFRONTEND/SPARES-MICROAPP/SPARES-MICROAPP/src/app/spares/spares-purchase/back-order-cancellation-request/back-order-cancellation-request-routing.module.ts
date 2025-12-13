import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBackOrderCancellationComponent } from './component/search-back-order-cancellation/search-back-order-cancellation.component';
import { CreateBackOrderCancellationPageComponent } from './component/create-back-order-cancellation-page/create-back-order-cancellation-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchBackOrderCancellationComponent,canActivate:[UserAccessGuard]},
  {path:'create',component:CreateBackOrderCancellationPageComponent, canActivate:[UserAccessGuard]},
  {path:'edit',component:CreateBackOrderCancellationPageComponent,canActivate:[UserAccessGuard]},
  {path:'view',component:CreateBackOrderCancellationPageComponent,canActivate:[UserAccessGuard]},
  {path:'approve',component:CreateBackOrderCancellationPageComponent,canActivate:[UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackOrderCancellationRequestRoutingModule { }
