import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DropDownMechanicName, SourceOfBooking, ServiceCategory, PlaceOfService, AutoCompChassisNumber, ActivityTypeDropDown, ActivityNumber } from '../domain/service-booking-domain';
import { ServiceBookingApi } from '../url-util/service-booking-url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceBookingCommonWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getDropDownMechanicName(): Observable<DropDownMechanicName> {
    return this.httpClient.get<BaseDto<DropDownMechanicName>>(`${ServiceBookingApi.getDropDownMechanicName}`
    ).pipe(map(dto => dto.result))
  }

  dropDownSourceOfBooking(): Observable<SourceOfBooking> {
    return this.httpClient.get<BaseDto<SourceOfBooking>>(`${ServiceBookingApi.dropDownSourceOfBooking}`
    ).pipe(map(dto => dto.result))
  }

  dropDownServiceCategory(preSalesFlag: string, retrofitmentFlag: string): Observable<ServiceCategory> {
    return this.httpClient.get<BaseDto<ServiceCategory>>(`${ServiceBookingApi.dropDownServiceCategory}`, {
      params: new HttpParams()
        .append('preSalesFlag', preSalesFlag)
        .append('retrofitmentFlag', retrofitmentFlag)
    }).pipe(map(dto => dto.result))
  }

  dropDownPlaceOfService(preSalesFlag: string): Observable<PlaceOfService> {
    return this.httpClient.get<BaseDto<PlaceOfService>>(`${ServiceBookingApi.dropDownPlaceOfService}`, {
      params: new HttpParams()
        .append('preSalesFlag', preSalesFlag)
    }
    ).pipe(map(dto => dto.result))
  }

  autoCompleteChassisNoInJobCard(chassisNo: string, preSalesFlag: string): Observable<Array<AutoCompChassisNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoCompChassisNumber>>>(ServiceBookingApi.autoCompleteChassisNoInJobCard, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
        .append('preSalesFlag', preSalesFlag)
    }).pipe(map(dto => dto.result))
  }

  getAllActivityType(): Observable<ActivityTypeDropDown> {
    return this.httpClient.get<BaseDto<ActivityTypeDropDown>>(`${ServiceBookingApi.getAllActivityType}`).pipe(map(dto => dto.result))
  }

  autoCompleteActivityNo(activityTypeId: string, activityNumber: string): Observable<Array<ActivityNumber>> {
    return this.httpClient.get<BaseDto<Array<ActivityNumber>>>(ServiceBookingApi.getActivityNumberByActivityTypeForJobCard, {
      params: new HttpParams()
        .append('activityTypeId', activityTypeId)
        .append('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }

}
