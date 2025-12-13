import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReInstallationCheckPoint } from '../../domain/re-installation-domain';
import { BaseDto } from 'BaseDto';
import { ReInstallationApi } from '../../url-util/re-installation-urls';
import { map } from 'rxjs/operators';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class ReInstallationDetailsWebService {

  constructor(
    private httpClient : HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getAllReInstallationDetails(series: string): Observable<Array<ReInstallationCheckPoint>> {
    return this.httpClient.get<BaseDto<Array<ReInstallationCheckPoint>>>(ReInstallationApi.getAllReInstallationDetails, {
      params: new HttpParams()
        .append('chassis', series)
        .append('transType', 'RINS')
    }).pipe(map(dto => dto.result))
  }

}
