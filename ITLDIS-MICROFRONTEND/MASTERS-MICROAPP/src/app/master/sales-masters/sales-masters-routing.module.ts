import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'updatevehicleregistration', loadChildren: () => import('./update-vehicle-registration/update-vehicle-registration.module').then(mod => mod.UpdateVehicleRegistrationModule) },
  { path: 'activityTypeBudget', loadChildren: () => import('./Marketing-activity-cum-budget-master/activity-type-budget.module').then(mod => mod.ActivityTypeBudgetModule) },
  { path: 'sourceMaster', loadChildren: () => import('./source-master/source-master.module').then(mod => mod.SourceMasterModule) },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesMastersRoutingModule { }
