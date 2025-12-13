import { Injectable, EventEmitter, Inject, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { LoginCredentials } from 'UserAccount';
import { LocalStorageService } from '../root-services/local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

 
  private isUserLogin = new EventEmitter();
  private isUserLogout = new EventEmitter();
  public isUserPresent;
  public responceObj;
  public menuList;
  public checkLogin$ = new BehaviorSubject(null);
  isBrsr: boolean;
  changeData = true

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object,
    private localStorage: LocalStorageService
  ) {
    if (isPlatformBrowser(this.platformId)) {
      // Client only code.
      this.isBrsr = true;
      // //console.log ('Client', this.isBrsr)
    }
    if (isPlatformServer(this.platformId)) {
      // Server only code.
      this.isBrsr = false;
      // //console.log ('Client', this.isBrsr)
    }
  }


  public loginUser(credentials: LoginCredentials) {
    // service new
    // return this.httpClient.post(this.loginUrl, credentials);
  }

  // getMenu(userId) {
  //   return this.httpClient.get(`${this.getMenuUrl}`, {
  //     params: new HttpParams().set('userId', userId)
  //   })
  // }

  isLogin() {
    if (this.isBrsr) {
      this.isUserPresent = this.localStorage.getLoginUser();
    }
    // 
    return this.isUserPresent;
  }
  logOut() {
    
    this.getChangeUserLogOut(true)
    if (this.isBrsr) {
      localStorage.removeItem('currentUser')
    }
    this.router.navigate(['/login']);
  }

  showChangeUserLogin(callback) {
    // 
    this.checkLogin$.subscribe(data => callback(data));
  }
  // ==================== Event Emmit ===========================

  // showChangeUserLogin(callback) {
  //   // 
  //   this.isUserLogin.subscribe(data => callback(data));
  // }

  // ==================== Event Emmit ===========================
  getChangeUserLogOut(loginUser) {
    this.isUserLogout.emit(loginUser);
    // 
  }
  showChangeUserLogOut(callback) {
    // 
    this.isUserLogout.subscribe(data => callback(data));
  }


  // async lastSeg(url: string, loginid, subMenuId) {
  //   const parts = url.split('/');
  //   const lastSegment = parts.pop() || parts.pop();
  //   // handle potential trailing slash
  //   let trendingClubRes = await this.getMenu(loginid).toPromise()
  //   this.menuList = trendingClubRes['response'];
  //   this.menuList.forEach(element => {
  //     element.tabs.forEach(element => {

  //       if (lastSegment === this.removeSpaceAndLowerCase(element.roleName)) {
  //         this.responceObj = {
  //           routerId: element.id,
  //           routerLink: lastSegment
  //         }
  //       }
  //     });
  //   });
  //   return await this.responceObj;
  // }

  removeSpaceAndLowerCase(data: string) {
    // 
    var st = data.replace(/\s/g, '');;
    return st.toLocaleLowerCase()
  }



  btoaLogin(objJsonStr) {
    // Encode the String
    //  // Outputs: "SGVsbG8gV29ybGQh"
    return btoa(objJsonStr);
  }
  atobLogin(encodedString) {
    // Decode the String
    //  // Outputs: "Hello World!"
    return atob(encodedString);

  }
  jsonLogin(objJsonStr) {
    if (objJsonStr) {
      return JSON.parse(objJsonStr)
    }
  }

}