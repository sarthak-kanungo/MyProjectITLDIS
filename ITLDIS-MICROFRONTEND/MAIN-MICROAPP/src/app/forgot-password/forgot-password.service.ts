import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {

  private readonly forgotPasswordUrl:string = `${environment.baseUrl}/${environment.api}/itldisuser/forgotPassword`;

  constructor(private fb: FormBuilder,
    private httpClient:HttpClient) { }

  creatForgotPasswordForm(){
    return this.fb.group({
      username : [null, Validators.required]
    })
  }

  forgotPassword(username){
    return this.httpClient.get(this.forgotPasswordUrl, {
      params : new HttpParams().set("username",username)
    })
  }
}
