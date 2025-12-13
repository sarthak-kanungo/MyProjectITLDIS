import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilterSearchServiceBooking } from '../../domain/service-booking-domain';
import { ServiceBookingApi } from '../../url-util/service-booking-url';

@Injectable()
export class ServiceBookingSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  serviceBookingSearch(filter : FilterSearchServiceBooking){
    return this.httpClient.post(ServiceBookingApi.serviceBookingSearch, filter)
  }
}
