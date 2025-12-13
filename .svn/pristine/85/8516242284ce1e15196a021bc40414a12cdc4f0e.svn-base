import { Injectable } from '@angular/core';
import { HttpClient,HttpParams, HttpResponse } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { DateService } from '../../../../../root-service/date.service';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BehaviorSubject, Observable } from 'rxjs';


@Injectable()
export class SearchJobCardWebService {
  private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.service}${urlService.reports}`;
  
  excelTypeSubject : BehaviorSubject<string> = new BehaviorSubject<string>('');
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService

  ) { }
  searchJobCardTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(JobCardUrl.postJobCardTableSearchUrl, searchData)
  }
  
  reopenJobCard(jobcardId:string){
      return this.httpClient.get(JobCardUrl.reopenJobcard+"/"+jobcardId);  
  }
  
  print(){
      return this.httpClient.get<Blob>(this.printUrl+"/printJobcard_blank", {
          observe: 'response', responseType: 'blob' as 'json'                         
      });
  }

  downloadJcExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(JobCardUrl.downloadJcExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
