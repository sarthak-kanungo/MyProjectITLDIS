import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class FillRatioReportService {

  private static readonly searchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/fillRatioReport`;
  private static readonly exportUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/exportFillRatioReport`;
 
  constructor(private httpClient: HttpClient,
    private dateService: DateService) { }

  getSystemGeneratedDate(){
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
 
  searchRecords(searchObject){
    return this.httpClient.post(FillRatioReportService.searchUrl, searchObject);
  }
  exportSearchRecords(searchObject):Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(FillRatioReportService.exportUrl, searchObject, {
      observe: 'response', responseType: 'blob' as 'json' 
    })
  }
}
