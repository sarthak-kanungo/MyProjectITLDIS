import { Component, OnInit } from '@angular/core';
import { DealerMasterPagePresenter } from './dealer-master-create.presenter';
import { DealerMasterPageStore } from './dealer-master-create.store';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-dealer-master-create',
  templateUrl: './dealer-master-create.component.html',
  styleUrls: ['./dealer-master-create.component.scss'],
  providers: [DealerMasterPageStore, DealerMasterPagePresenter]
})
export class DealerMasterCreateComponent implements OnInit {

  dealerMasterForm: FormGroup;
  dealerBankForm: FormGroup;
  dealerAddressForm: FormGroup;
  dealerTypeForm: FormGroup;
  dealerContactDetailForm: FormGroup;
  kaiRepresentativeForm: FormGroup;

  constructor(
    private dealerMasterPagePresenter: DealerMasterPagePresenter,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.allForms();
  }

  allForms() {
    this.dealerMasterForm = this.dealerMasterPagePresenter.dealerMaster;
    this.dealerBankForm = this.dealerMasterPagePresenter.dealerBank;
    this.dealerAddressForm = this.dealerMasterPagePresenter.dealerAddress;
    this.dealerTypeForm = this.dealerMasterPagePresenter.dealerType;
    this.dealerContactDetailForm = this.dealerMasterPagePresenter.dealerContactDetail;
    this.kaiRepresentativeForm = this.dealerMasterPagePresenter.kaiRepresentative;
  }

  validateForm() {

    this.openConfirmDialog();
  }

  submitData() {
  }

  private openConfirmDialog() {
    const message = 'Do you want to submit Dealer Master?';
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

}
