import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { FilterSearchServiceActivityReport } from '../../domain/service-activity-report.domain';

@Injectable()
export class ServiceActivityReportSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  serviceActivityReportSearch(filter : FilterSearchServiceActivityReport){
    return this.httpClient.post(ServiceActivityReportApi.serviceActivityReportSearch, filter)
  }

}
