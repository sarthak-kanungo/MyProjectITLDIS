import { Component, OnInit } from '@angular/core';
import { FormCreatePageStore } from './form-create-page.store';
import { StoreCreatePagePresenter } from './store-create-page-presenter';
import { FormGroup } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-store-create-page',
  templateUrl: './store-create-page.component.html',
  styleUrls: ['./store-create-page.component.scss'],
  providers: [FormCreatePageStore, StoreCreatePagePresenter]
})
export class StoreCreatePageComponent implements OnInit {

  storeForm: FormGroup
  storeDetailsForm: FormGroup

  constructor(
    private storeCreatePagePresenter: StoreCreatePagePresenter,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.storeForm = this.storeCreatePagePresenter.storeCreateForm
    this.storeDetailsForm = this.storeCreatePagePresenter.storeForm
  }

  onClickSubmit() {
    this.openConfirmDialog()
  }

  submitData() {
    console.log(this.storeForm.getRawValue());
  }

  private openConfirmDialog(): void | any {
    let message = `Do you want to submit Store Master?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.submitData()
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

}