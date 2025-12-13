import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { AssignOrgHierarchyToDealerCreateComponent } from './pages/assign-org-hierarchy-to-dealer-create/assign-org-hierarchy-to-dealer-create.component';
import { AssignOrgHierarchyToDealerSearchComponent } from './pages/assign-org-hierarchy-to-dealer-search/assign-org-hierarchy-to-dealer-search.component';


const routes: Routes = [
  { path: '', component: AssignOrgHierarchyToDealerSearchComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: AssignOrgHierarchyToDealerCreateComponent,canActivate: [UserAccessGuard]},
  { path: 'view/:id', component: AssignOrgHierarchyToDealerCreateComponent, canActivate:[UserAccessGuard]},
  { path: 'edit/:id', component: AssignOrgHierarchyToDealerCreateComponent, canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignOrgHierarchyToDealerRoutingModule { }
