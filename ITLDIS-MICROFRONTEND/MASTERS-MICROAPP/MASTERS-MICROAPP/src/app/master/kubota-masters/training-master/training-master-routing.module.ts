import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingMasterSearchComponent } from './pages/training-master-search/training-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: TrainingMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingMasterRoutingModule { }
