import { Component, HostBinding, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatAutocompleteTrigger } from '@angular/material';
import { NavService } from '../nav.service';
import { NavApiService } from '../nav-api.service';
import { UserFunctionality } from '../core/model/user-functionality.model';
import { IFrameService, IFrameMessageSource } from '../root-services/iFrame.service';
import { Location } from "@angular/common";
import { IFrameLayoutComponent } from '../i-frame-layout/i-frame-layout.component';
import { IframeComponent } from '../iframe/iframe.component';
import { RouteHandlerService } from '../root-services/route-handler.service';

@Component({
  selector: 'app-menu-list-item',
  templateUrl: './menu-list-item.component.html',
  styleUrls: ['./menu-list-item.component.scss'],
  animations: [
    trigger('indicatorRotate', [
      state('collapsed', style({ transform: 'rotate(0deg)' })),
      state('expanded', style({ transform: 'rotate(180deg)' })),
      transition('expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4,0.0,0.2,1)')
      ),
    ])
  ],
  providers: [NavApiService]
})
export class MenuListItemComponent implements OnInit {
  expanded: boolean;
  @HostBinding('attr.aria-expanded') ariaExpanded
  @Input() item: UserFunctionality;
  @Input() depth: number;

  constructor(
    public navService: NavService,
    private iframeService: IFrameService,
    private location: Location,
    public router: Router,
    private routeHandlerService:RouteHandlerService
  ) {
    if (this.depth === undefined) {
      this.depth = 0;
    }
  }

  ngOnInit() {

    this.navService.currentUrl.subscribe((url: string) => {
      if (this.item.routerLink && url) {
        this.expanded = url.indexOf(`/${this.item.routerLink}`) === 0;
        this.ariaExpanded = this.expanded;
      }
    });
  }

  onItemSelected(item: UserFunctionality) {
    if (!item.children || !item.children.length) {
      this.navService.closeNav();

      const splitUrl = item.routerLink.split('/');
       this.routeHandlerService.resetRouteConfig(item.routerLink)     // uncomment by vinay reload page on clicking same menu
      if (splitUrl[0] === '') {
        splitUrl.shift();
      }
      console.log('this.location.path(): ', this.location.path());
      const currentChildMicroApp = this.location.path().split('/')
      if (splitUrl[0] === currentChildMicroApp[1]) {
        this.location.replaceState(item.routerLink);
        const url = splitUrl.slice(1).join('/');
        this.iframeService.sendRouteChangeRequest(IFrameMessageSource.MAIN, { url, queryParam: null });
        return;
      }
      this.router.navigate([item.routerLink || '']).then(() => {
        // this.location.replaceState(item.routerLink);
        // const url = splitUrl.slice(1).join('/');
        // this.iframeService.sendRouteChangeRequest(IFrameMessageSource.MAIN, { url, queryParam: null })
      });
    }
    if (item.children && item.children.length) {
      this.expanded = !this.expanded;
    }
  }


}
