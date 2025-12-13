import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { InstallationApi } from '../url-util/installation-urls';
import { AutoCsbNumber } from '../domain/installation-domain';

@Injectable()
export class InstallationCommonWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  csbNoAuto(csbNo: string, model:string): Observable<Array<AutoCsbNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoCsbNumber>>>(InstallationApi.getAvailableCsbNo, {
      params: new HttpParams()
        .append('csbNo', csbNo)
        .append('model', model)
    }).pipe(map(dto => dto.result))
  }
}
