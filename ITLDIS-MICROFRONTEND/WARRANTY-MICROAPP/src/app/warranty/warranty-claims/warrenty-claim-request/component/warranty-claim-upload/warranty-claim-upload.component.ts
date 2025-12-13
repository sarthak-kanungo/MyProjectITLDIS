import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { EventEmitter } from 'events';
import { ToastrService } from 'ngx-toastr';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';
import { WcrPageStore } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.store';
import { SharedDataService } from './warranty-claim-upload-service';
import { DatePipe } from '@angular/common';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-warranty-claim-upload',
  templateUrl: './warranty-claim-upload.component.html',
  styleUrls: ['./warranty-claim-upload.component.css'],
  providers:[WcrPagePresenter,WcrPageStore,DatePipe]
})
export class WarrantyClaimUploadComponent implements OnInit {

  fileUploadForm: FormGroup
  file : File
  fileuploadname:string;
  futureDate=new Date()
  getInvoiceNumber: any;
  getInvoiceDate: any;
  loginType: import("LoginDto").StorageLoginUser;
  notShowInvoiceNumberOrDate: boolean;
 
  constructor(public dialogRef: MatDialogRef<WarrantyClaimUploadComponent>,
             private datepipe: DatePipe,
              private toaster:ToastrService,
              public presenter:WcrPagePresenter,
              private sharedDataService:SharedDataService,
              private localStorageService:LocalStorageService
              
              ) { }

  ngOnInit() {

    this.fileUploadForm=this.presenter.uploadForm
      
    this.loginUser()
  }

  loginUser() {
    this.loginType=this.localStorageService.getLoginUser();
    if(this.loginType.userType=='itldis'){
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
    
    if(this.fileUploadForm.invalid){
      this.fileUploadForm.markAllAsTouched();
      this.toaster.error("Please fill all required filled")
      return false;
    }
    if(this.fileUploadForm.valid){
      
      const latest_date =this.datepipe.transform(this.fileUploadForm.value.invoiceDate, 'dd-MM-yyyy');
      this.sharedDataService.setDatas(latest_date);
      const dataToSend =(this.fileUploadForm.value.invoiceNo) ;
      this.sharedDataService.setData(dataToSend);
        this.dialogRef.close({event:'upload',data:this.file});
      
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

  preventSpecialCharacter(event:KeyboardEvent){
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }

  onKey(event){
    this.getInvoiceNumber=event.target.value
    this.presenter.getIncoiceInputValue(event);
  }
  setToDate(event){
    this.getInvoiceDate=event.value;
  }


}
