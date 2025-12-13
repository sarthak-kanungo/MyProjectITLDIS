import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { DropDownActivityType, TargetedProductDropDown, AutoActivityNo } from '../../domain/service-activity-report.domain';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityReportSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getAllActivityType(): Observable<DropDownActivityType> {
    return this.httpClient.get<BaseDto<DropDownActivityType>>(`${ServiceActivityReportApi.getAllActivityType}`).pipe(map(dto => dto.result))
  }
  getAllProduct(): Observable<TargetedProductDropDown> {
    return this.httpClient.get<BaseDto<TargetedProductDropDown>>(`${ServiceActivityReportApi.getAllProduct}`).pipe(map(dto => dto.result))
  }

  getActivityNumberForSearch(activityNumber: string): Observable<Array<AutoActivityNo>> {
    return this.httpClient.get<BaseDto<Array<AutoActivityNo>>>(ServiceActivityReportApi.getActivityNumberForSearch, {
      params: new HttpParams()
        .append('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }
}
