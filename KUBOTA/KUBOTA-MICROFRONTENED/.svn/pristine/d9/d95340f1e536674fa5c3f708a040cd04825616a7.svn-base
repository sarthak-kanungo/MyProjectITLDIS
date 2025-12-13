import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ServiceActivityReportApi } from '../url-util/service-activity-report-urls';
import { ActivityEffectiveness } from '../domain/service-activity-report.domain';

@Injectable()
export class ServiceActivityReportCommonWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getActivityEffectiveness(): Observable<ActivityEffectiveness>{
    return this.httpClient.get<BaseDto<ActivityEffectiveness>>(ServiceActivityReportApi.getActivityEffectiveness).pipe(map(dto => dto.result))
  }

}
