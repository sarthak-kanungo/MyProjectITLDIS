import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';
import { SharedDataService } from '../warranty-claim-upload/warranty-claim-upload-service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { FormGroup } from '@angular/forms';
import { WcrPageStore } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.store';
import { take } from 'rxjs/operators';
import { invoiceVerifyService } from './invoice-verify-modal-service';

@Component({
  selector: 'app-invoice-verify-modal',
  templateUrl: './invoice-verify-modal.component.html',
  styleUrls: ['./invoice-verify-modal.component.css'],
  providers: [DatePipe, WcrPagePresenter, WcrPageStore]
})
export class InvoiceVerifyModalComponent implements OnInit {

  kaiInvoiceForm: FormGroup
  file: File
  fileuploadname: string;
  futureDate = new Date()
  getInvoiceNumber: any;
  getInvoiceDate: any;

  notShowInvoiceNumberOrDate: boolean;
  loginType: any;
  invoiceNo: any;
  invoiceDate: any;
  invoiceNoSubject: any;

  constructor(public dialogRef: MatDialogRef<InvoiceVerifyModalComponent>,
    private datepipe: DatePipe,
    private toaster: ToastrService,
    public presenter: WcrPagePresenter,
    // private sharedDataService:SharedDataService,
    private localStorageService: LocalStorageService,
    private service: invoiceVerifyService

  ) {

  }

  ngOnInit() {
    this.kaiInvoiceForm = this.presenter.kaiInvoiceForm;
    this.loginUser()
    this.startTimer()
  }
  getInvoiceNo() {
    this.service.sharedData$.subscribe((data) => {
      this.kaiInvoiceForm.get('invoiceNo').patchValue(data)
    });
  }

  getDate() {
    this.service.sharedDatas$.subscribe((date) => {
      this.kaiInvoiceForm.get('invoiceDate').patchValue(date)
    });
  }
  startTimer() {
    setTimeout(() => { this.getInvoiceNo(); this.getDate(); }, 1000);
  }




  loginUser() {
    this.loginType = this.localStorageService.getLoginUser();
    if (this.loginType.userType == 'kubota') {
      this.notShowInvoiceNumberOrDate = true
    } else {
      this.notShowInvoiceNumberOrDate = false
    }

    return this.localStorageService.getLoginUser();
  }

  public close() {
    this.dialogRef.close();
  }
  public cancel() {
    this.dialogRef.close();
  }

  public submit() {
    debugger
    if (this.kaiInvoiceForm.invalid) {
      this.kaiInvoiceForm.markAllAsTouched();
      this.toaster.error("Please fill all required filled")
      return false;
    }
    if (this.kaiInvoiceForm.valid) {
      console.log(this.kaiInvoiceForm, 'invoice')
      const latest_date = this.datepipe.transform(this.kaiInvoiceForm.value.invoiceDate, 'dd-MM-yyyy');
      this.service.setInvoiceDate(latest_date);
      const dataToSend = (this.kaiInvoiceForm.value.invoiceNo);
      this.service.setInvoiceNo(dataToSend);

      this.dialogRef.close({ event: 'upload', data: this.file });

    }
  }

  public fileSelctionChanges(fileEvent) {
    this.file = fileEvent.target['files'][0];
    if (this.file) {
      this.fileuploadname = this.file.name;
    } else {
      this.fileuploadname = '';
    }
  }

  preventSpecialCharacter(event: KeyboardEvent) {
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  onKey(event) {
    // console.log(event.target.value,'eee')
    this.getInvoiceNumber = event.target.value
    this.presenter.getIncoiceInputValue(event);
  }
  setToDate(event) {
    this.getInvoiceDate = event.value;

    // let latest_date = this.datepipe.transform(this.getInvoiceDate, 'dd-MM-yyyy');

  }

}
