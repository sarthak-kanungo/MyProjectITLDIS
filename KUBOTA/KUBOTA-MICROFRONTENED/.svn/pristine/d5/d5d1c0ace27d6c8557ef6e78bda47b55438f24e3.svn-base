import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from "@angular/router";
import { Observable, of } from "rxjs";
import {
  HttpClient,
  HttpParams,
  HttpErrorResponse,
} from "@angular/common/http";
import { environment } from "../../environments/environment";
import { urlService } from "../webservice-config/baseurl";
import { map, catchError } from "rxjs/operators";

import { BaseDto } from "BaseDto";
import { LocalStorageService } from "../root-service/local-storage.service";
import { EncryptDecryptService } from "./encrypt-decrypt";

@Injectable({
  providedIn: "root",
})
export class UserAccessPermissionService {
  private readonly checkRouterAccessibilityUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotauser}${urlService.checkRouterAccessibility}`;

  constructor(
    private httpClient: HttpClient,
    private loginFormService: LocalStorageService,
    private router: Router,
    private encDec: EncryptDecryptService
  ) {}

  checkPermission(routerLink: string): Observable<boolean> {
    const loginUser = this.loginFormService.getLoginUser();
    const userId = this.encDec.decrypt(String(loginUser.loginUserId));

    return this.httpClient
      .get<BaseDto<boolean>>(this.checkRouterAccessibilityUrl, {
        params: new HttpParams()
          .set("userId", userId)
          .set("routerLink", routerLink.slice(1)),
      })
      .pipe(
        map((res) => {
          if (res.result) {
           
          } else {
            this.router.navigate(["./access-denied"]); // Navigate to "access-denied" page if unauthorized
          }
          return res.result;
        }),
        catchError((error: HttpErrorResponse) => {
          // Handle the error here
          if (error.status === 401) {
            // If Unauthorized, show a custom error message
            alert("Unauthorized access. Please log in again.");
            this.router.navigate(["./login"]); // Redirect to login page (optional)
          } else {
            // Handle other errors
            alert(`Ankit: ${error.message}`);
          }
          // Return a fallback value (e.g., false) to prevent further navigation
          return of(false);
        })
      );
  }
}

@Injectable({
  providedIn: "root",
})
export class UserAccessGuard implements CanActivate {
  constructor(
    private userAccessPermissionService: UserAccessPermissionService
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.userAccessPermissionService
      .checkPermission(state.url)
      .toPromise();
  }
}
