import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageOrganizationHierarchyCreateComponent } from './component/manage-organization-hierarchy-create/manage-organization-hierarchy-create.component';
import { ManageOrganizationHierarchySearchComponent } from './component/manage-organization-hierarchy-search/manage-organization-hierarchy-search.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: ManageOrganizationHierarchyCreateComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: ManageOrganizationHierarchyCreateComponent,canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageOrganizationHierarchyRoutingModule { }
