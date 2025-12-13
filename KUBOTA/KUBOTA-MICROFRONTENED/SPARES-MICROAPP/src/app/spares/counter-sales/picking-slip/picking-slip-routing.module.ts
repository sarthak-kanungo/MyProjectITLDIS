import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PickingSlipContainerComponent } from './container/picking-slip-container/picking-slip-container.component';
import { PickingSlipSearchComponent } from './container/picking-slip-search/picking-slip-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  {path:'', component:PickingSlipSearchComponent, canActivate: [UserAccessGuard]},
  {path:'create',component:PickingSlipContainerComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PickingSlipRoutingModule { }
