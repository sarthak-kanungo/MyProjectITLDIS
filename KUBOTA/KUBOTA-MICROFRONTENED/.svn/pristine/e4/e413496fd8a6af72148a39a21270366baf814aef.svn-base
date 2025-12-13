import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartReturnSearchPageComponent } from './component/part-return-search-page/part-return-search-page.component';
import { PartReturnPageComponent } from './component/part-return-page/part-return-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: PartReturnSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component: PartReturnPageComponent, canActivate: [UserAccessGuard]},
  {path:'view/:viewId', component: PartReturnPageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartReturnRoutingModule { }
