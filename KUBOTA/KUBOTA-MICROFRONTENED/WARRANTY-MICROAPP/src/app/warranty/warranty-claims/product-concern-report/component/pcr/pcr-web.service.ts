import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {PcrApi} from '../../url-utils/product-concern-report.urls';
import { map } from 'rxjs/operators';
import {FieldCondition, FailureType, SoilType, CropGrown, PCRDomain, JobCardHistory, ApproveQuantity, ViewPcr} from '../../domain/product-concern-report.domain';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PcrWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  dropDownFieldCondition(): Observable<Array<FieldCondition>> {
    return this.httpClient.get<BaseDto<Array<FieldCondition>>>(PcrApi.dropDownFieldCondition)
    .pipe(map(res => res.result))
  }
  dropDownFailureType(serviceJobcardId?: string): Observable<Array<FailureType>> {
    serviceJobcardId = serviceJobcardId == undefined ? '0':serviceJobcardId; 
    return this.httpClient.get<BaseDto<Array<FailureType>>>(PcrApi.dropDownFailureType, {
      params: new HttpParams().append('serviceJobcardId', serviceJobcardId)
    }).pipe(map(res => res.result))
  }
  dropDownCropCondition(): Observable<Array<FieldCondition>>{
    return this.httpClient.get<BaseDto<Array<FieldCondition>>>(PcrApi.dropDownCropCondition)
    .pipe(map(res => res.result))
  }
  dropDownImplCatg(): Observable<Array<any>>{
      return this.httpClient.get<BaseDto<Array<any>>>(PcrApi.dropDownImplCategory)
      .pipe(map(res => res.result))
    }
  getSoilType(): Observable<Array<SoilType>>{
    return this.httpClient.get<BaseDto<Array<SoilType>>>(PcrApi.getSoilType)
    .pipe(map(res => res.result))
  }
  getMajorCropGrown(): Observable<Array<CropGrown>>{
    return this.httpClient.get<BaseDto<Array<CropGrown>>>(PcrApi.getMajorCropGrown)
    .pipe(map(res => res.result))
  }
  getLookupByCode(code){
    return this.httpClient.get(PcrApi.syslookupByCode,{
      params : new HttpParams().set('code',code)
    })
    .pipe(map(res => res['result']))
  }
}
