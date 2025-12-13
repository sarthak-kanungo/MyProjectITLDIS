import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BranchSubDealerMasterSearchComponent } from './pages/branch-sub-dealer-master-search/branch-sub-dealer-master-search.component';
import { BranchSubDealerMasterCreateComponent } from './pages/branch-sub-dealer-master-create/branch-sub-dealer-master-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: BranchSubDealerMasterSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: BranchSubDealerMasterCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BranchSubDealerMasterRoutingModule { }
