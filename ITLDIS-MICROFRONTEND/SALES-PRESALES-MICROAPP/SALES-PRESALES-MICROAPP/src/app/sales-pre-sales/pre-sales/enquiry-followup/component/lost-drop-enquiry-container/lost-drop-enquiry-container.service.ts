import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { LostDropEnquiryDomain } from 'LostDropEnquiry';

@Injectable()
export class LostDropEnquiryContainerService {

  private readonly resultURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getResult}`;
  private readonly lostDropReasonURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getLostDropReason}`;
  private readonly reasonURL =`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getReason}`;
  lostDropEnquiryDomain = {} as LostDropEnquiryDomain
  constructor(
    private http: HttpClient
  ) { }

  getResult(){
    return this.http.get(`${this.resultURL}`)
  }

  getLostDropReason(result: string){
    return this.http.get(`${this.lostDropReasonURL}`, {
      params: new HttpParams().set('result', result)
    })
  }

  getReason(){
    return this.http.get(`${this.reasonURL}`)
  }
}
