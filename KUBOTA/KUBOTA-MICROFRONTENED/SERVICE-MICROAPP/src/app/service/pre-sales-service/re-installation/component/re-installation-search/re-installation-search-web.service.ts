import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReInstallationApi } from '../../url-util/re-installation-urls';
import { BaseDto } from 'BaseDto';
import { ReinstallationNumber } from '../../domain/re-installation-domain';
import { map } from 'rxjs/operators';

@Injectable()
export class ReInstallationSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  reInstallationNumberAuto(reInstallationNumber: string): Observable<Array<ReinstallationNumber>> {
    return this.httpClient.get<BaseDto<Array<ReinstallationNumber>>>(ReInstallationApi.reInstallationNumberAuto, {
      params: new HttpParams()
        .append('reInstallationNumber', reInstallationNumber)
    }).pipe(map(dto => dto.result))
  }
}
