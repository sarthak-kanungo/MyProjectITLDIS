import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreatePdiComponent } from './component/pdi-page/create-pdi.component';
import { PdiSearchPageComponent } from './component/pdi-search-page/pdi-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: PdiSearchPageComponent, canActivate:[UserAccessGuard] },
  { path: 'create', component: CreatePdiComponent, canActivate:[UserAccessGuard] },
  { path: 'edit', component: CreatePdiComponent, canActivate:[UserAccessGuard] },
  { path: 'view', component: CreatePdiComponent, canActivate:[UserAccessGuard] }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreDeliveryInspectionRoutingModule { }
