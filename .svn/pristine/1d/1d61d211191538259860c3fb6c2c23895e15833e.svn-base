import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class QuotationContainerService {

  private readonly getEnquiryCodeURL = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.enquiry }${ urlService.getEnquiryCode }`;
  private readonly getAutoCompleteOpenEnquiryForQuotationUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getAutoCompleteOpenEnquiryForQuotation }`;
  // /api/enquiry/getEnquiryNo 

  private readonly getEnquiryByEnquiryNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getEnquiryByEnquiryNo }`;
  // /api/Quotation/getEnquiryByEnquiryNo
  private readonly getQuotationCodeListUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getQuotationCode }`;

  constructor(private httpClient: HttpClient) { }

  searchEnquiryCode(enquiryNo: string, userId: string) {
    return this.httpClient.get(`${ this.getAutoCompleteOpenEnquiryForQuotationUrl }`, {
      params: new HttpParams()
        .set('enquiryNumber', enquiryNo)
    })
  }

  searchByEnquiryNo(enquiryNo: string) {
    return this.httpClient.get(`${ this.getEnquiryByEnquiryNoUrl }`, {
      params: new HttpParams()
        .set('enquiryNo', enquiryNo)
    })

  }
  getQuotationCodeList(quotationCode: string) {
    return this.httpClient.get(`${ this.getQuotationCodeListUrl }`, {
      params: new HttpParams().set('quotationCode', quotationCode)
    })
  }
}
