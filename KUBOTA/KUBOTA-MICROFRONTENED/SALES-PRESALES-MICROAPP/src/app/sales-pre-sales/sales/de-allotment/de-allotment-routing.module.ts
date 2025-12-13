import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeAllotmentSearchComponent } from './pages/de-allotment-search/de-allotment-search.component';
import { DeAllotmentCreateComponent } from './pages/de-allotment-create/de-allotment-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DeAllotmentSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: DeAllotmentCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'deallot/:allotmentId', component: DeAllotmentCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: DeAllotmentCreateComponent, canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeAllotmentRoutingModule {

}