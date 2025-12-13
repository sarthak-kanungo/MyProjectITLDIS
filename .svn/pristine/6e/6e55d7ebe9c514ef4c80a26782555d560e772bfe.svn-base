import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceBookingApi } from '../../url-util/service-booking-url';
import { BookingNo, ServiceType, MachineModel, Status, EngineNo } from '../../domain/service-booking-domain';
import { HttpParams, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class ServiceBookingSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  autoCompleteBookingNo(bookingNo: string, jobCardFlag: string): Observable<Array<BookingNo>> {
    return this.httpClient.get<BaseDto<Array<BookingNo>>>(ServiceBookingApi.autoCompleteBookingNo, {
      params: new HttpParams()
        .append('bookingNo', bookingNo)
        .append('jobCardFlag', jobCardFlag)
    }).pipe(map(dto => dto.result))
  }

  dropDownModel(): Observable<MachineModel> {
    return this.httpClient.get<BaseDto<MachineModel>>(ServiceBookingApi.dropDownModel).pipe(map(dto => dto.result))
  }

  dropDownServiceType(serviceCategoryId: number, modelId: number): Observable<ServiceType> {
    return this.httpClient.get<BaseDto<ServiceType>>(ServiceBookingApi.dropDownServiceType, {
      params: new HttpParams()
        .append('serviceCategoryId', serviceCategoryId.toString())
        .append('modelId', modelId.toString())
    }).pipe(map(dto => dto.result))
  }

  dropDownBookingStatus(): Observable<Status> {
    return this.httpClient.get<BaseDto<Status>>(ServiceBookingApi.dropDownBookingStatus).pipe(map(dto => dto.result))
  }

  autoCompleteEngineNumber(engineNo: string): Observable<Array<EngineNo>> {
    return this.httpClient.get<BaseDto<Array<EngineNo>>>(ServiceBookingApi.autoCompleteEngineNumber, {
      params: new HttpParams()
        .append('engineNo', engineNo)
    }).pipe(map(dto => dto.result))
  }

}
