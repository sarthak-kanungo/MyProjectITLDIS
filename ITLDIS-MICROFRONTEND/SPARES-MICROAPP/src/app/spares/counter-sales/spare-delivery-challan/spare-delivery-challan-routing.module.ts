import { SpareDeliveryChallanComponent } from './component/spare-delivery-challan/spare-delivery-challan.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeliveryChallanSearchContainerComponent } from './container/delivery-challan-search-container/delivery-challan-search-container.component';
import { DeliveryChallanContainerComponent } from './container/delivery-challan-container/delivery-challan-container.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'',component:DeliveryChallanSearchContainerComponent, canActivate: [UserAccessGuard]},
{path:'create',component:DeliveryChallanContainerComponent, canActivate: [UserAccessGuard]}
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpareDeliveryChallanRoutingModule { }
