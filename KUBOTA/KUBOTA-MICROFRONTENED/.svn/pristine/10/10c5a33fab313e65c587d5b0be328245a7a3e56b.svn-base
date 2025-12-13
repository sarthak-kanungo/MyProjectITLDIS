import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { BaseDto } from 'BaseDto';
import { SparesSalesInvoiceApi } from '../../url-utils/spares-sales-invoice-api';
import {SearchPCRAutoComplete } from '../../domain/spares-sales-invoice.domain';

@Injectable()
export class SparesSalesInvoiceSearchWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public searchByInvoiceNumber(invoiceSearchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesSalesInvoiceApi.getSparesInvoiceNumberAutocomplete, {
      params: new HttpParams().set('invoiceNumber', invoiceSearchKey)
    }).pipe(map((res: BaseDto<object[]>) => res.result));
  }
  autoCompleteWcrNo(txt:string):Observable<SearchPCRAutoComplete>{
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(SparesSalesInvoiceApi.autoCompleteWcrNo, {
        params: new HttpParams().set('wcrNo', txt)
      }).pipe(map(res => res.result))  
  }
  searchAutoCompleteJobCode(jobCode: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(SparesSalesInvoiceApi.searchAutoCompleteJobCode, {
      params: new HttpParams().set('jobCode', jobCode)
    }).pipe(map(res => res.result))
  }


}
