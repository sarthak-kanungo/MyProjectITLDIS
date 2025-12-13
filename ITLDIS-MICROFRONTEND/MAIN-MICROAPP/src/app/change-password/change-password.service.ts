import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {
  
  private readonly resetPasswordUrl :string = `${environment.baseUrl}/${environment.api}/itldisuser/resetPassword`;

  constructor(private fb : FormBuilder, private httpClient : HttpClient) { }

  createPasswordResetForm(){

      return this.fb.group({
        oldPassword: [null, Validators.required],
        newPassword: [null, Validators.compose([Validators.required, Validators.pattern('^(?=.{8,})(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+*!=]).*$')])],
        confirmPassword: [null, Validators.required],
      });
  }

  resetPassword(passwordBody){
    return this.httpClient.post(this.resetPasswordUrl, passwordBody);
  }
}
