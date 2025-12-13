import { Component } from '@angular/core';
import { IFrameService, IFrameMessageSource, TokenResponseListener, IFrameRouteListener, UrlSegment } from './root-service/iFrame.service';
import { LocalStorageService } from './root-service/local-storage.service';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements TokenResponseListener, IFrameRouteListener {
  private queryParam: any;
  private isRouteFirstChange = true;
  constructor(
    private router: Router,
    private iframeService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private localStorage: LocalStorageService
  ) { }

  ngOnInit() {
    this.iframeService.tokenResponseListener = this;
    this.iframeService.iFrameRouteListener = this;
    this.activatedRoute.queryParamMap.subscribe(res => {
      this.queryParam = res['params'];
    })
    this.router.events.pipe(filter(eve => eve instanceof NavigationEnd)).subscribe((fullUrl: NavigationEnd) => {
      // const url = fullUrl.urlAfterRedirects.split('?')[0]
      const url = `sales-pre-sales${fullUrl.urlAfterRedirects.split('?')[0]}`;
      this.iframeService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url, queryParam: this.queryParam , firstChange: this.isRouteFirstChange} as UrlSegment);
      if (fullUrl.urlAfterRedirects.split('?')[0] === '/' || this.isRouteFirstChange) {
        this.isRouteFirstChange = false;
        return;
      }
      // const url = `sales-pre-sales${fullUrl.urlAfterRedirects.split('?')[0]}`;
      // this.iframeService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url, queryParam: this.queryParam })
    })
    this.iframeService.sendTokenRequest(IFrameMessageSource.SALE_PRESALE);
  }
  onTokenResponse(token: any) {
    this.localStorage.setItem('kubotaUser', token);
  }
  onRoute(urlSegment: UrlSegment) {
    console.log('spareApp onRoute', urlSegment);
    // this.location.go(urlSegment.url);
    this.router.navigate([urlSegment.url], { queryParams: urlSegment.queryParam ? urlSegment.queryParam : null });
  }
}
