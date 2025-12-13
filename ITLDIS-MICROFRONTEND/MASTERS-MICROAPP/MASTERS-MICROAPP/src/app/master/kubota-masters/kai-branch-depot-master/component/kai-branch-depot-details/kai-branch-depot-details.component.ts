import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-kai-branch-depot-details',
  templateUrl: './kai-branch-depot-details.component.html',
  styleUrls: ['./kai-branch-depot-details.component.scss']
})
export class KaiBranchDepotDetailsComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;
  branchCodes: string[] = [
    'Chennai', 'Pune',
  ];
  status: string[] = [
    'ACTIVE', 'INACTIVE', 
  ];

  depots: string[] = [
    'Chennai', 'Odisha'
  ];
  branchDepotMasterForm: FormGroup;
  constructor( private fb: FormBuilder,public dialog: MatDialog) { }

  ngOnInit() {
    this.createbranchDepotMasterForm();
  }

  createbranchDepotMasterForm() {
    this.branchDepotMasterForm = this.fb.group({
      
      branchCode: ['', Validators.compose([])],
      branchName: [{value:'', disabled:true}, Validators.compose([])],
      depot: ['', Validators.compose([])],
      gstNo: [{value:'', disabled:true}, Validators.compose([])],
      fax: [{value:'', disabled:true}, Validators.compose([])],
      status: ['', Validators.compose([Validators.required])],
      availableStorageArea: ['', Validators.compose([Validators.required])],
      contactNo: [{value:'', disabled:true}, Validators.compose([])],
      contactEmail: [{value:'', disabled:true}, Validators.compose([])],
    })
  }

  validateForm() {

    this.openConfirmDialog();
  }

  submitData() {
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Branch/ Depot Master?';
    if (this.isEdit) {
      message = 'Do you want to update Branch/ Depot Master?';
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
