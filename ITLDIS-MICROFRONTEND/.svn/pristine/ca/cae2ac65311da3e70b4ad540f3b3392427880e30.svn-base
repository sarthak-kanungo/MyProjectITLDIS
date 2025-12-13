import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { CurrentStockPageComponent } from './component/current-stock-page/current-stock-page.component';
import { CurrentStockSearchPageComponent } from './component/current-stock-search-page/current-stock-search-page.component';

const routes: Routes = [
  { path: '', component: CurrentStockSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CurrentStockPageComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:id', component: CurrentStockPageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: CurrentStockPageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CurrentStockRoutingModule { }
