import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineMasterSearchComponent } from './pages/machine-master-search/machine-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MachineMasterSearchComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineMasterRoutingModule { }
