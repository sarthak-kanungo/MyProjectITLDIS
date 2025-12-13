import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivityNo, ActivityStatus } from '../../domain/sap.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SapApi } from '../../url-util/sap-urls';

@Injectable()
export class SapSearchWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  autoCompleteActivityNo(searchString: string): Observable<Array<ActivityNo>> {
    return this.httpClient.get<BaseDto<Array<ActivityNo>>>(SapApi.getActivityProposalSearchForListing, {
      params: new HttpParams()
        .append('searchString', searchString)
    }).pipe(map(dto => dto.result))
  }
  getServiceActivityProposalStatus(): Observable<ActivityStatus> {
    return this.httpClient.get<BaseDto<ActivityStatus>>(SapApi.getServiceActivityProposalStatus).pipe(map(dto => dto.result))
  }
}
