import { Component, OnInit } from '@angular/core';
import { SourcePageStore } from './source-page.store';
import { SourcePagePresenter } from './source-page-presenter';
import { FormGroup } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { SubmitSource } from '../../domain/source-domain';
import { ToastrService } from 'ngx-toastr';
import { SourcePageWebService } from './source-page-web.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-source-page',
  templateUrl: './source-page.component.html',
  styleUrls: ['./source-page.component.scss'],
  providers: [SourcePageStore, SourcePagePresenter, SourcePageWebService]
})
export class SourcePageComponent implements OnInit {

  sourceForm: FormGroup
  sourceDetailsForm: FormGroup

  constructor(
    private sourcePagePresenter: SourcePagePresenter,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private sourcePageWebService: SourcePageWebService,
    private router: Router,
    private sourceRt: ActivatedRoute
  ) { }

  ngOnInit() {
    this.sourceForm = this.sourcePagePresenter.sourceForm
    this.sourceDetailsForm = this.sourcePagePresenter.detailsForm
  }

  submitSource() {
    if (this.sourceForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.sourcePagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }

  }

  clearSourceForm() {
    this.sourceForm.reset()
  }

  submitData() {
    console.log(this.sourceForm.value);
    const sourceForm = this.sourceForm.value
    const submitSource = {} as SubmitSource
    submitSource.sourceCode = sourceForm.sourceDetailsForm.sourceCode
    submitSource.sourceName = sourceForm.sourceDetailsForm.sourceName
    submitSource.sourcePurpose = {
      id: sourceForm.sourceDetailsForm.purpose.id
    }
    console.log('submit', submitSource);
    this.sourcePageWebService.saveEnquirySource(submitSource).subscribe(response => {
      const displayMsg = response['message']
      this.toastr.success(displayMsg, 'Success')
      this.router.navigate(['../'], { relativeTo: this.sourceRt })
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Source Master?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
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
    return confirmationData;
  }


}
