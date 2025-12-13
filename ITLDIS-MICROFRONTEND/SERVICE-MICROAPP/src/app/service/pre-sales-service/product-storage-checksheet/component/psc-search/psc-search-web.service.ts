import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { PscNumber, AutoChasisNumber } from '../../domain/psc-domain';
import { PscApi } from '../../url-util/psc.url';

@Injectable()
export class PscSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  autoCompletePscNo(pscNo: string): Observable<Array<PscNumber>> {
    return this.httpClient.get<BaseDto<Array<PscNumber>>>(PscApi.autoCompletePscNo, {
      params: new HttpParams()
        .append('pscNo', pscNo)
    }).pipe(map(dto => dto.result))
  }

  getChassisNo(chassisNo: string): Observable<Array<AutoChasisNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoChasisNumber>>>(PscApi.autoCompleteChassisNumberForSearch, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

}
