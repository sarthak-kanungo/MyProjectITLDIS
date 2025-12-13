import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BinningSlipContainerCreateComponent } from './container/binning-slip-container-create/binning-slip-container-create.component';
import { BinningSlipContainerSearchComponent } from './container/binning-slip-container-search/binning-slip-container-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:BinningSlipContainerSearchComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:BinningSlipContainerCreateComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BinningSlipRoutingModule { }
