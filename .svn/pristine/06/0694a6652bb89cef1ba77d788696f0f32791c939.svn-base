import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorkshopBillSearchComponent } from './component/workshop-bill-search/workshop-bill-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: WorkshopBillSearchComponent, canActivate:[UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkshopBillRoutingModule { }
