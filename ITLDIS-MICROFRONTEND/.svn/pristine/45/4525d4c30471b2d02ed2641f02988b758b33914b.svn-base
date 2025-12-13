import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KubotaSourceMasterSearchComponent } from './pages/kubota-source-master-search/kubota-source-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: KubotaSourceMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KubotaSourceMasterRoutingModule { }
