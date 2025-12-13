import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
// import { shareDataBetweenComponent } from './modal-file-upload.service';
import { DatePipe } from '@angular/common';
import { shareDataBetweenComponent } from './dealer-kai-upload-service';

@Component({
  selector: 'app-dealer-and-kai-upload-invoice',
  templateUrl: './dealer-and-kai-upload-invoice.component.html',
  styleUrls: ['./dealer-and-kai-upload-invoice.component.css'],
  providers:[shareDataBetweenComponent,DatePipe]
})
export class DealerAndKaiUploadInvoiceComponent implements OnInit {

  fileUploadForm: FormGroup
  file : File
  fileuploadname:string;
  loginType: any;
  futureDate=new Date()
  loggedInUserType:any
  getInvoiceDate: any;
  notShowInvoiceNumberOrDate: boolean=false;
  constructor(public dialogRef: MatDialogRef<DealerAndKaiUploadInvoiceComponent>,
              private fb : FormBuilder,
              private localStorageService:LocalStorageService,
              private service:shareDataBetweenComponent,
              private datepipe:DatePipe
              ) { }

  ngOnInit() {
    this.fileUploadForm = this.fb.group({
      fileUpload: [null, Validators.required],
      invoiceNo:[null,this.getitldisRequiredValidator()],
      invoiceDate:[null,this.getitldisRequiredValidator()]
    })
    this.loginUser()
  }
  loginUser() {
    this.loginType=this.localStorageService.getLoginUser();
    console.log(this.loginType,'type')
    if(this.loginType.userType=='dealer'){
       this.notShowInvoiceNumberOrDate=true
    }else{
      this.notShowInvoiceNumberOrDate=false
    }
    
    return this.localStorageService.getLoginUser();
    
}

  public close(){
    this.dialogRef.close();
  }


  public submit(){
    debugger
    console.log(this.fileUploadForm,'status')
    if(this.fileUploadForm.invalid){
      this.fileUploadForm.markAllAsTouched()
      return false;

    }
    if(this.fileUploadForm.valid){
      const invoiceDate =this.datepipe.transform(this.fileUploadForm.value.invoiceDate, 'dd-MM-yyyy');
      
      const dataToSend =(this.fileUploadForm.value.invoiceNo) ;
      
        this.dialogRef.close({event:'upload',data:this.file,dataToSend,invoiceDate});
    }
  }

  public fileSelctionChanges(fileEvent){
    this.file = fileEvent.target['files'][0];
    if(this.file){
        this.fileuploadname = this.file.name;
    }else{
        this.fileuploadname = '';
    }
  }

  getitldisRequiredValidator() {
    this.loggedInUserType=this.localStorageService.getLoginUser()
    if(this.loggedInUserType){
        if(this.loggedInUserType.userType == "itldis"){
          return Validators.nullValidator
        // return Validators.required?Validators.required:null
        }else{
            // return Validators.nullValidator
           return Validators.required?Validators.required:null
        }  
    }
  }
  preventSpecialCharacter(event:KeyboardEvent){
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }

  onKey(event){
    console.log(event,'eevevevv')
  }
  setToDate(event){
    this.getInvoiceDate=event.value;
  }

}
