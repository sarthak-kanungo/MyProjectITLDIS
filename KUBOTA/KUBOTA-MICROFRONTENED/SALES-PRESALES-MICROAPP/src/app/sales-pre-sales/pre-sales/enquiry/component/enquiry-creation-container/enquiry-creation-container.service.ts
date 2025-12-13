import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EnquiryDomain, EnquirySaveDomain, EnquiryDetailsDomain, EnquiryWithProspectMasterDomain, EnquiryWithCustomerMasterDomain } from 'EnquiryCreation';

@Injectable()
export class EnquiryCreationContainerService {
  private readonly getEnquiryURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiry}`
  private readonly purposeOfPurchaseURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getPurposeOfPurchase}`;
  private readonly generationActivityTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getGenerationActivityType}`;
  private readonly conversionActivityTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getConversionActivityType}`;
  private readonly setEnquiryTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.setEnquiryType}`;
  private readonly searchMarketingActivityNumberURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.searchMarketingActivityNumber}`;
  private readonly getSystemGeneratedDate = `${environment.baseUrl}${urlService.api}/getSystemGeneratedDate`;
  //http://192.168.15.109:8383/api/salesandpresales/enquiry/searchMarketingActivityNumber?activityNumber=m&activityPurpose=conversion
  //http://192.168.15.115:8383/api/getSystemGeneratedDate
  enquiryDomain = {} as EnquiryDomain
  enquirySaveDomain = {} as EnquirySaveDomain
  enquiryDetailsDomain = {} as EnquiryDetailsDomain
  enquiryWithProspectMasterDomain = {} as EnquiryWithProspectMasterDomain
  enquiryWithCustomerMasterDomain = {} as EnquiryWithCustomerMasterDomain
  constructor(
    private http: HttpClient
  ) { }

  dropdownPurposeOfPurchase() {
    return this.http.get(`${this.purposeOfPurchaseURL}`)
  }
  dropdowngenerationActivityType() {
    return this.http.get(`${this.generationActivityTypeURL}`)
  }
  dropdownconversionActivityType() {
    return this.http.get(`${this.conversionActivityTypeURL}`)
  }

  searchEnquiryByData(enquiryNo: string) {
    return this.http.get(`${this.getEnquiryURL}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo)
    })
  }

  setEnquiryType(enquiryDate: string, tentativePurchaseDate: string) {
    return this.http.get(`${this.setEnquiryTypeURL}`, {
      params: new HttpParams().set('enquiryDate', enquiryDate).set('tentativePurchaseDate', tentativePurchaseDate)
    })
  }
  searchMarketingActivityNumber(activityNumber: string, activityPurpose: string, source: string, activityType: string) {
      alert(activityNumber+":"+activityPurpose+":"+source+":"+ activityType);
     
	 return this.http.get(`${this.searchMarketingActivityNumberURL}`, {
          params: new HttpParams().set('activityNumber', activityNumber)
          .set('activityPurpose', activityPurpose)
          .set('source', source)
          .set('activityType', activityType)
        });
  }
  
  systemGeneratedDate(){
    return this.http.get(`${this.getSystemGeneratedDate}`)
  }
}