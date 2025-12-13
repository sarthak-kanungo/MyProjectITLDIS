import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { BlockPartSaleSearchComponent } from './component/block-part-sale-search/block-part-sale-search.component';


const routes: Routes = [
  {path:'',component:BlockPartSaleSearchComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlockPartSaleRoutingModule { }
