import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServiceBookingApi } from '../../url-util/service-booking-url';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { ViewServiceBooking, SaveAndSubmitServiceBooking } from '../../domain/service-booking-domain';

@Injectable()
export class ServiceBookingPageWebService {

  chassisDetailSubject:BehaviorSubject<any> = new BehaviorSubject<any>(null);
  
  constructor(
    private httpClient : HttpClient
  ) { }

  saveAndsubmitServiceBooking(formData: SaveAndSubmitServiceBooking){
    return this.httpClient.post(`${ServiceBookingApi.saveServiceBooking}`,formData)
  }

  viewServiceBookingById(id: string): Observable<ViewServiceBooking> {
    return this.httpClient.get<BaseDto<ViewServiceBooking>>(`${ServiceBookingApi.viewServiceBookingById}/${id}`
     ).pipe(map(dto => dto.result))
  }

  cancelServiceBooking(id: number, reason: string){
    return this.httpClient.get(`${ServiceBookingApi.cancelServiceBooking}`,{
      params: new HttpParams()
        .append('id', id.toString())
        .append('reason', reason)
    })
  }

}
