import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { SapApi } from '../../url-util/sap-urls';
import { map } from 'rxjs/operators';
import { SubActivity } from '../../domain/sap.domain';

@Injectable()
export class SubActivityWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getSubActivityByActivityTypeId(activityTypeId: number): Observable<Array<SubActivity>> {
    return this.httpClient.get<BaseDto<Array<SubActivity>>>(`${SapApi.getSubActivityByActivityTypeId}`, {
      params: new HttpParams()
        .append('activityTypeId', activityTypeId.toString())
    }).pipe(map(dto => dto.result))
  }
}
