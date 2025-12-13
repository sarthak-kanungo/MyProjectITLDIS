import { BehaviorSubject } from "rxjs";
import { Injectable } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { StorageLoginUser } from "LoginDto";
import { LocalStorageService } from "../root-services/local-storage.service";
import { EncryptDecryptService } from "./encrypt-decrypt";

@Injectable()
export class AuthService {
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable(); //push Interface
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private localStorage: LocalStorageService,
    private encyDecy: EncryptDecryptService
  ) {}

  storeLoginUser(user: StorageLoginUser) {
    if (!!user) {
      this.checkUserIsLoggedOut();
      const encryptedUser = this.encyDecy
        .decrypt(JSON.stringify(user))
        .toString();
      console.log(encryptedUser, "aurhg service");

      this.localStorage.setItem("itldisUser", user);
      this.loggedIn.next(true);

      this.router.navigate(["/dashboard"]);
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.localStorage.removeItem("itldisUser");
    this.router.navigate(["/login"]);
  }

  sessionExpired() {
    this.loggedIn.next(false);
    this.localStorage.removeItem("itldisUser");
    this.router.navigate(["/Session-Expired"]);
  }

  checkUserIsLogged(): boolean {
    if (
      this.localStorage.getItem("itldisUser") !== null &&
      this.localStorage.getItem("itldisUser") !== undefined
    ) {
      return true;
    }
    return false;
  }
  checkUserIsLoggedOut(): boolean {
    if (this.localStorage.getItem("itldisUser") === null) {
      return true;
    }
    return false;
  }
}
