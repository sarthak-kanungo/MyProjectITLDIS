import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManageApprovalHierarchyRoutingModule } from './manage-approval-hierarchy-routing.module';
import { SearchManageApprovalHierarchyComponent } from './search-manage-approval-hierarchy/search-manage-approval-hierarchy.component';
import { CreateManageApprovalHierarchyComponent } from './create-manage-approval-hierarchy/create-manage-approval-hierarchy.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';

@NgModule({
  declarations: [SearchManageApprovalHierarchyComponent, CreateManageApprovalHierarchyComponent],
  imports: [
    CommonModule,
    ManageApprovalHierarchyRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    NgswSearchTableModule
  ]
})
export class ManageApprovalHierarchyModule { }
