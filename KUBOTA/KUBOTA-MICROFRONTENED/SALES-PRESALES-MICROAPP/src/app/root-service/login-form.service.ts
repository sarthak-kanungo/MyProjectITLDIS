import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Credentials, StorageLoginUser } from "LoginDto";
import { environment } from "../../environments/environment";
import { urlService } from "../webservice-config/baseurl";
import { LocalStorageService } from "./local-storage.service";
import { EncryptDecryptService } from "../auth/encrypt-decrypt";
 
@Injectable({
  providedIn: "root",
})
export class LoginFormService {
  private readonly loginKubotaUserUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotauser}${urlService.login}`;
 
  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService,
    private encDec: EncryptDecryptService
  ) {}
 
  loginKubotaUser(credentials: Credentials) {
    return this.httpClient.post(this.loginKubotaUserUrl, credentials);
  }
 
  getLoginUser() {
    return this.localStorageService.getItem("kubotaUser") as StorageLoginUser;
  }
  getLoginUserId(): number {
    // Decrypt the user ID from local storage
    const decryptedId = this.encDec.decrypt(
      String(this.localStorageService.getItem("kubotaUser")["id"])
    );
 
    // Return the decrypted ID as a number
    return Number(decryptedId);
  }
  getLoginUserDealerCode() {
    return this.localStorageService.getItem("kubotaUser")[
      "dealerCode"
    ] as string;
  }
  getLoginUserType() {
    return this.localStorageService.getItem("kubotaUser")["userType"] as string;
  }
 
  getDesignation() {
    return this.localStorageService.getItem("kubotaUser")[
      "designation"
    ] as string;
  }
}
 