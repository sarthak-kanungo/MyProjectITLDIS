import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class EnquiryFollowupCreateService {

  private readonly addEnquiryFollowupURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.addEnquiryFollowUp}`;
  private readonly addEnquiryLostDropURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.addEnquiryLostDrop}`

  ///api/enquiryFollowUp/addEnquiryFollowUp
  ///api/enquiryFollowUp/addEnquiryLostDrop

  constructor(
    private http: HttpClient
  ) { }

  postEnquiryFollowupData(formData) {
    return this.http.post(`${this.addEnquiryFollowupURL}`, formData)
  }

  postEnquiryLostDropData(formData) {
    return this.http.post(`${this.addEnquiryLostDropURL}`, formData)
  }

}
