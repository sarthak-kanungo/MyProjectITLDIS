import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { urlService } from '../webservice-config/baseurl';

@Injectable({
  providedIn: 'root'
})
export class LoginConfirmationService {

    private readonly logOutKubotaUserUrl = `${environment.baseUrl}${urlService.api}${urlService.logout}`

  constructor(private fb: FormBuilder,
    private httpClient:HttpClient) { }

  

    logout(){
        console.log(this.logOutKubotaUserUrl)
    return this.httpClient.get(this.logOutKubotaUserUrl)
  }
}
