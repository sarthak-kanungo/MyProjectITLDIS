import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-branch-transfer-receipt-create',
  templateUrl: './branch-transfer-receipt-create.component.html',
  styleUrls: ['./branch-transfer-receipt-create.component.scss']
})
export class BranchTransferReceiptCreateComponent implements OnInit {

  isEdit: boolean;
  constructor(public dialog: MatDialog,) { }

  ngOnInit() {
  }
  validateForm() {

    this.openConfirmDialog();
  }

  submitData() {
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Branch Transfer Receipt?';
    if (this.isEdit) {
      message = 'Do you want to update Branch Transfer Receipt?';
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
