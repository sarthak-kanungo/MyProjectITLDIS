import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class CustomerSatisfactionService {

  static readonly lookupurl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`
  static readonly searchurl = `${environment.baseUrl}/${environment.api}/crm/report/searchCustomerSatisfactionReport`
  static readonly downloadExcel=`${environment.baseUrl}/${environment.api}/crm/report/cssExcelReport`
  
  constructor(private httpClient: HttpClient) { }

  
  getLookupByCode(code){
    return this.httpClient.get(CustomerSatisfactionService.lookupurl, {
      params : new HttpParams().set("code",code)
    })
  }
 
  getSatisfactionScoreReport(searchObj){
    return this.httpClient.post(CustomerSatisfactionService.searchurl, searchObj);
  }

  downloadExcel(downloadReport: any): Observable<HttpResponse<Blob>> {
    return this.httpClient.post<Blob>(CustomerSatisfactionService.downloadExcel, downloadReport, {
      observe: 'response',
      responseType: 'blob' as 'json'
    });
  }
}
