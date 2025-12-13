import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchOrderPlanningSheetComponent } from './search-order-planning-sheet/search-order-planning-sheet.component';
import { OrderPlanningSheetPageComponent } from './order-planning-sheet-page/order-planning-sheet-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchOrderPlanningSheetComponent,canActivate: [UserAccessGuard]},
  {path:'create',component:OrderPlanningSheetPageComponent,canActivate: [UserAccessGuard]},
  {path:'view',component:OrderPlanningSheetPageComponent,canActivate: [UserAccessGuard]},
  {path:'edit',component:OrderPlanningSheetPageComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderPlanningSheetRoutingModule { }
