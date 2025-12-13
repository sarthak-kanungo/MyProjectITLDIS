import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class EnquiryReportService {
  private static readonly searchUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchEnquiryReport`;
  private static readonly downloadReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/downloadEnquiryReport`;
    
  constructor(private httpClient: HttpClient) { 

  }
  
  getSearchRecords(searchObj){
    return this.httpClient.post(EnquiryReportService.searchUrl, searchObj);
  }

  downloadReport(filter){
    return this.httpClient.post<Blob>(EnquiryReportService.downloadReport, filter,
      {observe: 'response', responseType: 'blob' as 'json' }
    )
  }

}
