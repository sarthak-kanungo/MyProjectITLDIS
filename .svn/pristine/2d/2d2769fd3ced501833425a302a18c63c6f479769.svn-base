import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { SparesPaymentReceiptApi } from '../../url-utils/spare-payment-receipt-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class SparePaymentReceiptSearchService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public searchByReceiptNumber(searchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.searchByReceiptNumber, {
      params: new HttpParams().set('receiptNumber', searchKey).set('documentType', 'Spares')
    }).pipe(map(ele => ele.result))
  }
  public searchByCustomerName(searchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.searchByCustomerNameUrl, {
      params: new HttpParams().set('customerName', searchKey)
    }).pipe(map(ele => ele.result))
  }
  public searchByCustomerMobileNumber(searchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.searchByCustomerMobileNoUrl, {
      params: new HttpParams().set('mobileNumber', searchKey)
    }).pipe(map(ele => ele.result))
  }
  public getReceiptMode(): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getReceiptModeUrl)
  }
}
