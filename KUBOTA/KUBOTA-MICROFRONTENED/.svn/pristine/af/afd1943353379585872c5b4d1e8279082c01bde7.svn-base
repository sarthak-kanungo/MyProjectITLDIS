import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class ActivityEnquiryReportService {
  private static readonly searchUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchActivityEnquiryReport`;
  private static readonly downloadReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/downloadActivityEnquiryReport`;
   
  constructor(private httpClient: HttpClient) { }
  
  getSearchRecords(searchObj){
    return this.httpClient.post(ActivityEnquiryReportService.searchUrl, searchObj);
  }

  
  downloadReport(filter){
    return this.httpClient.post<Blob>(ActivityEnquiryReportService.downloadReport, filter,
      {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
