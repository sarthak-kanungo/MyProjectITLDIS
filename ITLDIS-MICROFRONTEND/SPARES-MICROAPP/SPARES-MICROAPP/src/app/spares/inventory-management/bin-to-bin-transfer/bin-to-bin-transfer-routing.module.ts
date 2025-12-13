import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { BtbtPageComponent } from './component/btbt-page/btbt-page.component';
import { BtbtSearchPageComponent } from './component/btbt-search-page/btbt-search-page.component';


const routes: Routes = [
  { path: '', component: BtbtSearchPageComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: BtbtPageComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:id', component: BtbtPageComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: BtbtPageComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BinToBinTransferRoutingModule { }
