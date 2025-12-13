import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KubotaUserSearchPageComponent } from './component/kubota-user-search-page/kubota-user-search-page.component';
import { KubotaUserCreatePageComponent } from './component/kubota-user-create-page/kubota-user-create-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
// import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: KubotaUserSearchPageComponent,canActivate: [UserAccessGuard]},
  { path: 'create', component: KubotaUserCreatePageComponent,canActivate: [UserAccessGuard]},
  { path: 'view/:id', component: KubotaUserCreatePageComponent,canActivate: [UserAccessGuard]},
  { path: 'edit/:id', component: KubotaUserCreatePageComponent,canActivate: [UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KubotaUserRoutingModule { }
