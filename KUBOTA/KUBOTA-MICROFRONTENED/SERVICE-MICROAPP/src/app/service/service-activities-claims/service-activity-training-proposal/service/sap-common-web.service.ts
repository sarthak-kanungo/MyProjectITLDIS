import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivityType, TargetedProduct } from '../domain/sap.domain';
import { SapApi } from '../url-util/sap-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class SapCommonWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getAllActivityType(): Observable<ActivityType> {
    return this.httpClient.get<BaseDto<ActivityType>>(`${SapApi.getAllActivityType}`).pipe(map(dto => dto.result))
  }

  getAllProduct(): Observable<TargetedProduct> {
    return this.httpClient.get<BaseDto<TargetedProduct>>(`${SapApi.getAllProduct}`).pipe(map(dto => dto.result))
  }

  getProposalBulkApprovalData():Observable<any>{
    return this.httpClient.get(SapApi.getProposalsPendingForApproval);
  }

  bulkApprovalForServiceActivityProposal(approveData:any):Observable<any>{
    return this.httpClient.post(SapApi.activityProposalGroupApproval,approveData)
  }

}
