import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-po-search-result',
  templateUrl: './po-search-result.component.html',
  styleUrls: ['./po-search-result.component.scss']
})
export class PoSearchResultComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  selected = 10;
  recordPerPageList: Array<number> = [5, 10, 25, 50];

  btnName = "Approve"

  constructor(
    public dialog: MatDialog) { }

  ngOnInit() {
  }

  validateForm() {
   
    this.openConfirmDialog();
}

submitData() {
}

private openConfirmDialog(): void | any {
  let message = 'Are you sure you want to Approve or Reject?';
  if (this.isEdit) {
    message = 'Are you sure you want to Approve or Reject?';
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
    if (result === 'Reject' && !this.isEdit) {
      this.submitData();
     this.btnName = "Rejected"
    }
    // if (result === 'approve' && !this.isEdit) {
    //   this.submitData();
    //  this.btnName = "approve"
    // }
    if (result === 'Approve' && !this.isEdit) {
      this.submitData();
      this.btnName = "Approved"
    }
  });
}
private setConfirmationModalData(message: string) {
  const confirmationData = {} as ConfirmDialogData;
  confirmationData.buttonAction = [] as Array<ButtonAction>;
  confirmationData.title = 'Confirmation';
  confirmationData.message = message;
  confirmationData.buttonName = ['Approve', 'Reject'];
  // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
  // confirmationData.buttonAction.push(this.cancelButtonAction());
  return confirmationData;
}

}
