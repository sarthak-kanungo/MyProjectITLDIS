import { Component, OnChanges } from '@angular/core';
import { IFrameService, IFrameMessageSource, IFrameMessageType, TokenResponseListener, IFrameRouteListener, UrlSegment } from './root-service/iFrame.service';
import { LocalStorageService } from './root-service/local-storage.service';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements TokenResponseListener, IFrameRouteListener, OnChanges {
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    throw new Error("Method not implemented.");
  }
  title;
  queryParam: any;
  private isRouteFirstChange = true;
  constructor(
    private iframeService: IFrameService,
    private router: Router,
    private localStorage: LocalStorageService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.iframeService.tokenResponseListener = this;
    this.iframeService.iFrameRouteListener = this;
    this.activatedRoute.queryParamMap.subscribe(res => {
      this.queryParam = res['params'];
    })
    this.router.events.pipe(filter(eve => eve instanceof NavigationEnd)).subscribe((fullUrl: NavigationEnd) => {
      if (fullUrl.urlAfterRedirects.split('?')[0] === '/' || this.isRouteFirstChange) {
        const url = `service${fullUrl.urlAfterRedirects.split('?')[0]}`;
        this.iframeService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: this.queryParam, firstChange: this.isRouteFirstChange } as UrlSegment)
        this.isRouteFirstChange = false;
        return;
      }
      const url = `service${fullUrl.urlAfterRedirects.split('?')[0]}`;
      this.iframeService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: this.queryParam, firstChange: this.isRouteFirstChange } as UrlSegment)
    })
    this.iframeService.sendTokenRequest(IFrameMessageSource.SERVICE);
  }
  onTokenResponse(token: any) {
    this.localStorage.setItem('itldisUser', token);
  }
  onRoute(urlSegment: UrlSegment) {
    console.log('serviceApp onRoute', urlSegment);
    // this.location.go(urlSegment.url);
    this.router.navigate([urlSegment.url], { queryParams: urlSegment.queryParam ? urlSegment.queryParam : null });
  }

}
