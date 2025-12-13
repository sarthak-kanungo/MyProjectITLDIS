import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferIssueRoutingModule } from './branch-transfer-issue-routing.module';
import { SearchBranchTransferIssueComponent } from './component/search-branch-transfer-issue/search-branch-transfer-issue.component';
import { BranchTransferIssuePageComponent } from './component/branch-transfer-issue-page/branch-transfer-issue-page.component';
import { BranchTransferItemDetailsComponent } from './component/branch-transfer-item-details/branch-transfer-item-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { UiModule } from 'src/app/ui/ui.module';
import { CreateBranchTransferIssueComponent } from './component/create-branch-transfer-issue/create-branch-transfer-issue.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';
import { MatSelectFilterModule } from 'mat-select-filter';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

@NgModule({
  declarations: [SearchBranchTransferIssueComponent, CreateBranchTransferIssueComponent,BranchTransferIssuePageComponent, BranchTransferItemDetailsComponent],
  imports: [
    CommonModule,
    BranchTransferIssueRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,

    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule,
    MatSelectFilterModule,
    NgMultiSelectDropDownModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]

})
export class BranchTransferIssueModule { }
