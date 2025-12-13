import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ComplaintOrQueryResolutionUrls } from '../../url-util/complaint-or-query-resolution.urls';
@Injectable({
  providedIn: 'root'
})
export class ComplaintOrQueryResolutionSearchPageService {
  constructor(
    private httpClient : HttpClient
  ) { }

  // serviceBookingSearch(filter : FilterSearchServiceBooking){
  //   return this.httpClient.post(ComplaintOrQueryResolutionUrls.serviceBookingSearch, filter)
  // }
}
