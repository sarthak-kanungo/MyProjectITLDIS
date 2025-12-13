import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PcrSearchPageComponent } from './component/pcr-search-page/pcr-search-page.component';
import { PcrPageComponent } from './component/pcr-page/pcr-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '',component:PcrSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:PcrPageComponent, canActivate: [UserAccessGuard]},
  {path:'view', component:PcrPageComponent, canActivate: [UserAccessGuard]},
  {path:'edit', component:PcrPageComponent, canActivate: [UserAccessGuard]},
  {path:'jobCard', component:PcrPageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductConcernReportRoutingModule { }
