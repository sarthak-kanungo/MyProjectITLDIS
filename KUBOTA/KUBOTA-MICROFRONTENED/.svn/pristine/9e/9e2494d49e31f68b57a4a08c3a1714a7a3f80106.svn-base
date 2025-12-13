import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ComplaintOrQueryResolutionUrls } from '../url-util/complaint-or-query-resolution.urls';

@Injectable({
  providedIn: 'root'
})
export class ComplaintOrQueryResolutionService {

  constructor(private httpClient: HttpClient) { }

  getLookupByCode(lookupCode:string){
    return this.httpClient.get(ComplaintOrQueryResolutionUrls.lookupCode, {
      params : new HttpParams().set('code', lookupCode)
    })
  }

  updateResolutionDetails(data){
    return this.httpClient.post(ComplaintOrQueryResolutionUrls.updateResolutionDetails, data);
  }

  /* Search Page Calls */
  getComplaintOrQueryResolutionSearchTableData(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      if (searchData[key] === null) {
        delete searchData[key]
      }
    })
    return this.httpClient.post(ComplaintOrQueryResolutionUrls.getComplaintOrQueryResolution, searchData)
  }
}
