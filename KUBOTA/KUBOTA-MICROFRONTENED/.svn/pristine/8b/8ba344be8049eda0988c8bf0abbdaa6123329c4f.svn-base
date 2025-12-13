import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { BlockMachineSaleSearchComponent } from './component/block-machine-sale-search/block-machine-sale-search.component';


const routes: Routes = [
  {path:'',component:BlockMachineSaleSearchComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlocksMachineSaleRoutingModule { }
