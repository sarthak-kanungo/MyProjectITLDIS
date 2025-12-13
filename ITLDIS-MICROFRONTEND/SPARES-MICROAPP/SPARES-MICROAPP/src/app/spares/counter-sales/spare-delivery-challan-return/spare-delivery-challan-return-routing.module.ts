import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeliveryChallanReturnSearchContainerComponent } from './container/delivery-challan-return-search-container/delivery-challan-return-search-container.component';
import { DeliveryChallanReturnContainerComponent } from './container/delivery-challan-return-container/delivery-challan-return-container.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'',component:DeliveryChallanReturnSearchContainerComponent, canActivate: [UserAccessGuard]},
  {path:'create',component:DeliveryChallanReturnContainerComponent, canActivate: [UserAccessGuard]}

  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpareDeliveryChallanReturnRoutingModule { }
