import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferIssueRoutingModule } from './branch-transfer-issue-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BranchTransferIssueComponent } from './pages/branch-transfer-issue/branch-transfer-issue.component';
import { BranchTransferIssueCreateComponent } from './pages/branch-transfer-issue-create/branch-transfer-issue-create.component';
import { CreateBranchTransferIssueComponent } from './component/create-branch-transfer-issue/create-branch-transfer-issue.component';
import { MaterialModule } from '../../../app.module';
import { SearchBranchTransferIssueComponent } from './component/search-branch-transfer-issue/search-branch-transfer-issue.component';
import { CreateBranchTransIssueContainerComponent } from './component/create-branch-trans-issue-container/create-branch-trans-issue-container.component';
import { BranchTransferIssueItemDetailsComponent } from './component/branch-transfer-issue-item-details/branch-transfer-issue-item-details.component';


@NgModule({
  declarations: [BranchTransferIssueComponent, BranchTransferIssueCreateComponent, CreateBranchTransferIssueComponent,SearchBranchTransferIssueComponent, CreateBranchTransIssueContainerComponent, BranchTransferIssueItemDetailsComponent],
  imports: [
    CommonModule,
    BranchTransferIssueRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
  ]
})
export class BranchTransferIssueModule { }
