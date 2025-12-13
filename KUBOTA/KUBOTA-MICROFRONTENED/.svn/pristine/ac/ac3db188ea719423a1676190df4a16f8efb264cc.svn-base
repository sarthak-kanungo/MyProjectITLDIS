import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { branchTransferIssuePresenter } from '../../component/create-branch-transfer-issue/branch-transfer-issue-presenter';
import { branchTransferIssueStore } from '../../component/create-branch-transfer-issue/branch-transfer-issue-store';

@Component({
  selector: 'app-branch-transfer-issue-create',
  templateUrl: './branch-transfer-issue-create.component.html',
  styleUrls: ['./branch-transfer-issue-create.component.scss'],
  providers: [branchTransferIssuePresenter,branchTransferIssueStore]
})
export class BranchTransferIssueCreateComponent implements OnInit {
 branchTransferIssueForm:FormGroup

  isEdit: boolean;
  constructor(public dialog: MatDialog,private toaster:ToastrService,private presenter:branchTransferIssuePresenter) { }

  ngOnInit() {
    // this.branchTransferIssueForm = this.presenter.issueForm;
  }
  // validateForm(submitType:any) {
  //   if (this.branchTransferIssueForm.invalid) {
  //     this.branchTransferIssueForm.markAllAsTouched()
  //     this.toaster.error('Please fill all required field!')
  //     return;
  //   }
  //   console.log(this.branchTransferIssueForm)
  //   console.log(submitType,'validateForm')
  //   this.openConfirmDialog();
  // }

  // submitData() {
  // }
  // clearForm(){
  //   this.branchTransferIssueForm.reset()
  // }


  // private openConfirmDialog(): void | any {
  //   let message = 'Do you want to submit Branch Transfer Issue?';
  //   if (this.isEdit) {
  //     message = 'Do you want to update Branch Transfer Issue?';
  //   }
  //   const confirmationData = this.setConfirmationModalData(message);
  //   // //console.log ('confirmationData', confirmationData);
  //   const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
  //     width: '500px',
  //     panelClass: 'confirmation_modal',
  //     data: confirmationData
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed', result);
  //     if (result === 'Confirm' && !this.isEdit) {
  //       this.submitData();
  //     }
  //     if (result === 'Confirm' && this.isEdit) {
  //       this.submitData();
  //     }
  //   });
  // }
  // private setConfirmationModalData(message: string) {
  //   const confirmationData = {} as ConfirmDialogData;
  //   confirmationData.buttonAction = [] as Array<ButtonAction>;
  //   confirmationData.title = 'Confirmation';
  //   confirmationData.message = message;
  //   confirmationData.buttonName = ['Confirm', 'Cancel'];
  //   // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
  //   // confirmationData.buttonAction.push(this.cancelButtonAction());
  //   return confirmationData;
  // }

 
}
