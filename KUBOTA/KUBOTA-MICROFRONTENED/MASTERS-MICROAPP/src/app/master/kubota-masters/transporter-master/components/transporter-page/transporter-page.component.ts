import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TransporterPagePresenter } from './transporter-page-presenter';
import { TransporterPageStore } from './transporter-page.store';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { SubmitTransporter } from '../../domain/transporter-domain';
import { TransporterPageWebService } from './transporter-page-web.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-transporter-page',
  templateUrl: './transporter-page.component.html',
  styleUrls: ['./transporter-page.component.scss'],
  providers: [TransporterPagePresenter, TransporterPageStore, TransporterPageWebService]
})
export class TransporterPageComponent implements OnInit {

  transporterCreateForm: FormGroup
  transporterDetailsForm: FormGroup
  addressDetailsForm: FormGroup

  constructor(
    private transporterPageWebService: TransporterPageWebService,
    private transporterPagePresenter: TransporterPagePresenter,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private transporterRt: ActivatedRoute
  ) { }

  ngOnInit() {
    this.transporterCreateForm = this.transporterPagePresenter.transporterCreateForm
    this.transporterDetailsForm = this.transporterPagePresenter.transporterDetailsForm
    this.addressDetailsForm = this.transporterPagePresenter.addressDetailsForm
  }


  submitTransporter() {
    if (this.transporterCreateForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.transporterPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitData() {
    console.log(this.transporterCreateForm.value);
    const transporterCreateForm = this.transporterCreateForm.value
    const submitTransporter = {} as SubmitTransporter
    submitTransporter.transporterCode = transporterCreateForm.transporterDetailsForm.transporterCode
    submitTransporter.transporterName = transporterCreateForm.transporterDetailsForm.transporterName

    console.log('submit', submitTransporter);
    this.transporterPageWebService.saveEnquiryTransporter(submitTransporter).subscribe(response => {
      const displayMsg = response['message']
      this.toastr.success(displayMsg, 'Success')
      this.router.navigate(['../'], { relativeTo: this.transporterRt })
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Transporter Master?';
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

  clearTransporterForm() {
    this.transporterCreateForm.reset()
  }

}
