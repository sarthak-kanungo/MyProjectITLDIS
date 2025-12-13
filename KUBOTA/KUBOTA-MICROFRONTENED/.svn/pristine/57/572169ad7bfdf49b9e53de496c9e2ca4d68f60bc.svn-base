import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FunctionMasterPagePresenter } from './function-master-page.presenter';
import { FunctionMasterPageStore } from './function-master-page.store';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { SubmitDto } from '../../domain/function-master-domain';


@Component({
  selector: 'app-function-master-page',
  templateUrl: './function-master-page.component.html',
  styleUrls: ['./function-master-page.component.scss'],
  providers:[FunctionMasterPagePresenter, FunctionMasterPageStore]
})
export class FunctionMasterPageComponent implements OnInit {
  functionMasterForm: FormGroup
  functionTableForm: FormGroup
  constructor(
    private functionMasterPagePresenter:FunctionMasterPagePresenter,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.functionMasterForm = this.functionMasterPagePresenter.functionMasterForm
    this.functionTableForm = this.functionMasterPagePresenter.functionTableForm

  }

  submitData() {
    let submitData = {} as SubmitDto
  }

  validateForm() {
      this.openConfirmDialog();
   
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Function Master?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        // this.validateForm();
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
