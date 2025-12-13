import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductWisePurchaseDepotMasterRoutingModule } from './product-wise-purchase-depot-master-routing.module';
import { ProductWisePurchaseDepotMasterSearchComponent } from './pages/product-wise-purchase-depot-master-search/product-wise-purchase-depot-master-search.component';


@NgModule({
  declarations: [ProductWisePurchaseDepotMasterSearchComponent],
  imports: [
    CommonModule,
    ProductWisePurchaseDepotMasterRoutingModule
  ]
})
export class ProductWisePurchaseDepotMasterModule { }
