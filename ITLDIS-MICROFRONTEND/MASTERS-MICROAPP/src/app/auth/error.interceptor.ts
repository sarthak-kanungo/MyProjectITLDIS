import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { ToastrService } from 'ngx-toastr';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(
        private authService: AuthService,
        private toastr: ToastrService
    ) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            catchError(err => {
                // console.log('err ===>', err)
                // this.toastr.error('Something went wrong!', 'Error');
                if (err.status === 401) {
                    // auto logout if 401 response returned from api
                    this.authService.logout();
                    // location.reload(true);
                }

                const error = err.error.message || err.statusText;
                return throwError(error);
            }), tap(evt => {
                if (evt instanceof HttpResponse) {
                    // console.log('evet-->>>?', evt.headers.get('filename'));
                }
            }))
    }



    // getResponse(httpResponse:HttpResponse<any>) {

    // }
}