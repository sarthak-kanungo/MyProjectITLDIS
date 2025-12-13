import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { Observable } from 'rxjs/Observable';
import { AutoCompleteResult, PartDetails } from '../../domain/log-sheet.domain';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class LogSheetFailureWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  autoCompletePartNumber(partNumber: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(LogSheetApi.autoCompletePartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
  getPartDetailsByPartNumber(partNumber: string): Observable<PartDetails> {
    return this.httpClient.get<BaseDto<PartDetails>>(LogSheetApi.getPartDetailsByPartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
 getPartFailureCodeByCode(code: string): Observable<PartDetails> {
    return this.httpClient.get<BaseDto<PartDetails>>(LogSheetApi.getPartFailureCodeByCode, {
      params: new HttpParams().set('code', code)
    }).pipe(map(res => res.result))
  }
}
