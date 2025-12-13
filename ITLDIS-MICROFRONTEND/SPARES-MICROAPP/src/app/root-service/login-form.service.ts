import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Credentials, StorageLoginUser } from 'LoginDto';
import { environment } from '../../environments/environment';
import { urlService } from '../webservice-config/baseurl';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class LoginFormService {
  private readonly loginitldisUserUrl = `${environment.baseUrl}${urlService.api}${urlService.itldisuser}${urlService.login}`

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  loginitldisUser(credentials: Credentials) {
    return this.httpClient.post(this.loginitldisUserUrl, credentials)
  }

  getLoginUser() {
    return this.localStorageService.getItem('itldisUser') as StorageLoginUser;
  }
  getLoginUserId() {
    return this.localStorageService.getItem('itldisUser')['id'] as number;
  }
  getLoginUserDealerCode() {
    return this.localStorageService.getItem('itldisUser')['dealerCode'] as string;
  }
  getLoginUserType() {
    return this.localStorageService.getItem('itldisUser')['userType'] as string;
  }
}
