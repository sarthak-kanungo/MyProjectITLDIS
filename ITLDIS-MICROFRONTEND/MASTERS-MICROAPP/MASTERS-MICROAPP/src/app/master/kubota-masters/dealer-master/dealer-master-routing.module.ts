import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealerMasterSearchComponent } from './component/dealer-master-search/dealer-master-search.component';
import { DealerMasterCreateComponent } from './component/dealer-master-create/dealer-master-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DealerMasterSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: DealerMasterCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: DealerMasterCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'edit', component: DealerMasterCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerMasterRoutingModule { }
