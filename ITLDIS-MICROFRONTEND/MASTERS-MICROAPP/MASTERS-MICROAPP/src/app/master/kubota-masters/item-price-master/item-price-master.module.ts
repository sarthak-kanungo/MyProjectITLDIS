import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemPriceMasterRoutingModule } from './item-price-master-routing.module';
import { ItemPriceMasterSearchComponent } from './pages/item-price-master-search/item-price-master-search.component';


@NgModule({
  declarations: [ItemPriceMasterSearchComponent],
  imports: [
    CommonModule,
    ItemPriceMasterRoutingModule
  ]
})
export class ItemPriceMasterModule { }
