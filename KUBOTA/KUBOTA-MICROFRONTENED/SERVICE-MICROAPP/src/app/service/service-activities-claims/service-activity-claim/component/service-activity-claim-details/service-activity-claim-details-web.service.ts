import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { ActivityDetailsByActivityNo, AutoCompActivityNo } from '../../domain/service-activity-claim.domain';

@Injectable()
export class ServiceActivityClaimDetailsWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  autoCompleteActivityNo(activityNumber: string, dealerId: number): Observable<Array<AutoCompActivityNo>> {
    return this.httpClient.get<BaseDto<Array<AutoCompActivityNo>>>(ServiceActivityClaimApi.getActivityNumberForActivityClaim, {
      params: new HttpParams()
        .append('activityNumber', activityNumber)
        .append('dealerId', dealerId.toString())
    }).pipe(map(dto => dto.result))
  }

  getHeaderDataForActivityClaim(activityNumberId: number): Observable<ActivityDetailsByActivityNo> {
    return this.httpClient.get<BaseDto<ActivityDetailsByActivityNo>>(ServiceActivityClaimApi.getHeaderDataForActivityClaim, {
      params: new HttpParams()
        .append('activityNumberId', activityNumberId.toString())
    }).pipe(map(dto => dto.result))
  }


}
