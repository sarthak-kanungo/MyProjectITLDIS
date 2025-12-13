import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class MonthlyFailureCodeSummaryReportService {
  static readonly searchurl = `${environment.baseUrl}/${environment.api}/crm/report/monthlyFailureCodeSummaryReport`
  static readonly downloadExcel = `${environment.baseUrl}/${environment.api}/crm/report/exportMFCS`
  
  constructor(private httpClient : HttpClient) { }

  getSummaryReport(searchobj){
    return this.httpClient.post(MonthlyFailureCodeSummaryReportService.searchurl, searchobj);
  }
  exportFailurecodeReport(searchObj){
    return this.httpClient.post<Blob>(MonthlyFailureCodeSummaryReportService.downloadExcel, searchObj,
    {observe: 'response', responseType: 'blob' as 'json' });
  }

}
