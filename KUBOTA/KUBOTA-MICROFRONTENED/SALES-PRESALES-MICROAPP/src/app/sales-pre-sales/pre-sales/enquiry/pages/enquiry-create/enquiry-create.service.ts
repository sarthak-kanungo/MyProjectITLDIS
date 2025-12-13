import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';

@Injectable()
export class EnquiryCreateService {

  private readonly postCreatedEnquiryURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.addEnquiry}`;
 
  constructor(
    private http: HttpClient,
  ) { }

  postCreateEnquiry(formData) {
   return this.http.post(`${this.postCreatedEnquiryURL}`, formData)
  }
}
