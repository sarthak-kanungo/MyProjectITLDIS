import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FilterSearchServiceActivityProposal } from '../../domain/sap.domain';
import { SapApi } from '../../url-util/sap-urls';

@Injectable()
export class SapSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  serviceActivityProposalSearch(filter : FilterSearchServiceActivityProposal){
    return this.httpClient.post(SapApi.serviceActivityProposalSearch, filter)
  }

  updateStatusHold(id, remark){
    return this.httpClient.post(SapApi.approveServiceActivityProposal, {
      serviceActivityProposalId: id, 
      remark: remark,
      approvedAmount:0,
      approvedFlag: 'Hold'
    });
  }
}

