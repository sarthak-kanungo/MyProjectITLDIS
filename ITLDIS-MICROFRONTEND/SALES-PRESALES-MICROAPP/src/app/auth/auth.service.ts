import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { StorageLoginUser } from 'LoginDto';
import { LocalStorageService } from '../root-service/local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private localStorage: LocalStorageService
  ) { }

  storeLoginUser(user: StorageLoginUser) {
    if (!!user) {
      this.localStorage.setItem('itldisUser', user)
      this.loggedIn.next(true);
      this.router.navigate(['/dashboard']);
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.localStorage.removeItem('itldisUser');
    this.router.navigate(['/login']);
  }
  checkUserIsLogged(): boolean {
    if (this.localStorage.getItem('itldisUser') !== null && this.localStorage.getItem('itldisUser') !== undefined) {
      return true
    }
    return false;
  }
  checkUserIsLoggedOut(): boolean {
    if (this.localStorage.getItem('itldisUser') === null) {
      return true
    }
    return false;
  }
}