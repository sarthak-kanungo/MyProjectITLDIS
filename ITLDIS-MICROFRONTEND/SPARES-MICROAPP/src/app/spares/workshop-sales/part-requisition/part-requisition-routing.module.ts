import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartRequisitionSearchPageComponent } from './component/part-requisition-search-page/part-requisition-search-page.component';
import { PartRequisitionPageComponent } from './component/part-requisition-page/part-requisition-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: PartRequisitionSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: PartRequisitionPageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: PartRequisitionPageComponent, canActivate: [UserAccessGuard] },
  { path: 'update/:updateId', component: PartRequisitionPageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartRequisitionRoutingModule { }
