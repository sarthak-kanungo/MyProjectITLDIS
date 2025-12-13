import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LocalItemMasterSearchComponent } from './pages/local-item-master-search/local-item-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: LocalItemMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalItemMasterRoutingModule { }
