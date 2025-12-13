import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BudgetHeadMasterSearchComponent } from './pages/budget-head-master-search/budget-head-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: BudgetHeadMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BudgetHeadMasterRoutingModule { }
