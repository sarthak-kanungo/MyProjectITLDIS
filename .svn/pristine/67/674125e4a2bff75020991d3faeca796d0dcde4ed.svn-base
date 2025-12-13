import { Component, Input, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { Router } from '@angular/router';
import { ServiceClaimInvoiceCreateService } from '../../pages/service-claim-invoice-create/service-claim-invoice-create.service';
import { JobCardUrl } from 'src/app/service/customer-service/job-card/url-util/job-card-url';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-service-claim-invoice-details',
  templateUrl: './service-claim-invoice-details.component.html',
  styleUrls: ['./service-claim-invoice-details.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class ServiceClaimInvoiceDetailsComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;
  serviceClaimInvoiceForm: FormGroup;
	fileStaticPath: string = JobCardUrl.staticPath
  fileName:string
  pageHeader:string
  id:number
  invoiceNo:string
  constructor(public dialog: MatDialog,
    private fb: FormBuilder,
    private router : Router,
    private createService: ServiceClaimInvoiceCreateService) { }

  ngOnInit() {
    this.createserviceClaimInvoiceForm();
    this.serviceClaimInvoiceForm.disable();
    const operation = this.router.url.split('/');
    if(operation[operation.length-2]=='service-claim-invoice'){
        this.pageHeader = 'Service Claim Invoice';
    } else if(operation[operation.length-2]=='service-claim-invoice'){
      this.pageHeader = 'Service Claim Invoice';
    } else if(operation[operation.length-2]=='service-activity-claim-invoice'){
      this.pageHeader = 'Service Activity Claim Invoice';
    } else if(operation[operation.length-2]=='marketing-activity-claim-invoice'){
      this.pageHeader = 'Sales Marketing Activity Claim Invoice';
    }
    this.createService.viewInvoiceSubject.subscribe(resultSet => {
      if(resultSet){
        this.serviceClaimInvoiceForm.patchValue(resultSet['Header']);
        this.id= resultSet['Header']['id']
        this.invoiceNo = resultSet['Header']['invoiceNo']
        this.fileName = resultSet['Header']['fileName']
      }
    });
    
  }

  createserviceClaimInvoiceForm() {
    this.serviceClaimInvoiceForm = this.fb.group({

      claimNo: ['', Validators.compose([])],
      claimDate: [{ value: '', disabled: true }, Validators.compose([])],
      invoiceNo: [{ value: '', disabled: true }, Validators.compose([])],
      invoiceDate: [{ value: '', disabled: true }, Validators.compose([])],
      dealerCode: [{ value: '', disabled: true }, Validators.compose([])],
      dealerName: [{ value: '', disabled: true }, Validators.compose([])],
      // fromDate: [{ value: '', disabled: true }, Validators.compose([])],
      // toDate: [{ value: '', disabled: true }, Validators.compose([])],
    })
  }

  // validateForm() {

  //   this.openConfirmDialog();
  // }

  // submitData() {
  // }

  // private openConfirmDialog(): void | any {
  //   let message = 'Do you want to submit Service Claim Invoice?';
  //   if (this.isEdit) {
  //     message = 'Do you want to update Service Claim Invoice?';
  //   }
  //   const confirmationData = this.setConfirmationModalData(message);
  //   // //console.log ('confirmationData', confirmationData);
  //   const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
  //     width: '500px',
  //     panelClass: 'confirmation_modal',
  //     data: confirmationData
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed', result);
  //     if (result === 'Confirm' && !this.isEdit) {
  //       this.submitData();
  //     }
  //     if (result === 'Confirm' && this.isEdit) {
  //       this.submitData();
  //     }
  //   });
  // }
  // private setConfirmationModalData(message: string) {
  //   const confirmationData = {} as ConfirmDialogData;
  //   confirmationData.buttonAction = [] as Array<ButtonAction>;
  //   confirmationData.title = 'Confirmation';
  //   confirmationData.message = message;
  //   confirmationData.buttonName = ['Confirm', 'Cancel'];
  //   // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
  //   // confirmationData.buttonAction.push(this.cancelButtonAction());
  //   return confirmationData;
  // }
	printPdf(printStatus:string){
    this.createService.print(this.id, printStatus, this.invoiceNo).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
 }

}
