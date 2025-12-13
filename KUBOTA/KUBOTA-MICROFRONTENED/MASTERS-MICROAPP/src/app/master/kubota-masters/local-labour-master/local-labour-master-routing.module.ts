import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LocalLabourMasterSearchComponent } from './pages/local-labour-master-search/local-labour-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: LocalLabourMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalLabourMasterRoutingModule { }
