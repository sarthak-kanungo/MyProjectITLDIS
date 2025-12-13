import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MachineDetailsByActivityNo, ServiceDetailsByActivityNo } from '../../domain/service-activity-report.domain';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceMachineDetailsWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getMachineDetails(activityProposalId: number, fromDate:string, toDate:string): Observable<Array<MachineDetailsByActivityNo>> {
    return this.httpClient.get<BaseDto<Array<MachineDetailsByActivityNo>>>(ServiceActivityReportApi.getMachineDetails, {
      params: new HttpParams()
        .append('activityProposalId', activityProposalId.toString())
        .append('fromDate', fromDate)
        .append('toDate', toDate)
    }).pipe(map(dto => dto.result))
  }
  getServiceDetails(activityProposalId: number, fromDate:string, toDate:string): Observable<Array<ServiceDetailsByActivityNo>> {
    return this.httpClient.get<BaseDto<Array<ServiceDetailsByActivityNo>>>(ServiceActivityReportApi.getServiceDetails, {
      params: new HttpParams()
        .append('activityProposalId', activityProposalId.toString())
        .append('fromDate', fromDate)
        .append('toDate', toDate)
    }).pipe(map(dto => dto.result))
  }
}
