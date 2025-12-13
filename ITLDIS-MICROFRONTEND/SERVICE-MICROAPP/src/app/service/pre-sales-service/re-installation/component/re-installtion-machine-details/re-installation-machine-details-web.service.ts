import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ReInstallationApi } from '../../url-util/re-installation-urls';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { chassisNumberList, DataByChassisNo } from '../../domain/re-installation-domain';

@Injectable()
export class ReInstallationMachineDetailsWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  chassisNoAuto(chassisNo: string, seriesId: number, chassisId : string): Observable<Array<chassisNumberList>> {
    return this.httpClient.get<BaseDto<Array<chassisNumberList>>>(ReInstallationApi.chassisNoAuto, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
        .append('seriesId', seriesId.toString())
        .append('chassisId', chassisId)
    }).pipe(map(dto => dto.result))
  }

  getDetailsByChassisNo(chassisNo: string): Observable<DataByChassisNo>{
    return this.httpClient.get<BaseDto<DataByChassisNo>>(ReInstallationApi.getDetailsByChassisNo, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }
}
