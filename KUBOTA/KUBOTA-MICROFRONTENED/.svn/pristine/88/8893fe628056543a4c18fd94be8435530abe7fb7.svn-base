import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { Observable } from 'rxjs';
import { HeadsDetailsByActivityNumber } from '../../domain/service-activity-claim.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityClaimHeadsWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getHeadsDataForActivityClaim(activityNumberId: number): Observable<Array<HeadsDetailsByActivityNumber>> {
    return this.httpClient.get<BaseDto<Array<HeadsDetailsByActivityNumber>>>(ServiceActivityClaimApi.getHeadsDataForActivityClaim, {
      params: new HttpParams()
        .append('activityNumberId', activityNumberId.toString())
    }).pipe(map(dto => dto.result))
  }
}
