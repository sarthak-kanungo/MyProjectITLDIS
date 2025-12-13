import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemPriceMasterSearchComponent } from './pages/item-price-master-search/item-price-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ItemPriceMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemPriceMasterRoutingModule { }
