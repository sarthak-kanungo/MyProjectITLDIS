import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class SearchJobCardWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService

  ) { }
  searchJobCardTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(JobCardUrl.searchJobCardReopenApproval, searchData)
  }
  
  reopenJobCard(jobcardId:string){
      return this.httpClient.get(JobCardUrl.reopenJobcard+"/"+jobcardId);  
  }
}
