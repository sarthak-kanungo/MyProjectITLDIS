import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ForgotPasswordService } from './forgot-password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm:FormGroup;
  PasswordError:string;
  constructor(private forgotPasswordService: ForgotPasswordService,
    private dialogRef : MatDialogRef<ForgotPasswordComponent>,
    private toastr : ToastrService) { }

  ngOnInit() {
    this.forgotPasswordForm = this.forgotPasswordService.creatForgotPasswordForm();
  }
  
  public close(){
    this.dialogRef.close();
  }
  
  public submit(){
    this.forgotPasswordForm.markAllAsTouched();
    if(this.forgotPasswordForm.valid){
      this.forgotPasswordService.forgotPassword(this.forgotPasswordForm.get('username').value).subscribe(response => {
        if(response){
          if(response['result']=='Done'){
              this.toastr.success("Password send to registered email Id");
              this.dialogRef.close();
          }else{
            this.forgotPasswordForm.get('username').setErrors({"invalid":"Invalid"})
            this.PasswordError = response['result'];
          }
        }
      })
    }
  }

  preventWhiteSpace(event){
    if (event.code === 'Space') {
      event.preventDefault();
    }
  }
}
