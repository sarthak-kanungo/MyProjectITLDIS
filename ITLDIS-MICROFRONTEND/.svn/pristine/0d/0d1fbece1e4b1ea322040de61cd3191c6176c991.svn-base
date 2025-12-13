import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { IFrameMessageSource, IFrameRouteListener, IFrameService, TokenResponseListener, UrlSegment } from './root-service/iFrame.service';
import { LocalStorageService } from './root-service/local-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  implements TokenResponseListener, IFrameRouteListener {
  title = 'DASHBOARD';
  queryParam: any;
  private isRouteFirstChange = true;
  constructor(
    private iframeService: IFrameService,
    private localStorage: LocalStorageService,
    private router: Router,
    private activatedRoute:ActivatedRoute
  ) { }
  
  ngOnInit() {
    this.iframeService.tokenResponseListener = this;
    this.iframeService.iFrameRouteListener = this;
    this.activatedRoute.queryParamMap.subscribe(res=>{
      this.queryParam = res.get('params');
    })
    this.router.events.pipe(filter(eve => eve instanceof NavigationEnd)).subscribe((fullUrl: NavigationEnd) => {
      const url = `${fullUrl.urlAfterRedirects.substring(1).split('?')[0]}`;
      this.iframeService.sendRouteChangeRequest(IFrameMessageSource.DASHBOARD, { url, queryParam: this.queryParam , firstChange: this.isRouteFirstChange} as UrlSegment);
      
      // if (fullUrl.urlAfterRedirects.split('?')[0] === '/' || this.isRouteFirstChange) {
      //   this.isRouteFirstChange = false;
      //   return;
      // }
      
    });
    this.iframeService.sendTokenRequest(IFrameMessageSource.DASHBOARD);
  }
  onTokenResponse(token: any) {
    this.localStorage.setItem('kubotaUser', token);
  }
  onRoute(urlSegment: UrlSegment) {
    this.router.navigate([urlSegment.url], { queryParams: urlSegment.queryParam ? urlSegment.queryParam : null });
  }
}
