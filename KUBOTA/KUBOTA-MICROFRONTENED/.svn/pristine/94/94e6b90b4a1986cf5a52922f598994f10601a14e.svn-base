import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class EnquiryEditService {
  private readonly postUpdateEnquiryURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.updateEnquiry}`;
//  /POST /api/salesandpresales/enquiry/updateEnquiry
  constructor(
    private http : HttpClient
  ) { }
  postUpdateEnquiry(formData) {
    return this.http.put(`${this.postUpdateEnquiryURL}`, formData)
   }
}
