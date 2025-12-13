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
  private readonly loginKubotaUserUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotauser}${urlService.login}`

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  loginKubotaUser(credentials: Credentials) {
    return this.httpClient.post(this.loginKubotaUserUrl, credentials)
  }

  getLoginUser() {
    return this.localStorageService.getItem('kubotaUser') as StorageLoginUser;
  }
  getLoginUserId() {
    return this.localStorageService.getItem('kubotaUser')['id'] as number;
  }
  getLoginUserDealerCode() {
    return this.localStorageService.getItem('kubotaUser')['dealerCode'] as string;
  }
  getLoginUserType() {
    return this.localStorageService.getItem('kubotaUser')['userType'] as string;
  }
}
