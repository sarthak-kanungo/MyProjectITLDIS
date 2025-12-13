import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { FailurePart } from '../../domain/product-concern-report.domain';
import { PcrApi } from '../../url-utils/product-concern-report.urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class FailurePartsWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  autoCompleteFailurePart(machineMasterId: string, code:string): Observable<FailurePart[]> {
    return this.httpClient.get<BaseDto<FailurePart[]>>(PcrApi.autoCompletePartFailureCode, {
      params: new HttpParams().append('machineMasterId', machineMasterId).append('code', code)
    }).pipe(map(res => res.result));
  }
}
