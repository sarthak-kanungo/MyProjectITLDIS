import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { urlService } from "../../../webservice-config/baseurl";
import { Credentials, StorageLoginUser } from "LoginDto";
import { LocalStorageService } from "../../../root-services/local-storage.service";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class LoginFormService {
  private readonly loginKubotaUserUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotauser}${urlService.login}`;
  private readonly logOutKubotaUserUrl = `${environment.baseUrl}${urlService.api}${urlService.logout}`;
  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) {}

  loginKubotaUser(credentials: Credentials) {
    return this.httpClient.post(this.loginKubotaUserUrl, credentials);
  }

  logOut(): Observable<any> {
    return this.httpClient.get<any>(this.logOutKubotaUserUrl);
  }

  getLoginUser() {
    return this.localStorageService.getItem("kubotaUser") as StorageLoginUser;
  }
  getLoginUserId() {
    return this.localStorageService.getItem("kubotaUser")["id"] as number;
  }
  getLoginUserDealerCode() {
    return this.localStorageService.getItem("kubotaUser")[
      "dealerCode"
    ] as string;
  }
  getLoginUserType() {
    return this.localStorageService.getItem("kubotaUser")["userType"] as string;
  }
}
