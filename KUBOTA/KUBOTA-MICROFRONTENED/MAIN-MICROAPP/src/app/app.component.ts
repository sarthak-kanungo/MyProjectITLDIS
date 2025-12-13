import { Component, ViewChild, ElementRef, ChangeDetectionStrategy } from '@angular/core';
import { UserFunctionality } from './core/model/user-functionality.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { NavService } from './nav.service';
import { LoginFormService } from './login/component/login-form/login-form.service';
import { AuthService } from './auth/auth.service';
import { Router, RouterEvent, NavigationEnd } from '@angular/router';
import { environment} from '../environments/environment';
import { DomSanitizer } from '@angular/platform-browser';
import { IFrameService, IFrameMessage, IFrameMessageType, IFrameMessageSource, IFrameMessageListener, TokenRequestListener, IFrameRouteListener } from './root-services/iFrame.service';
import { LocationStrategy, PathLocationStrategy, Location } from '@angular/common';
import { filter } from 'rxjs/internal/operators/filter';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [Location, { provide: LocationStrategy, useClass: PathLocationStrategy }]
})
export class AppComponent {
  
  title = 'kubota';
  public iconUrl = '/assets/Icons/'
  fillerNav = Array.from({ length: 50 }, (_, i) => `Nav Item ${i + 1}`);
  public menu: Array<any> = [];
  public breadcrumbList: Array<any> = [];
  //To toggle a sidenav from another component you need:
  //1. The property decorator @ViewChild.
  //2. A service to accomplish this functionality.
  //The @ViewChild decorator is used to query a single DOM element from the DOM tree
  @ViewChild('snav', { static: false }) snav: ElementRef; //To access the native element, we can use ElementRef, which returns the native HTML element.
  loginUser: import("LoginDto").StorageLoginUser;
  /*
   * RxJS has observers (consuming interface) and observables (push interface). 
   * A Subject is both an observer and observable. 
   * A BehaviorSubject a Subject that can emit the current value (Subjects have no concept of current value).
   * The BehaviorSubject holds the value that needs to be shared with other components.
   * These components subscribe to data which is simple returning the BehaviorSubject value without the functionality to change the value.
   * */
  sideNavItemList$: BehaviorSubject<UserFunctionality[]>;
  appVersion: string;
  isLoggedIn$: BehaviorSubject<boolean>;
  projectUrl
  constructor(
    private navService: NavService,
    private loginFormService: LoginFormService,
    private authService: AuthService
  ) {
    this.appVersion = environment.version;
    this.loginUser = this.loginFormService.getLoginUser();
  }

  ngOnInit() {
    this.sideNavItemList$ = this.navService.userFunctionality$;
    this.isLoggedIn$ = this.authService.loggedIn;
    if (this.loginUser) {
      this.authService.loggedIn.next(true);
    }
  }

  ngAfterViewInit() {
    this.navService.appDrawer = this.snav;
  }
}

