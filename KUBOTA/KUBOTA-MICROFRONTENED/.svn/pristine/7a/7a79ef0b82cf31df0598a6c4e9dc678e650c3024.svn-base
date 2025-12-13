import { Directive, Input, OnChanges, SimpleChanges, Output, EventEmitter, OnInit } from '@angular/core';
import { Location } from '@angular/common';
export interface UrlSegment {
  url: string,
  queryParam?: { [key: string]: any }
}
@Directive({
  selector: '[insertQueryParams]'
})
export class InsertQueryParamsDirective implements OnInit, OnChanges {

  @Input() insertQueryParams: { [key: string]: any };
  @Output() initialQueryParams = new EventEmitter<{ [key: string]: any }>();
  @Output() onUrlChange = new EventEmitter<UrlSegment>();
  constructor(
    private location: Location
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes.insertQueryParams && changes.insertQueryParams.currentValue) {
      this.updateQueryParams();
    }
  }
  ngOnInit(): void {
    this.location.onUrlChange((fullUrl: string, state) => {
      let url = `${fullUrl.split('?')[0]}`;
      if (url.startsWith('/')) {
        url = url.slice(1);
      }
      const queryParam = this.getQueryParams(fullUrl);
      this.onUrlChange.emit({ url, queryParam });
    });
    const queryParams = this.getQueryParams(this.location.path());
    if (queryParams) {
      this.initialQueryParams.emit(queryParams);
    }
  }
  private updateQueryParams() {
    const currentPath = this.location.path().split('?')[0];
    let queryParams: string = '';
    Object.entries(this.insertQueryParams).forEach((value, index) => {
      if (!queryParams) {
        queryParams = `${value[0]}=${value[1]}`;
        return;
      }
      queryParams += `&${value[0]}=${value[1]}`;
    })
    if (Object.keys(this.insertQueryParams).length) {
      this.location.go(currentPath, queryParams);
      return;
    }
    this.location.go(currentPath);
  }
  getQueryParams(url: string) {
    const queryString = url.split('?')[1];
    if (!queryString) {
      return;
    }
    const queryList = queryString.split('&');
    let queryParams: { [kay: string]: any } = {};
    queryList.forEach((val: string, index: number) => {
      if (val) {
        const queryParam = val.split('=');
        const key = queryParam[0];
        const value = queryParam[1].split('%20').join(' ');
        queryParams[key] = value;
      }
    });
    if (Object.keys(queryParams).length) {
      return queryParams;
    }
    return null;
  }
}
