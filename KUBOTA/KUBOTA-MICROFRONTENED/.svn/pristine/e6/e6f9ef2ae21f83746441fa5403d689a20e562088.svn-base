import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { SearchFilterPaymentReceiptListDomain, PaymentReceiptListSearchDomain, ViewPaymentReceiptDomain } from 'search-payment-receipt-dto';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { DateService } from '../../../../../root-service/date.service';
import { environment } from '../../../../../../environments/environment';
@Injectable()
export class SearchPaymentRecieptService {

  private readonly getReceiptModeURL = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getReceiptMode}`;
  private readonly getReceiptTypeURL = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getAllReceiptType}`;
  private readonly getAllProductUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;
  private readonly getAllSeriesUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSeries}`;
  private readonly getAllModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllModel}`;
  private readonly getAllSubModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSubModel}`;
  private readonly getAllVariantUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`;
  private readonly getPaymentReceiptListUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getPaymentReceiptList}`;
  private readonly searchByReceiptNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.searchByReceiptNumber}`;
  private readonly getPaymentReceiptByReceiptNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getPaymentReceiptByReceiptNumber}`;
  private readonly searchByCustomerNameUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.searchByCustomerName}`;
  private readonly searchByCustomerMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.searchByMobileNumber}`;
  private readonly searchByItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemNo}`;

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  dropdownReceiptType(){
      return this.httpClient.get(`${this.getReceiptTypeURL}`)
  }
  dropdownReceiptModeType() {
    return this.httpClient.get(`${this.getReceiptModeURL}`)
  }

  dropdownGetAllProductType() {
    return this.httpClient.get(`${this.getAllProductUrl}`)
  }

  dropdownGetAllSeriesType() {
    return this.httpClient.get(`${this.getAllSeriesUrl}`)
  }

  dropdownGetAllModelType() {
    return this.httpClient.get(`${this.getAllModelUrl}`)
  }

  dropdowngetAllSubModelType() {
    return this.httpClient.get(`${this.getAllSubModelUrl}`)
  }

  dropdownGetAllVariantType() {
    return this.httpClient.get(`${this.getAllVariantUrl}`)
  }

  searchByReceiptNumber(receiptNumber) {
    return this.httpClient.get(`${this.searchByReceiptNumberUrl}`, {
      params: new HttpParams().set('receiptNumber', receiptNumber)
    })
  }
  searchByCustomerName(receiptNumber) {
    return this.httpClient.get(`${this.searchByCustomerNameUrl}`, {
      params: new HttpParams().set('customerName', receiptNumber)
    })
  }
  searchByCustomerMobileNo(receiptNumber) {
    return this.httpClient.get(`${this.searchByCustomerMobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', receiptNumber)
    })
  }
  searchByItemNumber(itemNumber) {
    return this.httpClient.get(`${this.searchByItemNumberUrl}`, {
      params: new HttpParams().set('itemNo', itemNumber)
    })
  }
  getPaymentReceiptByReceiptNumber(receiptNumber: string): Observable<BaseDto<ViewPaymentReceiptDomain>> {
    return this.httpClient.get<BaseDto<ViewPaymentReceiptDomain>>(`${this.getPaymentReceiptByReceiptNumberUrl}`, {
      params: new HttpParams().set('receiptNo', receiptNumber)
    })
  }
  
  searchUsingFilter(filter: SearchFilterPaymentReceiptListDomain): Observable<BaseDto<Array<PaymentReceiptListSearchDomain>>> {
    return this.httpClient.get<BaseDto<Array<PaymentReceiptListSearchDomain>>>(
      this.getPaymentReceiptListUrl, {
      params: this.prepareHttpParams(filter)
    })
  }

  private prepareHttpParams(filter: SearchFilterPaymentReceiptListDomain) {
    let httpParams = new HttpParams()
    for (const key of Object.keys(filter)) {
      if (filter[key]) {
        if ((key === 'toDate') || (key === 'fromDate')) {
          httpParams = httpParams.append(key, this.dateService.getDateIntoYYYYMMDD(filter[key]))
        }
        else {
          httpParams = httpParams.append(key, filter[key])
        }
      }
    }
    return httpParams
  }
}

