import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SearchAutocomplete, ChassisNumberJobData, ServiceCategoryDropDownList, PlaceOfServiceDropDownList, ServicetypesList, ActivityEventTypesList, BookingNoJobData, TotalHoursCalculation, checkByChassisNo, } from '../../domain/job-card-domain';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';


@Injectable()
export class JobCardDetailWebService {

  private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.service}${urlService.reports}`;
  private readonly printSpareUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}`;
  
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

  servicetypesListDropDown(machineInventoryId, totalHour, serviceCategory, jobid): Observable<ServicetypesList> {
    return this.httpClient.get<BaseDto<ServicetypesList>>(JobCardUrl.dropDownServiceType, {
      params: new HttpParams().set('machineInventoryId', machineInventoryId).set('totalHour', totalHour).set('serviceCategory', serviceCategory).set('jobid',jobid)
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
  // get retreo details

  getRetrofitmentDetails(chassisNo): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(JobCardUrl.getRetrofitmentDetails, {
      params: new HttpParams().set('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

  // 
  getRetroItemAndLabourDetailsById(retroId):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(JobCardUrl.getRetroItemAndLabourDetailsById, {
      params: new HttpParams().set('retroId', retroId)
    }).pipe(map(dto => dto.result))
  }
  // get Implement Service
  getImplementByVinId(inventoryId):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(JobCardUrl.getImplements, {
      params: new HttpParams().set('inventoryId', inventoryId)
    }).pipe(map(dto => dto.result))
  }

  checkByChassisNo(machineInventoryId: number, jobCardFlag: string): Observable<BaseDto<checkByChassisNo>> {
    return this.httpClient.get<BaseDto<checkByChassisNo>>(JobCardUrl.checkByChassisNo, {
      params: new HttpParams()
        .append('machineInventoryId', machineInventoryId.toString())
        .append('jobCardFlag', jobCardFlag)
    })
  }
  lookupByCode(code){
    return this.httpClient.get<BaseDto<checkByChassisNo>>(JobCardUrl.lookupApiUrl, {
      params: new HttpParams()
        .append('code', code)
    })
  }
  print(jobCardNo, printStatus, type){
      if(type == 'jobcard')
          return this.httpClient.get<Blob>(this.printUrl+"/printJobcard", {
              params: new HttpParams().set('jobCardNo', jobCardNo)
                                      .set('printStatus', printStatus),
             observe: 'response', responseType: 'blob' as 'json'                         
          });
      else
          return this.httpClient.get<Blob>(this.printSpareUrl+"/printSalesInvoice", {
              params: new HttpParams().set('jobCardNo', jobCardNo)
                                      .set('printStatus', printStatus)
                                      .set('type', "Job Card"),
             observe: 'response', responseType: 'blob' as 'json'                         
          }); 
  }
}
