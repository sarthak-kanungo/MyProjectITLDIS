import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-new-parts-claim',
  templateUrl: './new-parts-claim.component.html',
  styleUrls: ['./new-parts-claim.component.scss']
})
export class NewPartsClaimComponent implements OnInit {
  isEdit: boolean;

  customers : any[] = ['Discrepancy Claim','MRR'];

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit New Parts Claim';
  if (this.isEdit) {
    message = 'Do you want to update New Parts Claim';
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
