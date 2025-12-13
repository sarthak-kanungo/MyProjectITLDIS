import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { map } from 'rxjs/operators';
import { HeaderDetailsByActivityNo, AutoActivityNo } from '../../domain/service-activity-report.domain';

@Injectable()
export class ServiceActivityReportDetailsWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  autoCompleteActivityNo(activityNumber: string): Observable<Array<AutoActivityNo>> {
    return this.httpClient.get<BaseDto<Array<AutoActivityNo>>>(ServiceActivityReportApi.activityNumberAuto, {
      params: new HttpParams()
        .append('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }

  getHeaderDetails(activityProposalId: number): Observable<HeaderDetailsByActivityNo> {
    return this.httpClient.get<BaseDto<HeaderDetailsByActivityNo>>(ServiceActivityReportApi.getHeaderDetails, {
      params: new HttpParams()
        .append('activityProposalId', activityProposalId.toString())
    }).pipe(map(dto => dto.result))
  }
  
}
