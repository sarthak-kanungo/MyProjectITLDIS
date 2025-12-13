import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-binning-slip',
  templateUrl: './binning-slip.component.html',
  styleUrls: ['./binning-slip.component.scss']
})
export class BinningSlipComponent implements OnInit {
  isEdit: boolean;

  constructor( public dialog: MatDialog) { }

  ngOnInit() {
  }
  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Binning Slip?';
  if (this.isEdit) {
    message = 'Do you want to update Binning Slip?';
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
