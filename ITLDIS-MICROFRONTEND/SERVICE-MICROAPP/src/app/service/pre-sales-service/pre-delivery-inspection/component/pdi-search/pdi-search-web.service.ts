import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchAutocomplete } from '../../domain/pdi-domain';
import { PdiUrl } from '../../url-util/pdi-url';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PdiSearchWebService {

  constructor(private httpClient: HttpClient) { }
  searchByChassisNumber(searchString: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(PdiUrl.chassisNumberUrl, {
      params: new HttpParams().set('searchString', searchString)
    }).pipe(map(dto => dto.result))

  }

  searchByInvoiceNumber(kaiInvoiceNumber: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(PdiUrl.invoiceNumberUrl, {
      params: new HttpParams().set('kaiInvoiceNumber', kaiInvoiceNumber)
    }).pipe(map(dto => dto.result))

  }
  searchByGrnNumber(dmsGrnNumber: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(PdiUrl.grnNumberUrl, {
      params: new HttpParams().set('dmsGrnNumber', dmsGrnNumber)
    }).pipe(map(dto => dto.result))

  }

}
