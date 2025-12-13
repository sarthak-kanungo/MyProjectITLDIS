import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { SearchAutocomplete, FrsNumberData, DropDownCategory } from '../../domain/job-card-domain';
import { BaseDto } from 'BaseDto';
import { JobCardUrl } from '../../url-util/job-card-url';

@Injectable()
export class LabourChargesWebService {

  constructor(private httpClient: HttpClient) { }

  frsAutocomplete(frsCode: string, modelId: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.autoCompletefrsNumber, {
      params: new HttpParams().set('frsCode', frsCode).set('modelId', modelId)
    }).pipe(map(dto => dto.result))
  }

  getDataFromFrsNumber(frsCode: string, modelId: string): Observable<FrsNumberData> {
    return this.httpClient.get<BaseDto<FrsNumberData>>(JobCardUrl.getDataFromFrsNumberUrl, {
      params: new HttpParams().set('frsCode', frsCode).set('modelId', modelId)
    }).pipe(map(dto => dto.result))
  }
  serviceCategory(): Observable<DropDownCategory> {
    return this.httpClient.get<BaseDto<DropDownCategory>>(JobCardUrl.dropDownLabourChargeCategory, {
      params: new HttpParams()
    }).pipe(map(dto => dto.result))
  }
}
