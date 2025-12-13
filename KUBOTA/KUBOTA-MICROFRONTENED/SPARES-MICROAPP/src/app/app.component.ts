import { Component } from '@angular/core';
import { IFrameService, IFrameMessageSource, IFrameMessageType, TokenResponseListener, IFrameRouteListener, UrlSegment } from './root-service/iFrame.service';
import { LocalStorageService } from './root-service/local-storage.service';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements TokenResponseListener, IFrameRouteListener {
  title;
  queryParam: any;
  private isRouteFirstChange = true;
  constructor(
    private iframeService: IFrameService,
    private router: Router,
    private localStorage: LocalStorageService,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.iframeService.tokenResponseListener = this;
    this.iframeService.iFrameRouteListener = this;
    this.activatedRoute.queryParamMap.subscribe(res => {
      this.queryParam = res['params'];
    })
    this.router.events.pipe(filter(eve => eve instanceof NavigationEnd)).subscribe((fullUrl: NavigationEnd) => {
      
      this.sendRouteChangeRequest(fullUrl.url)
      if (fullUrl.urlAfterRedirects.split('?')[0] === '/' || this.isRouteFirstChange) {
        this.isRouteFirstChange = false;
        return;
      }
    })
    this.location.onUrlChange(urlChange=>{
      
      
    })
    this.iframeService.sendTokenRequest(IFrameMessageSource.SPARE);
  }
  private sendRouteChangeRequest(fullUrl: string){
    const url = `spares${fullUrl.split('?')[0]}`;
    this.iframeService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url, queryParam: this.queryParam , firstChange: this.isRouteFirstChange} as UrlSegment);
  }
  onTokenResponse(token: any) {
    this.localStorage.setItem('kubotaUser', token);
  }
  onRoute(urlSegment: UrlSegment) {

    // this.location.go(urlSegment.url);
    this.router.navigate([urlSegment.url], { queryParams: urlSegment.queryParam ? urlSegment.queryParam : null });
  }

}
