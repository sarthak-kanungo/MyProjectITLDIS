import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { DateService } from '../../../../../root-service/date.service';
import { environment } from '../../../../../../environments/environment';
import { ExchangeInventoryListSearchDomain, SearchFilterExchangeInventoryListDomain } from 'search-exchange-inventory-dto';
import { SearchEnqiryListDomain, SearchEnquiryFilterDomain } from 'EnquirySearchCriteria';
import { EnquiryApi } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/url-utils/enquiry-urls';

@Injectable()
export class SearchExchangeInventoryService {

  private readonly searchByCustomerMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.searchByMobileNumber}`;
  private readonly searchByCustomerNameUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.searchByCustomerName}`;
  private readonly getEnquiryCodeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryNo}`;

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  searchByCustomerMobileNo(receiptNumber) {
    return this.httpClient.get(`${this.searchByCustomerMobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', receiptNumber)
    })
  }
  searchByCustomerName(receiptNumber) {
    return this.httpClient.get(`${this.searchByCustomerNameUrl}`, {
      params: new HttpParams().set('customerName', receiptNumber)
    })
  }
  searchEnquiryCode(enquiryNo: string, functionality: string) {
    return this.httpClient.get(`${this.getEnquiryCodeURL}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo).set('functionality',functionality)
    })
  }
  getEnquirySearch(filter: SearchFilterExchangeInventoryListDomain): Observable<BaseDto<Array<ExchangeInventoryListSearchDomain>>> {

    return this.httpClient.get<BaseDto<Array<ExchangeInventoryListSearchDomain>>>(
      EnquiryApi.exchangeInventoryController, {
      params: this.prepareHttpParams(filter)
    })
  }
  private prepareHttpParams(filter: SearchFilterExchangeInventoryListDomain) {
    let httpParams = new HttpParams()

    for (const key of Object.keys(filter)) {
      if (`` + filter[key]) {
        if (key == 'toDate' || key == 'fromDate') {
          httpParams = httpParams.append(key, this.convertDateToServerFormat(filter[key]))
        }
        else httpParams = httpParams.append(key, filter[key])
      }
    }
    console.log(httpParams)
    return httpParams
  }

  convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' +  date.getDate()
      return formattedDate
    }
    return null
  }
}
