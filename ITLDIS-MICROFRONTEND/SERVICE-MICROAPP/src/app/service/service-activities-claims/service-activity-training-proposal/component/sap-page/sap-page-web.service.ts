import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SaveAndSubmitServiceActivityProposal, ViewServiceActivityProposal, ApproveServiceActivityProposal } from '../../domain/sap.domain';
import { SapApi } from '../../url-util/sap-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable()
export class SapPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  saveServiceActivityProposal(formData: SaveAndSubmitServiceActivityProposal){
    return this.httpClient.post(SapApi.saveServiceActivityProposal, formData)
  }

  getServiceActivityProposalByActivityNumber(activityNumber: string): Observable<ViewServiceActivityProposal> {
    return this.httpClient.get<BaseDto<ViewServiceActivityProposal>>(`${SapApi.getServiceActivityProposalByActivityNumber}`, {
      params: new HttpParams()
      .append('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }

  approveServiceActivityProposal(formData: ApproveServiceActivityProposal){
    return this.httpClient.post(SapApi.approveServiceActivityProposal, formData)
  }


}
