import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class MarketingClaimReportService {
  private static readonly searchUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchMarketingClaimReport`;
  private static readonly searchClaimStatusUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchActivityClaimStatusReport`;
  private static readonly searchProposalStatusUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchActivityProposalStatusReport`;
  private static readonly searchReportStatusUrl = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/searchActivityReportStatusReport`;
  
  private static readonly downloadMarketingClaimReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/exportMarketingClaimReport`;
  private static readonly downloadClaimStatusReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/exportActivityClaimStatusReport`;
  private static readonly downloadProposalStatusReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/exportActivityProposalStatusReport`;
  private static readonly downloadReportStatusReport = `${environment.baseUrl}/${environment.api}/salesandpresales/reports/exportActivityReportStatusReport`;
  

  constructor(private httpClient: HttpClient) { 

  }
  
  getSearchRecords(searchObj, requestType){
    if(requestType=="activityReportStatus"){
      return this.httpClient.post(MarketingClaimReportService.searchReportStatusUrl, searchObj);
    }else if(requestType=="activityProposalStatus"){
      return this.httpClient.post(MarketingClaimReportService.searchProposalStatusUrl, searchObj);
    }else if(requestType=="activityClaimStatus"){
      return this.httpClient.post(MarketingClaimReportService.searchClaimStatusUrl, searchObj);
    }else {
      return this.httpClient.post(MarketingClaimReportService.searchUrl, searchObj);
    }
  }

  downloadReport(filter, requestType): Observable<HttpResponse<Blob>>{
    if(requestType=="activityReportStatus"){
      return this.httpClient.post<Blob>(MarketingClaimReportService.downloadReportStatusReport, filter,
        {observe: 'response', responseType: 'blob' as 'json' }
      )
    }else if(requestType=="activityProposalStatus"){
      return this.httpClient.post<Blob>(MarketingClaimReportService.downloadProposalStatusReport, filter,
        {observe: 'response', responseType: 'blob' as 'json' }
      )
    }else if(requestType=="activityClaimStatus"){
      return this.httpClient.post<Blob>(MarketingClaimReportService.downloadClaimStatusReport, filter,
        {observe: 'response', responseType: 'blob' as 'json' }
      )
    }else {
      return this.httpClient.post<Blob>(MarketingClaimReportService.downloadMarketingClaimReport, filter,
          {observe: 'response', responseType: 'blob' as 'json' }
        )
    }
  }
}
