import { Injectable } from '@angular/core';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityClaimPhotoWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getActivityReportPhotosByProposalId(activityNumberId: number){
    return this.httpClient.get(ServiceActivityClaimApi.getActivityReportPhotosByProposalId, {
      params: new HttpParams()
        .append('activityNumberId', activityNumberId.toString())
    })
  }

  getServiceReport(proposalId:any){
    return this.httpClient.get(ServiceActivityClaimApi.getReportImagesByReportId, {
      params: new HttpParams()
        .append('proposalId', proposalId.toString())
    })
  }

}
