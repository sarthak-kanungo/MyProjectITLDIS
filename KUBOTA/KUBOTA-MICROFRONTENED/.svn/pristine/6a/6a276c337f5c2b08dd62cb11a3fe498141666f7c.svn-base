import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from "@angular/router";
import { Observable } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { urlService } from "../webservice-config/baseurl";
import { map } from "rxjs/operators";
import { LocalStorageService } from "../root-service/local-storage.service";
import { BaseDto } from "BaseDto";
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
    if (loginUser && loginUser.id) {
      return this.httpClient
        .get<BaseDto<boolean>>(this.checkRouterAccessibilityUrl, {
          params: new HttpParams()
            .set("userId", userId)
            .set("routerLink", routerLink.slice(1)),
        })
        .pipe(
          map((res) => {
            if (!res.result) {
              this.router.navigate(["./access-denied"]);
            }
            return res.result;
          })
        );
    }
    return new Observable<boolean>((obser) => obser.next(false));
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
    // console.log('next', next);
    // console.log('state', state);
    return this.userAccessPermissionService
      .checkPermission(state.url)
      .toPromise();
  }
}
 