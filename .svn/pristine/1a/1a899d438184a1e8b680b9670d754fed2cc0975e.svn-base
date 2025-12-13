import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobCardDetailsByActivityNo } from '../../domain/service-activity-report.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';

@Injectable()
export class ActivityReportJobCardDetailsWebService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  getJobCardDetails(activityProposalId: number, fromDate:string, toDate:string): Observable<Array<JobCardDetailsByActivityNo>> {
    return this.httpClient.get<BaseDto<Array<JobCardDetailsByActivityNo>>>(ServiceActivityReportApi.getJobCardDetails, {
      params: new HttpParams()
        .append('activityProposalId', activityProposalId.toString())
        .append('fromDate', fromDate)
        .append('toDate', toDate)
    }).pipe(map(dto => dto.result))
  }
}
