import { Injectable } from "@angular/core";
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse,
} from "@angular/common/http";
import { Observable } from "rxjs";
import { LoginService } from "../login/login.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: LoginService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    if (this.lastSeg(request.url) === "login") {
      return next.handle(request);
    }

    let currentUser = this.authenticationService.isLogin();
    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          Authorization: `${currentUser.token}`,
        },
      });
    }
    return next.handle(request);
  }

  lastSeg(url: string): string {
    const parts = url.split("/");
    const lastSegment = parts.pop() || parts.pop(); // handle potential trailing slash
    return lastSegment;
  }
}
