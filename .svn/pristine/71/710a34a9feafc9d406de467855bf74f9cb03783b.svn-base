import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DutyStructureMasterSearchComponent } from './pages/duty-structure-master-search/duty-structure-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DutyStructureMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DutyStructureMasterRoutingModule { }
