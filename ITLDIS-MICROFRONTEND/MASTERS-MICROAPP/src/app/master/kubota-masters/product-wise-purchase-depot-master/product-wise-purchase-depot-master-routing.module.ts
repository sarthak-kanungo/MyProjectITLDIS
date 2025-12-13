import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductWisePurchaseDepotMasterSearchComponent } from './pages/product-wise-purchase-depot-master-search/product-wise-purchase-depot-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ProductWisePurchaseDepotMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductWisePurchaseDepotMasterRoutingModule { }
