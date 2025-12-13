import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { itldisSourceMasterSearchComponent } from './pages/itldis-source-master-search/itldis-source-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: itldisSourceMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class itldisSourceMasterRoutingModule { }
