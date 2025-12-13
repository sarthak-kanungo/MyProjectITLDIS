import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityTypeBudgetComponent } from './pages/activity-type-budget/activity-type-budget.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AddNewBudgetComponent } from './component/add-new-budget/add-new-budget.component';


const routes: Routes = [
  { path: '', component: ActivityTypeBudgetComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: AddNewBudgetComponent,canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityTypeBudgetRoutingModule { }
