import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllServiceCheckListMasterSearchComponent } from './pages/all-service-check-list-master-search/all-service-check-list-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: AllServiceCheckListMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AllServiceCheckListMasterRoutingModule { }
