import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductMasterRoutingModule } from './product-master-routing.module';
import { ProductMasterSearchComponent } from './pages/product-master-search/product-master-search.component';


@NgModule({
  declarations: [ProductMasterSearchComponent],
  imports: [
    CommonModule,
    ProductMasterRoutingModule
  ]
})
export class ProductMasterModule { }
