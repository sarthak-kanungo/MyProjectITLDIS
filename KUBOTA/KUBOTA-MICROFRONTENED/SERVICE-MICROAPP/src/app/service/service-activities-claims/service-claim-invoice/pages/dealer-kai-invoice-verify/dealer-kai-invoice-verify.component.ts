import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { claimInvoicePresenter } from '../service-claim-invoice-search/service-claim-invoice-presenter';
import { claimInvoiceStore } from '../service-claim-invoice-search/service-claim-invoice-store';
import { ServiceClaimInvoiceSearchServiceService } from '../service-claim-invoice-search/service-claim-invoice-search-service.service';


@Component({
  selector: 'app-dealer-kai-invoice-verify',
  templateUrl: './dealer-kai-invoice-verify.component.html',
  styleUrls: ['./dealer-kai-invoice-verify.component.css'],
  providers:[DatePipe,claimInvoicePresenter,claimInvoiceStore,ServiceClaimInvoiceSearchServiceService]
})
export class DealerKaiInvoiceVerifyComponent implements OnInit {

  invoiceVerifyForm:FormGroup
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

  constructor(
    public dialogRef: MatDialogRef<DealerKaiInvoiceVerifyComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private datepipe: DatePipe,
    private fb:FormBuilder,
    private localStorage:LocalStorageService,
    private presenter:claimInvoicePresenter,
    private store:claimInvoiceStore,
    private searchService:ServiceClaimInvoiceSearchServiceService
  ) {
  
  }

  ngOnInit() {
     this.invoiceVerifyForm=this.presenter.claimSearchForms; 
    this.loginUser()
    this.startTimer()
    
  }
  
  getInvoiceNo() {
    
   this.invoiceVerifyForm.get('invoiceNo').patchValue(this.data.invoiceNo?this.data.invoiceNo:null)
  }
  
  getDate() {
  
    this.invoiceVerifyForm.get('invoiceDate').patchValue(this.data.invoiceDate?this.data.invoiceDate:null)
  }
  
  startTimer() {
    setTimeout(() => { this.getInvoiceNo(); this.getDate(); }, 1000);
  }




  loginUser() {
    this.loginType = this.localStorage.getLoginUser();
    console
    if (this.loginType.userType == 'kubota') {
      this.notShowInvoiceNumberOrDate = true
    } else {
      this.notShowInvoiceNumberOrDate = false
    }

    return this.localStorage.getLoginUser();
  }

  public close() {
    this.dialogRef.close();
  }
  public cancel() {
    this.dialogRef.close();
  }

  public submit() {
   
    if (this.invoiceVerifyForm.invalid) {
      this.invoiceVerifyForm.markAllAsTouched();
      // this.toaster.error("Please fill all required filled")
      return false;
    }
    if (this.invoiceVerifyForm.valid) {
      console.log(this.invoiceVerifyForm, 'invoice')
      const invoiceDate = this.datepipe.transform(this.invoiceVerifyForm.value.invoiceDate, 'dd-MM-yyyy');
     
      const invoiceNo = (this.invoiceVerifyForm.value.invoiceNo);
     

      this.dialogRef.close({ event: 'upload', data: this.file,invoiceDate,invoiceNo});

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
    this.getInvoiceNumber = event.target.value
  }
  setToDate(event) {
    this.getInvoiceDate = event.value;
  }


}
