import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeliveryChallanSearchComponent } from './pages/delivery-challan-search/delivery-challan-search.component';
import { DeliveryChallanCreateComponent } from './pages/delivery-challan-create/delivery-challan-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: DeliveryChallanSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'update/:editId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'cancel/:cancelId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'approve/:approveId', component: DeliveryChallanCreateComponent, canActivate: [UserAccessGuard] }
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeliveryChallanRoutingModule { }
