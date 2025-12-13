import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
// import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { branchTransferIssuePresenter } from '../create-branch-transfer-issue/branch-transfer-issue-presenter';
import { branchTransferIssueStore } from '../create-branch-transfer-issue/branch-transfer-issue-store';
import { Location } from '@angular/common';
@Component({
  selector: 'app-create-branch-trans-issue-container',
  templateUrl: './create-branch-trans-issue-container.component.html',
  styleUrls: ['./create-branch-trans-issue-container.component.scss'],
  providers:[branchTransferIssuePresenter,branchTransferIssueStore]
})
export class CreateBranchTransIssueContainerComponent implements OnInit {

  branchTransferIssueForm:FormGroup
  itemForm: FormArray;
  isEdit: boolean;
  
  constructor(public dialog: MatDialog,private toaster:ToastrService,private presenter:branchTransferIssuePresenter,private store:branchTransferIssueStore,private location: Location) { }

  ngOnInit() {
   this.branchTransferIssueForm = this.presenter.issueForm;
    this.itemForm=this.presenter.addItems;
  
  }
  
  validateForm(submitType:any) {
    let flag: boolean = false;
    if (this.branchTransferIssueForm.invalid) {
      this.branchTransferIssueForm.markAllAsTouched()
      this.toaster.error('Please fill all required field!')
      return;
    }
    this.itemForm.controls.forEach((ele: any) => {
      if (ele.status == "INVALID") {
        this.toaster.error('Please fill all required field!')
        ele.markAllAsTouched()
        flag= true;
        return;
      }
    })
    if (flag) {
      return false;
    }
 
    this.openConfirmDialog();
  }

  submitData() {
    console.log(this.branchTransferIssueForm.value);
    console.log(this.itemForm.value)
    this.toaster.success("Data Save Sucessfully")
  }
  clearForm(){
    this.branchTransferIssueForm.reset()
    this.itemForm.reset()
  }


  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Branch Transfer Issue?';
    if (this.isEdit) {
      message = 'Do you want to update Branch Transfer Issue?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
  exit(){
    this.location.back()
  }
}
