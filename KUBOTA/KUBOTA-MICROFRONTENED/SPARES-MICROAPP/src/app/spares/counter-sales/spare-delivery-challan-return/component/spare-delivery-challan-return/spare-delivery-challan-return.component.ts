import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-spare-delivery-challan-return',
  templateUrl: './spare-delivery-challan-return.component.html',
  styleUrls: ['./spare-delivery-challan-return.component.scss']
})
export class SpareDeliveryChallanReturnComponent implements OnInit {
  isEdit: boolean;
  constructor(public dialog: MatDialog,private fb:FormBuilder) { }

  ngOnInit() {
    this.createDeliveryChallanReturnForm()
  }
  deliveryChallanReturnForm:FormGroup

  createDeliveryChallanReturnForm() {
    this.deliveryChallanReturnForm = this.fb.group({
      retDelChallanNo: ['', Validators.compose([])],
      delChallanNo: ['', Validators.compose([])],
      date: [{ value: '', disabled: true }, Validators.compose([])],
      invDate: [{ value: '', disabled: true }, Validators.compose([])],
      cusType: [{ value: '', disabled: true }, Validators.compose([])],
      refDocument: [{ value: '', disabled: true }, Validators.compose([])],
      salesType: [{ value: '', disabled: true }, Validators.compose([])],
      custName: [{ value: '', disabled: true }, Validators.compose([])],
      custAddr1: [{ value: '', disabled: true }, Validators.compose([])],
      custAddr2: [{ value: '', disabled: true }, Validators.compose([])],
      //refDocDate: [{ value: '', disabled: true }, Validators.compose([])],
      transporter: [{ value: '', disabled: true }, Validators.compose([])],
      tahsilTalMandal: [{ value: '', disabled: true }, Validators.compose([])],
      district: [{ value: '', disabled: true }, Validators.compose([])],
      pricing: [{ value: '', disabled: true }, Validators.compose([])],
      lrNo: [{ value: '', disabled: true }, Validators.compose([])],
      modeOfTRansport: [{ value: '', disabled: true }, Validators.compose([])],
      state: [{ value: '', disabled: true }, Validators.compose([])],
      pinCode: [{ value: '', disabled: true }, Validators.compose([])],
      mob: [{ value: '', disabled: true }, Validators.compose([])],
      custCode: [{ value: '', disabled: true }, Validators.compose([])],
      cityvillage: [{ value: '', disabled: true }, Validators.compose([])]

})
  }

  validateForm() {
    this.openConfirmDialog();
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Delivery Challan Return?';
    if (this.isEdit) {
      message = 'Do you want to update Delivery Challan Return?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

   /* dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
      }
    });*/
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
