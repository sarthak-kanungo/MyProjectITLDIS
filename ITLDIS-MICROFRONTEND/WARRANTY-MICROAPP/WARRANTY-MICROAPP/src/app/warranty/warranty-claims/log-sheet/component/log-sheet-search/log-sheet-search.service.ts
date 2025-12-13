import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { Observable } from 'rxjs/Observable';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SearchPCRAutoComplete } from '../../../product-concern-report/domain/product-concern-report.domain';

@Injectable()
export class LogSheetSearchService {

  constructor(
    private httpClient: HttpClient
  ) { }

  searchLogsheetNo(logsheetNo: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(LogSheetApi.searchLogsheetNo, {
      params: new HttpParams().set('logsheetNo', logsheetNo)
    }).pipe(map(res => res.result))
  }
  getLookupByCode(code){
    return this.httpClient.get(LogSheetApi.lookupUrl,{
      params: new HttpParams().set('code', code)
    })
  }
  searchCustomerMobileNo(mobileNo: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(LogSheetApi.searchCustomerMobileNo, {
      params: new HttpParams().set('mobileNo', mobileNo)
    }).pipe(map(res => res.result))
  }
}
