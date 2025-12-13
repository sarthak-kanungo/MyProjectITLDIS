import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { branchTransferIssueStore } from './branch-transfer-issue-store';
import { branchTransferIssuePresenter } from './branch-transfer-issue-presenter';

@Component({
  selector: 'app-create-branch-transfer-issue',
  templateUrl: './create-branch-transfer-issue.component.html',
  styleUrls: ['./create-branch-transfer-issue.component.scss'],
  providers:[branchTransferIssueStore,branchTransferIssuePresenter]
})
export class CreateBranchTransferIssueComponent implements OnInit {
  @Input() branchTransferIssueForm: FormGroup;
  
  isView: boolean;
  data: Object;
  disable = true
  branches: string[] = [
    'Branch 1', 'Branch 2'
  ];

  constructor(private issueStore:branchTransferIssueStore,private presenter:branchTransferIssuePresenter) { }

  ngOnInit() {
    // this.branchTransferIssueForm = this.presenter.issueForm;
  }

 

 
}