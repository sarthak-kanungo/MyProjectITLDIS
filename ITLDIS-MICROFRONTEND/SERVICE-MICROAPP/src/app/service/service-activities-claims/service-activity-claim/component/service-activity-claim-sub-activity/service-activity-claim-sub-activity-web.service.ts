import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubActivityDetailsByActivityNumber } from '../../domain/service-activity-claim.domain';
import { BaseDto } from 'BaseDto';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityClaimSubActivityWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getSubActivityForActivityClaim(activityProposalId: number): Observable<Array<SubActivityDetailsByActivityNumber>> {
    return this.httpClient.get<BaseDto<Array<SubActivityDetailsByActivityNumber>>>(ServiceActivityClaimApi.getSubActivityForActivityClaim, {
      params: new HttpParams()
        .append('activityProposalId', activityProposalId.toString())
    }).pipe(map(dto => dto.result))
  }
}
