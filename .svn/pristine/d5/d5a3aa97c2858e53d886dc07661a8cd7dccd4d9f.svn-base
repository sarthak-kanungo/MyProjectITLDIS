import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
// import { shareDataBetweenComponent } from './modal-file-upload.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-modal-file-upload',
  templateUrl: './modal-file-upload.component.html',
  styleUrls: ['./modal-file-upload.component.css'],

})
export class ModalFileUploadComponent implements OnInit {
  
  fileUploadForm: FormGroup
  futureDate=new Date()
  file : File
  fileuploadname:string;
  loginType: any;
  loggedInUserType: any
  getInvoiceDate: any;
  notShowInvoiceNumberOrDate:boolean=false
  constructor(public dialogRef: MatDialogRef<ModalFileUploadComponent>,
              private fb : FormBuilder,
              private localStorageService:LocalStorageService,
              // private service:shareDataBetweenComponent,
              private datepipe:DatePipe
              ) { }

  ngOnInit() {
    this.fileUploadForm = this.fb.group({
      fileUpload: [null, Validators.required],
      invoiceNo:[null,this.getKubotaRequiredValidator()],
      invoiceDate:[null,this.getKubotaRequiredValidator()]
    })
  }
  loginUser() {
    this.loginType=this.localStorageService.getLoginUser();
    if(this.loginType.userType=='kubota'){
      // this.notShowInvoiceNumberOrDate=true
    }else{
      // this.notShowInvoiceNumberOrDate=false
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
    
  }

  public fileSelctionChanges(fileEvent){
    this.file = fileEvent.target['files'][0];
    if(this.file){
        this.fileuploadname = this.file.name;
    }else{
        this.fileuploadname = '';
    }
  }

  getKubotaRequiredValidator() {
    this.loggedInUserType=this.localStorageService.getLoginUser()
    if(this.loggedInUserType){
        if(this.loggedInUserType.userType == "kubota"){
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
