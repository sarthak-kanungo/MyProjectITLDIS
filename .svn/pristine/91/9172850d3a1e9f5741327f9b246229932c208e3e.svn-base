import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SearchAutocomplete, ChassisNumberJobData, ServiceCategoryDropDownList, PlaceOfServiceDropDownList, ServicetypesList, ActivityEventTypesList, BookingNoJobData, TotalHoursCalculation, checkByChassisNo, } from '../../domain/job-card-domain';

@Injectable()
export class JobCardDetailWebService {

  constructor(private httpClient: HttpClient) { }

  getSystemDate() {
    return this.httpClient.get(JobCardUrl.getSystemDateUrl)
  }
  bookingNumberAuto(searchString: string, jobCardFlag: any): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.autoCompleteBookingNoInJobCard, {
      params: new HttpParams().set('bookingNo', searchString).set('jobCardFlag', jobCardFlag)
    }).pipe(map(dto => dto.result))
  }
  bookingNoDetails(bookingId: string): Observable<BookingNoJobData> {
    return this.httpClient.get<BaseDto<BookingNoJobData>>(JobCardUrl.bookingNumberData, {
      params: new HttpParams().set('bookingId', bookingId)
    }).pipe(map(dto => dto.result))
  }

  chassisNumberAuto(searchString: string, checkBoxFlag): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.autoCompleteChassisNoInJobCard, {
      params: new HttpParams().set('chassisNo', searchString).set('preSalesFlag', checkBoxFlag)
    }).pipe(map(dto => dto.result))
  }
  activityNumberAuto(activityTypeId: string, activityNumber: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.getActivityNumberByActivityTypeForJobCard, {
      params: new HttpParams().set('activityTypeId', activityTypeId).set('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }

  chassisDetails(chassisNo: string, checkBoxFlag, jobCardFlag) {
    return this.httpClient.get<BaseDto<ChassisNumberJobData>>(JobCardUrl.getChassisDetailsByChassisNoInJobCard, {
      params: new HttpParams().set('chassisNo', chassisNo).set('preSalesFlag', checkBoxFlag).set('jobCardFlag', jobCardFlag)
    })
  }
  serviceCategoryDropDown(preSalesFlag: string, retrofitmentFlag: string): Observable<ServiceCategoryDropDownList> {
    return this.httpClient.get<BaseDto<ServiceCategoryDropDownList>>(JobCardUrl.dropDownServiceCategory, {
      params: new HttpParams().set('preSalesFlag', preSalesFlag).set('retrofitmentFlag', retrofitmentFlag)
    }).pipe(map(dto => dto.result))
  }
  placeOfServiceDropDown(preSalesFlag: string, ): Observable<PlaceOfServiceDropDownList> {
    return this.httpClient.get<BaseDto<PlaceOfServiceDropDownList>>(JobCardUrl.dropDownPlaceOfService, {
      params: new HttpParams().set('preSalesFlag', preSalesFlag)
    }).pipe(map(dto => dto.result))
  }

  servicetypesListDropDown(machineInventoryId, totalHour, serviceCategory): Observable<ServicetypesList> {
    return this.httpClient.get<BaseDto<ServicetypesList>>(JobCardUrl.dropDownServiceType, {
      params: new HttpParams().set('machineInventoryId', machineInventoryId).set('totalHour', totalHour).set('serviceCategory', serviceCategory)
    }).pipe(map(dto => dto.result))
  }
  activityEventTypesListDropDown(): Observable<ActivityEventTypesList> {
    return this.httpClient.get<BaseDto<ActivityEventTypesList>>(JobCardUrl.dropDownActivityEvent).pipe(map(dto => dto.result))
  }
  totalHourCalculation(currentHour, meterChangeHour, machineInventoryId): Observable<TotalHoursCalculation> {
    return this.httpClient.get<BaseDto<TotalHoursCalculation>>(JobCardUrl.getTotalHour, {
      params: new HttpParams().set('currentHour', currentHour).set('meterChangeHour', meterChangeHour).set('machineInventoryId', machineInventoryId)
    }).pipe(map(dto => dto.result))
  }

  checkByChassisNo(machineInventoryId: number, jobCardFlag: string): Observable<BaseDto<checkByChassisNo>> {
    return this.httpClient.get<BaseDto<checkByChassisNo>>(JobCardUrl.checkByChassisNo, {
      params: new HttpParams()
        .append('machineInventoryId', machineInventoryId.toString())
        .append('jobCardFlag', jobCardFlag)
    })
  }
}
