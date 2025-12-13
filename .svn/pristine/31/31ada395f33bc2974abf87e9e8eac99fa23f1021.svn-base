import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, NavigationEnd } from '@angular/router';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { urlService } from '../webservice-config/baseurl';
import { catchError, filter, map } from 'rxjs/operators';
import { LocalStorageService } from '../root-service/local-storage.service';
import { BaseDto } from 'BaseDto';
import { MatDialog } from '@angular/material';

import { EncryptDecryptService } from './encrypt-decrypt';
import { ToastrService } from 'ngx-toastr';


@Injectable({
  providedIn: 'root'
})
export class UserAccessPermissionService {
  private readonly checkRouterAccessibilityUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.kubotauser }${ urlService.checkRouterAccessibility }`;
  constructor(
    private httpClient: HttpClient,
    private loginFormService: LocalStorageService,
    private router: Router,
    private dialog:MatDialog,
    private encDec:EncryptDecryptService,
    private toaster:ToastrService
  ) { }
  checkPermission(routerLink: string): Observable<boolean> {
    // const loginUser = this.loginFormService.getLoginUser();
    const loginUser = this.loginFormService.getLoginUser();
    const userId = this.encDec.decrypt(String(loginUser.loginUserId)); 
    if(userId==null){
      alert("USer Already Login Other Tab or Browser")
    }
    return this.httpClient.get<BaseDto<boolean>>(this.checkRouterAccessibilityUrl, {
      params: new HttpParams()
        .set('userId', userId)
        .set('routerLink', routerLink.slice(1))
    }).pipe(map(res => {
      if (!res.result) {
  
        this.openConfirmationDialog();
      }
      return res.result
    }));
  }
  private openConfirmationDialog(): void {
   
  }
}






@Injectable({
  providedIn: 'root'
})
export class UserAccessGuard implements CanActivate {
  constructor(
    private userAccessPermissionService: UserAccessPermissionService,
    private toasterService:ToastrService,
    private router:Router
  ) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.userAccessPermissionService.checkPermission(state.url).pipe(
      catchError((error) => {
        // Show alert box for error
        this.toasterService.info("User Already Login Into Other Tab or Browser");
        // this.router.navigateByUrl('http://localhost:9000/login')
      //  let url= this.router.navigate(['../'])
      let url=this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        console.log('Navigated to:', event.url); // Log the URL after navigation
      });

        console.log(url,'url')

        // alert('An error occurred while checking permissions. Please try again.');
        console.error('Permission check error:', error); // log the error for debugging
        return of(false); // return false to prevent activation
      })
    ).toPromise();
  }

  

}