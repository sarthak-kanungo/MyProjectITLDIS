import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { PartRequisitionUrl } from '../../url-util/part-requisition.url';
import { map } from 'rxjs/operators';

@Injectable()
export class PartRequisitionSearchApiService {

  constructor(private httpClient: HttpClient) { }
  searchPartRequisitionNo(requisitionNo: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.searchPartRequisitionNo, {
      params: new HttpParams().set('requisitionNo', requisitionNo)
    }).pipe(map(res => res.result));
  }
  searchJobCardNo(jobCardNo: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.searchJobCardNo, {
      params: new HttpParams().set('jobCardNo', jobCardNo)
    }).pipe(map(res => res.result));
  }
  getRequisitionPurpose() {
    return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.getRequisitionPurpose)
      .pipe(map(res => res.result));
  }
}
