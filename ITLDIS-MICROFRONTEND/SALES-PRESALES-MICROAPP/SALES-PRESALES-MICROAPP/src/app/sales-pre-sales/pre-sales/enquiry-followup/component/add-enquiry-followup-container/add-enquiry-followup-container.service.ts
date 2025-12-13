import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AddEnquiryFollowUp, AddEnquiryLostDropDomain, Enquiry, EnquiryFollowUpDomain } from 'AddEnquiryFollowup';

@Injectable()
export class AddEnquiryFollowupContainerService {
  private readonly autoPupolateDataByEnquiryCodeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryFollowByEnquiryCode}`
  enquiryFollowUpDomain = {} as EnquiryFollowUpDomain
  addEnquiryFollowUp = {} as AddEnquiryFollowUp
  addEnquiryLostDropDomain = {} as AddEnquiryLostDropDomain
  enquiry = {} as Enquiry
  constructor(
    private http : HttpClient,
  ) { }


  autoPupolateDataByEnquiryCode(enquiryNo: string) {
    return this.http.get(`${this.autoPupolateDataByEnquiryCodeURL}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo)
    })
  }
}
