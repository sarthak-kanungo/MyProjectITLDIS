import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { Location } from "@angular/common";
import { BehaviorSubject, Observable } from 'rxjs';
import { IFrameLayoutComponent } from '../i-frame-layout/i-frame-layout.component';
import { IframeComponent } from '../iframe/iframe.component';

@Injectable({
  providedIn: 'root'
})
export class RouteHandlerService {
  private _currentNormalizePath$ = new BehaviorSubject<string>(null);

  constructor(
    private router: Router,
    private location: Location
  ) {
    this.routeNavigationEnd();
    this.location.onUrlChange((url) => {
      
    })
    this.location.subscribe((url) => {
      
      // this.createBaseUrlOfMicroApp(url);
    })
  }

  public get currentNormalizePath$(): Observable<string> {
    return this._currentNormalizePath$.asObservable();
  }
  
  public set currentNormalizePath(v : string) {
    this._currentNormalizePath$.next(v);
  }
  
  public get currentNormalizePath() : string {
    return this._currentNormalizePath$.value;
  }
    

  private routeNavigationEnd() {
    this.router.events.pipe(
      filter(e => e instanceof NavigationEnd)
    ).subscribe((e: NavigationEnd) => {
      // const url = e.url && e.url.split('/')[1];
      const url = e.url && e.url.split('/').slice(1).join('/');      
      if (this._currentNormalizePath$.value !== url) {
        this._currentNormalizePath$.next(url);
      }
    });
  }
  resetRouteConfig(routerLink: string) {
    const splitUrl = routerLink.split('/');
    this.router.resetConfig([
      ...this.router.config,
      {
        path: splitUrl[0], component: IFrameLayoutComponent, pathMatch: 'prefix',
        children: [
          { path: '', component: IframeComponent },
          { path: splitUrl.slice(1).join('/'), component: IframeComponent },
        ]
      }
    ])
  }
}
