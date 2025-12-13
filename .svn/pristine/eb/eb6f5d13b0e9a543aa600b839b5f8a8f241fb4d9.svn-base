import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealerUserSearchComponent } from './pages/dealer-user-search/dealer-user-search.component';
import { DealerUserCreateComponent } from './pages/dealer-user-create/dealer-user-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DealerUserSearchComponent ,canActivate: [UserAccessGuard]},
  { path: 'create', component: DealerUserCreateComponent,canActivate: [UserAccessGuard]},
  { path: 'view', component: DealerUserCreateComponent, canActivate:[UserAccessGuard]},
  { path: 'edit', component: DealerUserCreateComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerUserRoutingModule { }
