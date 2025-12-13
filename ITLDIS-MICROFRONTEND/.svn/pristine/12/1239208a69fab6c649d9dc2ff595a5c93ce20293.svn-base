import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PdcApi } from '../../url-util/pdc-url';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { DataByChassisNo, CheckpointListByModel, ChassisNo } from '../../domain/pdc-domain';
import { map } from 'rxjs/operators';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class PreDeliveryCheckWebService {

  constructor(
    private httpClient : HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getChassisNumberAutoComplete(chassisNo: string): Observable<Array<ChassisNo>> {
    return this.httpClient.get<BaseDto<Array<ChassisNo>>>(PdcApi.getChassisNumberAutoComplete, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

  getChassisDetailsByChassisNo(chassisNo: string): Observable<BaseDto<DataByChassisNo>> {
    return this.httpClient.get<BaseDto<DataByChassisNo>>(PdcApi.getChassisDetailsByChassisNo, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    })
  }

  getAggregateAndCheckPointByModel(chassis: string): Observable<Array<CheckpointListByModel>> {
    /*return this.httpClient.get<BaseDto<Array<CheckpointListByModel>>>(PdcApi.getAggregateAndCheckPointByModel, {
      params: new HttpParams()
        .append('model', model)
    }).pipe(map(dto => dto.result))*/
     return this.httpClient.get<BaseDto<Array<CheckpointListByModel>>>(PdcApi.getAggregateAndCheckPointByModel,
            { params : new HttpParams()
                .append('transType', 'PDC')
                .append('chassis', chassis)
            }).pipe(map(dto=>dto.result))
  }


}
