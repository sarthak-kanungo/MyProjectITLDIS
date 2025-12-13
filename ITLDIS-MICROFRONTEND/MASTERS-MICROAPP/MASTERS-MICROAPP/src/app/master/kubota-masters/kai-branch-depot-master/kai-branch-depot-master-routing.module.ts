import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KaiBranchDepotMasterSearchComponent } from './pages/kai-branch-depot-master-search/kai-branch-depot-master-search.component';
import { KaiBranchDepotMasterCreateComponent } from './pages/kai-branch-depot-master-create/kai-branch-depot-master-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: KaiBranchDepotMasterSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: KaiBranchDepotMasterCreateComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KaiBranchDepotMasterRoutingModule { }
