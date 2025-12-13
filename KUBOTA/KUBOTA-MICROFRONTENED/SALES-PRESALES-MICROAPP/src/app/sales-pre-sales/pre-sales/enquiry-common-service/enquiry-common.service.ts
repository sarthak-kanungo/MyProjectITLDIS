import { Injectable , EventEmitter } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { urlService } from '../../../webservice-config/baseurl';
import { environment } from '../../../../environments/environment';
import { Observable, BehaviorSubject } from 'rxjs';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { BaseDto } from 'BaseDto';

@Injectable()
export class EnquiryCommonService {

  emCommon : BehaviorSubject<number> = new BehaviorSubject<number>(0)

  private readonly getEnquiryCodeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryNo}`;
  private readonly sourceURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquirySource}${urlService.getSource}`;
  private readonly followupTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getFollowUpType}`;
  private readonly retailconversionActivityTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getRetailConversionActivityType}`;
  private readonly brandURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getBrands}`;
  private readonly getAllSalesPersonURL = `${environment.baseUrl}${urlService.api}${urlService.dealerEmployeeMaster}${urlService.getSalesPerson}`
  private readonly getEnquiryByEnquiryNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryByEnquiryNumber}${urlService.enquiryNumber}`
  private readonly getEnquiryNumberNameMobileNoTehsilUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryNumberNameMobileNoTehsil}`
 
 //http://192.168.15.109:8383/api/salesandpresales/enquiry/getEnquiryNumberNameMobileNoTehsil?search=e
  constructor(private http: HttpClient
  ) { }

  dropdownSource() {
    return this.http.get(`${ this.sourceURL }`)
  }
  dropdownfollowupType() {
    return this.http.get(`${this.followupTypeURL}`)
  }
  dropdownretailconversionActivityType() {
    return this.http.get(`${ this.retailconversionActivityTypeURL }`)
  }

  dropdownbrand(){
    return this.http.get(`${this.brandURL}`)
  }

  searchEnquiryCode(enquiryNo: string, functionality: string) {
    return this.http.get(`${this.getEnquiryCodeURL}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo).set('functionality',functionality)
    })
  }
  
  getEnquiryNumberNameMobileNoTehsil(search: string) {
    return this.http.get(`${this.getEnquiryNumberNameMobileNoTehsilUrl}`, {
      params: new HttpParams().set('search', search)
    })
  }
  getAllSalesPerson(){
    return this.http.get(`${this.getAllSalesPersonURL}`)
  }

  getEnquiryByeEnquiryNumber(enquiryNumber: string): Observable<BaseDto<ViewEnquiryDomain>> {
    return this.http.get<BaseDto<ViewEnquiryDomain>>(`${this.getEnquiryByEnquiryNumberUrl}`,{
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    })
  }
}
