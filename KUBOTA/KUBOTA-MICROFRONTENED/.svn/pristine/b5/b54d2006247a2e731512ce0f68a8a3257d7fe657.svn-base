import { Injectable, EventEmitter } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ActivityProposalCreateService {

  private readonly postCreatedActivityProposalURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.saveMarketingActivityProposal}`;
  //salesandpresales/marketingActivityProposal/saveMarketingActivityProposal
  constructor(private http: HttpClient) { }
  postCreateActivityProposal(formData) {
    return this.http.post(`${this.postCreatedActivityProposalURL}`, formData)
  }
  btnClick = new EventEmitter<number>()
}


