import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FilterSearchServiceActivityClaim } from '../../domain/service-activity-claim.domain';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';

@Injectable()
export class ServiceActivityClaimSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  serviceActivityClaimSearch(filter : FilterSearchServiceActivityClaim){
    return this.httpClient.post(ServiceActivityClaimApi.serviceActivityClaimSearch, filter)
  }

  generateInvoice(claimId:number, invoiceType:string){
    return this.httpClient.get(ServiceActivityClaimApi.generateDealerInvoice, {
      params : new HttpParams().set('claimId', claimId.toString()).set('invoiceType', invoiceType)
    })
  }
  // updateStatusHold(id, remark){
  //   return this.httpClient.post(ServiceActivityClaimApi.approveServiceActivityClaim, {
  //     serviceActivityClaimId: id, 
  //     remark: remark,
  //     approvedAmount:0,
  //     approvalType: 'Hold'
  //   });
  // }
}
