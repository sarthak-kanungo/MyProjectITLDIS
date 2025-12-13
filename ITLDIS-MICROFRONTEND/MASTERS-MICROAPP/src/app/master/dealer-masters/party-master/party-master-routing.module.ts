import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { PartyMasterSearchPageComponent } from './component/party-master-search-page/party-master-search-page.component';
import { PartyMasterPageComponent } from './component/party-master-page/party-master-page.component';


const routes: Routes = [
  { path: '', component: PartyMasterSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: PartyMasterPageComponent,canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: PartyMasterPageComponent,canActivate: [UserAccessGuard] },
  { path: 'edit/:id', component: PartyMasterPageComponent,canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartyMasterRoutingModule { }
