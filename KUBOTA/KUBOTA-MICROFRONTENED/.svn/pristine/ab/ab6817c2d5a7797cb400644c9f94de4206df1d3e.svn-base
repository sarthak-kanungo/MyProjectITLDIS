import { Component, OnInit, Input, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { IFrameService, TokenRequestListener, IFrameRouteListener, UrlSegment, IFrameMessageSource } from '../root-services/iFrame.service';
import { Location } from '@angular/common';
import { LoginFormService } from '../login/component/login-form/login-form.service';
import { StorageLoginUser } from 'LoginDto';
import { ChildAppStartRoute, environment, ChildAppPort } from '../../environments/environment';
import { RouteHandlerService } from '../root-services/route-handler.service';

@Component({
  selector: 'app-iframe',
  templateUrl: './iframe.component.html',
  styleUrls: ['./iframe.component.css'],
  providers: []
})
export class IframeComponent implements OnInit, TokenRequestListener, IFrameRouteListener {
  private loginUser: StorageLoginUser;
  @Input() projectUrl: SafeResourceUrl;
  currentMicroAppUrlSegment: string;
  urlSegmentForRouteChange: UrlSegment;
  isLoadDiffrentMicroApp: boolean;
  @ViewChild('iFrameOutletRef', {static:true}) iFrameOutlet: ElementRef;

  constructor(
    private router: Router,
    private loginFormService: LoginFormService,
    private sanitizer: DomSanitizer,
    private location: Location,
    private iframeService: IFrameService,
    private routeHandlerService: RouteHandlerService,
    private changeDetectorRef: ChangeDetectorRef  ) { }

  ngOnInit() {

    this.loginUser = this.loginFormService.getLoginUser();
    this.iframeService.tokenRequestListener = this;
    this.iframeService.iFrameRouteListener = this;
    // this.projectUrl = this.routeHandlerService.currentNormalizePath;
    this.currentNormalizePath()
  }
  private currentNormalizePath() {
    this.routeHandlerService.currentNormalizePath$.subscribe(path => {
      console.log('currentNormalizePath path',path);
      if (path !== null) {
        this.createBaseUrlOfMicroApp(path);
      }
    })
  }
  private createBaseUrlOfMicroApp(url: string) {
    const splitUrl = url.split('/')[0];
    this.projectUrl = null;
    if (splitUrl) {
      switch (splitUrl) {
        case ChildAppStartRoute.WARRANTY: {
          const baseUrl = `${environment.domain}${ChildAppPort.WARRANTY}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.DASHBOARD: {
          const baseUrl = `${environment.domain}${ChildAppPort.DASHBOARD}`;
          this.loadProject(baseUrl, url, splitUrl);
            
          return;
        }
        case ChildAppStartRoute.SPARE: {
          const baseUrl = `${environment.domain}${ChildAppPort.SPARE}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.SALE_PRESALE: {
          console.log(environment.domain);
          const baseUrl = `${environment.domain}${ChildAppPort.SALE_PRESALE}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.SERVICE: {
          const baseUrl = `${environment.domain}${ChildAppPort.SERVICE}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.MASTER: {
          const baseUrl = `${environment.domain}${ChildAppPort.MASTER}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.CRM_TRAINING: {
          const baseUrl = `${environment.domain}${ChildAppPort.CRM_TRAINING}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        case ChildAppStartRoute.TRAINING: {
          const baseUrl = `${environment.domain}${ChildAppPort.TRAINING}`;
          this.loadProject(baseUrl, url, splitUrl);
          return;
        }
        default: {
          const baseUrl = `${environment.domain}${ChildAppPort.DASHBOARD}`;
          this.loadProject(baseUrl, '', ChildAppStartRoute.DASHBOARD);
          break;
        }
      }
    }
  }
  private loadProject(baseUrl: string, url: string, currentMicroAppUrlSegment:string) {
    this.projectUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`${baseUrl}/${url}`);
    console.log('projectUrl : ');
    console.log(this.projectUrl);
    // this.iFrameOutlet.nativeElement.setAttribute('src', `${baseUrl}/${url}`)
    this.currentMicroAppUrlSegment = currentMicroAppUrlSegment;
    
  }
  onTokenRequest() {
    this.iframeService.publishTokenToIFrame(this.loginUser);
    console.log('this.location.subscribe onTokenRequest::: ',this.location.path());
    // this.sendRouteChangeRequest();
  }
  private sendRouteChangeRequest() {
    
    if (this.isLoadDiffrentMicroApp) {
      this.iframeService.sendRouteChangeRequest(IFrameMessageSource.MAIN, this.urlSegmentForRouteChange);
      this.isLoadDiffrentMicroApp = false;
      return;
    }
    const splitedUrl = this.location.path().split('/');
    let url;
    if (splitedUrl.length > 1) {
      url = splitedUrl.slice(2).join('/');
    }else{
      url = '';
    }
    const urlSegment = this.createURLSegment(url);
    
    this.iframeService.sendRouteChangeRequest(IFrameMessageSource.MAIN, urlSegment);
  }
  private createURLSegment(url: string): UrlSegment {
    if (url.startsWith('/')) {
      url = url.slice(1);
    }
    const urlTree = this.router.parseUrl(url);
    const urlWithoutQueryparam = url.split('?')[0];
    return { url: urlWithoutQueryparam, queryParam: (urlTree && Object.keys(urlTree.queryParams).length) ? urlTree.queryParams : null };
  }
  onRoute(urlSegment: UrlSegment) {
    console.log('main :: onRoute urlSegment: ', urlSegment);
    const url = this.checkRouteRequestIsForNewMicroApp(urlSegment.url, urlSegment.queryParam);
    console.log('url: ', url);
    this.urlSegmentForRouteChange = {...urlSegment};
    if (url && !urlSegment['firstChange']) {
      // this.createBaseUrlOfMicroApp(url.mainAppUrl);
      this.routeHandlerService.currentNormalizePath = `${url.mainAppUrl}/${url.childAppUrl}`;
      this.urlSegmentForRouteChange.url = url.childAppUrl;
      this.isLoadDiffrentMicroApp = true;
      this.location.replaceState(urlSegment.url, this.queryParamsString(urlSegment.queryParam));
      this.changeDetectorRef.detectChanges();
      // this.changeDetectorRef.markForCheck();
      return;
    }
    
    this.location.replaceState(urlSegment.url, this.queryParamsString(urlSegment.queryParam));
    if (url && url.mainAppUrl) {
      this.currentMicroAppUrlSegment = url.mainAppUrl;
    }
    // this.router.navigate([urlSegment.url], { queryParams: urlSegment.queryParam ? urlSegment.queryParam : null });
  }
  private checkRouteRequestIsForNewMicroApp(url: string, queryParam?:{[key:string]:any}) {
    const splitUrl = url.split('/');
    console.log('this.currentMicroAppUrlSegment: ', this.currentMicroAppUrlSegment, 'splitUrl',splitUrl);
    if (this.currentMicroAppUrlSegment !== splitUrl[0]) {
      const childAppUrl = (splitUrl.slice(1) && splitUrl.slice(1).length > 1) && splitUrl.slice(1).join('/')+'?'+ this.queryParamsString(queryParam);
      return {mainAppUrl: splitUrl[0], childAppUrl };
    }
    return undefined;
  }
  private queryParamsString(queryParam: { [key: string]: any }) {
    if (!queryParam) {
      return '';
    }
    return Object.entries(queryParam).map(value => {
      return `${value[0]}=${value[1]}`;
    }).join('&');
  }
}
