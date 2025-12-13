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
  remarkConfig?: RemarkConfiguration
}
@Component({
  selector: 'app-confirmation-box',
  templateUrl: './confirmation-box.component.html',
  styleUrls: ['./confirmation-box.component.scss']
})
export class ConfirmationBoxComponent implements OnInit {

  remarkFormControl = new FormControl(null);

  constructor(
    public dialogRef: MatDialogRef<ConfirmationBoxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogData
  ) { }

  ngOnInit() {
    if (this.data.remarkConfig) {
      if (this.data.remarkConfig.source == Source.REJECT || this.data.remarkConfig.source == Source.REQUIRED) {
        this.remarkFormControl.setValidators(Validators.required)
      }
    }
  }
  submit(btnName: string): void {
    
    if (this.data.remarkConfig) {
      let dialogResult: DialogResult = { button: btnName, remarkText: this.remarkFormControl.value }
      if (this.data.remarkConfig.source == Source.REJECT || this.data.remarkConfig.source == Source.REQUIRED) {
        if (this.remarkFormControl.valid) {
          this.dialogRef.close(dialogResult);
          
          
        }
      }
      else {
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

