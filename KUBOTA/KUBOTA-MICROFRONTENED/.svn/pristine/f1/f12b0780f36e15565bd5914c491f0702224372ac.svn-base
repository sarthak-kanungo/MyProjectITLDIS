import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FailureCodeMasterSearchComponent } from './pages/failure-code-master-search/failure-code-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: FailureCodeMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FailureCodeMasterRoutingModule { }
