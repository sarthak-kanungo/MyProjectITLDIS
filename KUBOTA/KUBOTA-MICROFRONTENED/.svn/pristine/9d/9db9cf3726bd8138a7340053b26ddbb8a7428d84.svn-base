import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-branch-sub-dealer-details',
  templateUrl: './branch-sub-dealer-details.component.html',
  styleUrls: ['./branch-sub-dealer-details.component.scss']
})
export class BranchSubDealerDetailsComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;

  categories: string[] = [
    'Branch', 'Sub-Dealer',
  ];

  titles: string[] = [
    'Mr.', 'Miss', 'Mrs.'
  ];
  branchSubDealerDetailsForm: FormGroup;
  constructor( private fb: FormBuilder,public dialog: MatDialog) { }

  ngOnInit() {
    this.createbranchSubDealerDetailsForm();
  }

  createbranchSubDealerDetailsForm() {
    this.branchSubDealerDetailsForm = this.fb.group({
      category: ['', Validators.compose([Validators.required])],
      branchSubDealerCode: ['', Validators.compose([])],
      subDealerName: ['', Validators.compose([Validators.required])],
      location: ['', Validators.compose([Validators.required])],
      subDealerGstNo: ['', Validators.compose([])],
      subDealerPanNo: ['', Validators.compose([])],
      subDealeraadharCardNo: ['', Validators.compose([])],
      contactPerson: ['', Validators.compose([])],
      title: ['', Validators.compose([Validators.required])],
      firstName: ['', Validators.compose([Validators.required])],
      middleName: ['', Validators.compose([])],
      lastName: ['', Validators.compose([])],
      designation: ['', Validators.compose([])],
      mobile: ['', Validators.compose([Validators.required])],
      emailId: ['', Validators.compose([])],
    })
  }


  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Branch/ Sub-Dealer Master?';
  if (this.isEdit) {
    message = 'Do you want to update Branch/ Sub-Dealer Master?';
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
