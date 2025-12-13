import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchAutocomplete, PartNumberData, DropDownCategory } from '../../domain/job-card-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { JobCardUrl } from '../../url-util/job-card-url';

@Injectable()
export class PartRequisitionWebService {

  constructor(private httpClient: HttpClient) { }

  partNumberAutocomplete(partNumber: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.autoCompletePartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(dto => dto.result))
  }

  getDataFromPartNumber(partNumber: string): Observable<Array<PartNumberData>> {
    return this.httpClient.get<BaseDto<Array<PartNumberData>>>(JobCardUrl.getPartDetailsByPartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(dto => dto.result))
  }

  serviceCategory(preSalesFlag: string): Observable<DropDownCategory[]> {
    return this.httpClient.get<BaseDto<DropDownCategory[]>>(JobCardUrl.getDropDownCategory, {
      params: new HttpParams().set('preSalesFlag', preSalesFlag)
    }).pipe(map(dto => dto.result))
  }
}
