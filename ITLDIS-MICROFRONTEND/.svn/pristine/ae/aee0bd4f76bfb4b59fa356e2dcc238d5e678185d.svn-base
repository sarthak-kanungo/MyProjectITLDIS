import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InstallationApi } from '../../url-util/installation-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { InstallationNumber, AutoChassisNumber } from '../../domain/installation-domain';

@Injectable()
export class InstallationSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  installationNumberAuto(installationNumber: string): Observable<Array<InstallationNumber>> {
    return this.httpClient.get<BaseDto<Array<InstallationNumber>>>(InstallationApi.installationNumberAuto, {
      params: new HttpParams()
        .append('installationNumber', installationNumber)
    }).pipe(map(dto => dto.result))
  }

  chassisNoAutoForSearch(chassisNo: string): Observable<Array<AutoChassisNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoChassisNumber>>>(InstallationApi.chassisNoAutoForSearch, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }
}
