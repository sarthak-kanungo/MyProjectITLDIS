import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { DetailsByChassisNumber, ServiceType } from '../../domain/service-booking-domain';
import { ServiceBookingApi } from '../../url-util/service-booking-url';
import { DateService } from '../../../../../root-service/date.service';
import { TotalHoursCalculation } from '../../../job-card/domain/job-card-domain';
import { JobCardUrl } from '../../../job-card/url-util/job-card-url';

@Injectable()
export class BookingDetailsWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getChassisDetailsByChassisNoInJobCard(chassisNo: number, preSalesFlag: string, jobCardFlag: string): Observable<BaseDto<DetailsByChassisNumber>> {
    return this.httpClient.get<BaseDto<DetailsByChassisNumber>>(ServiceBookingApi.getChassisDetailsByChassisNoInJobCard, {
      params: new HttpParams()
        .append('chassisNo', chassisNo.toString())
        .append('preSalesFlag', preSalesFlag)
        .append('jobCardFlag', jobCardFlag)
    })
  }

  getServiceTypeByHour(machineInventoryId: number, totalHour: number, serviceCategory: string,jobid): Observable<ServiceType> {
    return this.httpClient.get<BaseDto<ServiceType>>(`${ServiceBookingApi.getServiceTypeByHour}`, {
      params: new HttpParams()
        .append('machineInventoryId', machineInventoryId.toString())
        .append('totalHour', totalHour.toString())
        .append('serviceCategory', serviceCategory)
        .append('jobid', jobid)
    }
    ).pipe(map(dto => dto.result))
  }
  totalHourCalculation(currentHour, meterChangeHour, machineInventoryId): Observable<TotalHoursCalculation> {
    return this.httpClient.get<BaseDto<TotalHoursCalculation>>(JobCardUrl.getTotalHour, {
      params: new HttpParams().set('currentHour', currentHour).set('meterChangeHour', meterChangeHour).set('machineInventoryId', machineInventoryId)
    }).pipe(map(dto => dto.result))
  }
}
