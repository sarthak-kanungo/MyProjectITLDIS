import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductMasterSearchComponent } from './pages/product-master-search/product-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ProductMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductMasterRoutingModule { }
