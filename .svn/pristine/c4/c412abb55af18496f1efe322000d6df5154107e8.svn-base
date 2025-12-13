import { Component, OnInit } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { SubmitBranchTransferRequestService } from './submit-branch-transfer-request.service';

@Component({
  selector: 'app-branch-transfer-request-create',
  templateUrl: './branch-transfer-request-create.component.html',
  styleUrls: ['./branch-transfer-request-create.component.scss'],
  providers :[SubmitBranchTransferRequestService]
})
export class BranchTransferRequestCreateComponent implements OnInit {
  isEdit: any;
  
  constructor(public dialog: MatDialog,
    private submitBranchTransferRequestService : SubmitBranchTransferRequestService) { }

  ngOnInit() {
  }
  validateForm() {

    this.openConfirmDialog();
  }

  submitData() { }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Branch Transfer Request?';
    if (this.isEdit) {
      message = 'Do you want to update Branch Transfer Request?';
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

}
