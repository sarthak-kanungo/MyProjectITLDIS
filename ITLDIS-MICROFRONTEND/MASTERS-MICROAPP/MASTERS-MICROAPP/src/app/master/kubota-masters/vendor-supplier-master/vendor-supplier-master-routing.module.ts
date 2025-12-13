import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VendorSupplierMasterSearchComponent } from './pages/vendor-supplier-master-search/vendor-supplier-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: VendorSupplierMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VendorSupplierMasterRoutingModule { }
