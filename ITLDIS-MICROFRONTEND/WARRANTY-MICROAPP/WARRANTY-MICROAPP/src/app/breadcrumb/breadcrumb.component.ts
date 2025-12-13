import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { BreadscrumbService } from './breadscrumb.service';
import { Subscription, Subject, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { filter } from 'rxjs/internal/operators/filter';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss']
})
export class BreadcrumbComponent implements OnInit {
  public appVersion = `${environment.version}`;
  private subscription = new Subscription();
  public breadcrumbList: Array<any> = [];
  private _breadcrumbList$ = new Subject<Array<any>>();
  public breadcrumbList$: Observable<Array<any>>;
  public menu: Array<any> = [];
  public currentDate: Date;
  public isLogin: boolean;
  public userName: string;
  public menuList = [];
  loginCount: string;
  breadcrumbListLength: number = 0;
  searchFilter: any;

  constructor(
    private breadscrumbService: BreadscrumbService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.currentDate = new Date();
    this.menu = this.breadscrumbService.getMenu();
    // console.log(' this.menu', this.menu);

    this.listenRouting();
    this.breadcrumbList$ = this._breadcrumbList$.asObservable();
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.searchFilter = qParam.searchFilter;
      console.log('this.searchFilter_BreadCrum', this.searchFilter);
    });
    
  }

  listenRouting() {
    let routerUrl: string, routerList: Array<any>, target: any;
    // this.router.events
    this.router.events.pipe(
      filter(e => e instanceof NavigationEnd)
    ).subscribe((router: any) => {
      // console.log('BreadcrumbComponent router==================>',router);

      routerUrl = router.urlAfterRedirects;
      if (routerUrl && typeof routerUrl === 'string') {
        target = this.menu;
        this.breadcrumbList.length = 0;
        routerList = routerUrl.slice(1).split('/');

        routerList.forEach((router: string, index) => {
          if (router.includes('?')) {
            router = router.split('?').slice(0, 1).toString();
          }

          target = (target as any[]).find(page => {
            return page.path === router
          });

          if (target) {
            this.breadcrumbList.push({
              name: target.name,
              path: (index === 0) ? target.path : `${this.breadcrumbList[index - 1].path}/${target.path}`
            });
          }
          if (index + 1 !== routerList.length) {
            if (target && target.children && target.children.length > 0) {
              target = target.children;
            }
          }
        });
        this._breadcrumbList$.next(this.breadcrumbList);
        this.breadcrumbListLength = this.breadcrumbList.length;
      }
    });
  }

  isNoPath(functionalityName: string) {
    if (functionalityName !== undefined) {
      let noPathPages = ['master', 'itldis masters', 'sales and presales', 'purchase', 'pre sales', 'sales', 'branch transfer', 'activity', 'schemes', 'salesmasters', 'service', 'pre sales service', 'spares', 'spares master', 'counter sales', 'service activities claims', 'customer service', 'warranty', 'warranty claims', 'workshop sales']
      let val = noPathPages.includes(functionalityName.toLowerCase());
      return val;
    }
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

}
