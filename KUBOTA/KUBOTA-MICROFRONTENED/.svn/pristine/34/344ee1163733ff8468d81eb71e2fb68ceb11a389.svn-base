import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { LoginFormService } from '../login/component/login-form/login-form.service';
import { HttpClient } from '@angular/common/http';
import { LoginConfirmationService } from './login-confirmation-service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login-confirmation',
  templateUrl: './login-confirmation.component.html',
  styleUrls: ['./login-confirmation.component.css'],
  providers:[]
})
export class LoginConfirmationComponent implements OnInit {
  @Input() errorMessage!: string;
  userCode:string
 
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<LoginConfirmationComponent>,
    private loginService:LoginFormService,
    private httpClient:HttpClient,
    private authService:AuthService
  ) { }

  ngOnInit() {
    this.errorMessage=this.data.message;
   let userId=JSON.parse(this.data.credential)
    this.userCode=userId.username;
  }

  onCancel(){
    this.dialogRef.close();
  }

  token:any;

  onLogout(btnName:any){
    if(btnName=='logout'){
  //   console.log(btnName)
  //  if(btnName=='logout'){
  //   const returnData = {
  //     action: 'logout',
  //     username: this.userCode
  //   };
  //   this.dialogRef.close(returnData);
  //  }
    // this.loginService.logout();

    const tokenString = localStorage.getItem('kubotaUser');
    console.log(tokenString,'tokenString')
    if (tokenString !== null) {
     this.token = JSON.parse(tokenString).token;
    }
    if (this.token==null) {
      this.loginService.logOut().subscribe({
        next: response => console.log(response),
        error: error => console.error(error)
      });
    }
    // this.loginFormService.logOut();
    this.authService.logout();

    // if(this.modalDialog){
    //   this.modalDialog.close();
    // }
  }
}
}
