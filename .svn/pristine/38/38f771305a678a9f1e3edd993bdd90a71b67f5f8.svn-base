import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageOrganizationHierarchyRoutingModule } from './manage-organization-hierarchy-routing.module';
import { ManageOrganizationHierarchyCreateComponent } from './component/manage-organization-hierarchy-create/manage-organization-hierarchy-create.component';
import { ManageOrganizationHierarchySearchComponent } from './component/manage-organization-hierarchy-search/manage-organization-hierarchy-search.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from 'src/app/ui/ui.module';
import { ManageOrganizationHierarchyModalComponent } from './component/manage-organization-hierarchy-create/manage-organization-hierarchy-modal/manage-organization-hierarchy-modal.component';

@NgModule({
  declarations: [ManageOrganizationHierarchyCreateComponent, ManageOrganizationHierarchySearchComponent, ManageOrganizationHierarchyModalComponent],
  imports: [
    CommonModule,
    ManageOrganizationHierarchyRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    UiModule
  ],
  entryComponents: [
    ManageOrganizationHierarchyModalComponent
  ]
})
export class ManageOrganizationHierarchyModule { }
