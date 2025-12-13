import { Component, OnInit, Inject } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { RemarkConfiguration, Source, DialogResult } from './confirmation-data';
export interface ButtonAction {
  buttonName: string;
  // successHandler: (sucessRes?: any)=> void;
  clickHandler: Observable<any>;
  errorHandler?: (errorRes?: any) => void;
  webCall?: any;
  webCallUrl?: string;
  webCallType?: string;
  data?: any;
}
export interface ConfirmDialogData {
  title: string;
  message: string;
  buttonAction: Array<ButtonAction>;
  buttonName?: Array<string>;
  data?: Object;
  remarkConfig?: RemarkConfiguration,
  buttonType?:string
  rejectList?:[]
  wcrStatus?:Array<string>
}
@Component({
  selector: 'app-confirmation-box',
  templateUrl: './confirmation-box.component.html',
  styleUrls: ['./confirmation-box.component.scss']
})
export class ConfirmationBoxComponent implements OnInit {

  remarkFormControl = new FormControl(null);
  showRejectionReason:boolean
  showApprovalRating:boolean
  showWcrStatusUpdate:boolean
  ratingControl = new FormControl(null);
  rejectionControl = new FormControl(null);
  wcrStatusControl = new FormControl(null);
  constructor(
    public dialogRef: MatDialogRef<ConfirmationBoxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogData
  ) { 
    if(data.buttonType && data.buttonType==='PCR_REJECT'){
      this.showRejectionReason = true;
      this.showApprovalRating = false;
      this.showWcrStatusUpdate = false;
      this.rejectionControl.setValidators(Validators.required);
    }else if(data.buttonType && data.buttonType==='PCR_APPROVE'){
      this.showApprovalRating = true;
      this.showRejectionReason = false;
      this.showWcrStatusUpdate = false;
      this.ratingControl.setValidators(Validators.compose([Validators.required, Validators.max(5), Validators.min(0)]));
    }else if(data.buttonType && data.buttonType=='WCR_STATUS'){
      this.showWcrStatusUpdate = true;
      this.showApprovalRating = false;
      this.showRejectionReason = false;
      this.wcrStatusControl.setValidators(Validators.required);
    }
  }

  ngOnInit() {
    if (this.data.remarkConfig) {
      if (this.data.remarkConfig.source == Source.REJECT || this.data.remarkConfig.source == Source.REQUIRED) {
        this.remarkFormControl.setValidators(Validators.required)
      }
    }
  }
  submit(btnName: string): void {
    
    if(btnName.toLowerCase()=='cancel'){
      this.dialogRef.close(btnName);
    }
    else if (this.data.remarkConfig) {
      let dialogResult: DialogResult = { button: btnName, remarkText: this.remarkFormControl.value }
      if (this.data.remarkConfig.source == Source.REJECT || this.data.remarkConfig.source == Source.REQUIRED) {
        if (this.remarkFormControl.valid) {
          this.dialogRef.close(dialogResult);
        }
      }
      else {
        this.dialogRef.close(dialogResult);
      }
    } else if(this.showApprovalRating){
      if (this.ratingControl.valid) {
        let dialogResult: DialogResult = { button: btnName, rating: this.ratingControl.value }
        this.dialogRef.close(dialogResult);
      }
    } else if(this.showRejectionReason){
      if (this.rejectionControl.valid) {
        let dialogResult: DialogResult = { button: btnName, reason: this.rejectionControl.value }
        this.dialogRef.close(dialogResult);
      }
    } else if(this.showWcrStatusUpdate){
      if (this.wcrStatusControl.valid) {
        let dialogResult: DialogResult = { button: btnName, wcrStatus: this.wcrStatusControl.value }
        this.dialogRef.close(dialogResult);
      }
    } else {
      this.dialogRef.close(btnName);
    }
  }
  close(subscription?: Subscription) {
    if (subscription) {
      subscription.unsubscribe();
    }
    this.dialogRef.close();
  }
}

