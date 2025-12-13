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
  private readonly loginitldisUserUrl = `${environment.baseUrl}${urlService.api}${urlService.itldisuser}${urlService.login}`;
 
  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService,
    private encDec: EncryptDecryptService
  ) {}
 
  loginitldisUser(credentials: Credentials) {
    return this.httpClient.post(this.loginitldisUserUrl, credentials);
  }
 
  getLoginUser() {
    return this.localStorageService.getItem("itldisUser") as StorageLoginUser;
  }
  getLoginUserId(): number {
    // Decrypt the user ID from local storage
    const decryptedId = this.encDec.decrypt(
      String(this.localStorageService.getItem("itldisUser")["id"])
    );
 
    // Return the decrypted ID as a number
    return Number(decryptedId);
  }
  getLoginUserDealerCode() {
    return this.localStorageService.getItem("itldisUser")[
      "dealerCode"
    ] as string;
  }
  getLoginUserType() {
    return this.localStorageService.getItem("itldisUser")["userType"] as string;
  }
 
  getDesignation() {
    return this.localStorageService.getItem("itldisUser")[
      "designation"
    ] as string;
  }
}
 