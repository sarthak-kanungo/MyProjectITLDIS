import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PartIssueUrl } from '../../url-util/part-issue.url';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';
import { PartRequisitionUrl } from '../../../part-requisition/url-util/part-requisition.url';

@Injectable()
export class PartIssueSearchApiService {

  constructor(
    private httpClient: HttpClient
    ) { }
    searchApiNo(apiNo: string) {
      return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.searchApiNo, {
        params: new HttpParams().set('apiNo',`${apiNo}`)
      }).pipe(map(res => res.result));
    }
    searchPartRequisitionNo(requisitionNo: string) {
      return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.searchPartRequisitionNo, {
        params: new HttpParams().set('requisitionNo', requisitionNo)
      }).pipe(map(res => res.result));
    }
    searchJobCardNo(jobCardNo: string) {
      return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.searchJobCardNo, {
        params: new HttpParams().set('jobCardNo', jobCardNo)
      }).pipe(map(res => res.result));
    }
    getRequisitionPurpose() {
      return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.getRequisitionPurpose)
        .pipe(map(res => res.result));
    }
}
