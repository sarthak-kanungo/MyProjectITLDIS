import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EnquiryDetailsForConversion } from 'ActivityProposalModule';
import { url } from 'inspector';

@Injectable()
export class EnquiryDetailsProposalService {
  private readonly getEnquiryNumberAutocomplete = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getEnquiryNumberAutocomplete`
  private readonly getEnquiryDetailsByEnquiryNumber = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getEnquiryDetailsByEnquiryNumber`
  private readonly getEnquiryForActivityPurposeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.getEnquiryForActivityPurpose}`


  constructor(private httpClient: HttpClient) { }

  enquiryDetailsForConversion = {} as Array<EnquiryDetailsForConversion>

  getEnquiryNumber(enquiryNumber) {
    return this.httpClient.get(`${this.getEnquiryNumberAutocomplete}`, {
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    })
  }

  getEnquiryDetailsByEnquiryNo(enquiryNumberId) {
    return this.httpClient.get(`${this.getEnquiryDetailsByEnquiryNumber}`, {
      params: new HttpParams().set('enquiryNumberId', enquiryNumberId)
    })
  }

  getEnquiriesByActivityPropoalId(proposalId: number) {
    return this.httpClient.get(`${this.getEnquiryForActivityPurposeUrl}/${proposalId}`)
  }

}
