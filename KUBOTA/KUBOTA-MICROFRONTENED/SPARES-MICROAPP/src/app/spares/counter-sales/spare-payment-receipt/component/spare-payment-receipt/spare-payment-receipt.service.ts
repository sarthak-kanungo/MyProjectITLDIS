import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparesPaymentReceiptApi } from '../../url-utils/spare-payment-receipt-urls';
import { BaseDto } from 'BaseDto';

@Injectable()
export class SparePaymentReceiptService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public getCustomerTypeList(): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getCustomerType, {
      params: new HttpParams().set('documentType', 'Payment Receipt')
    })
  }
  public getReceiptModeList(): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getReceiptModeUrl)
  }
  public getReceiptTypeList(): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getReceiptTypeUrl)
  }
  public searchByCustomerCode(searchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getCustomerCodeUrl, {
      params: new HttpParams().set('mobileNumber', searchKey)
    }).pipe(map((res: BaseDto<object[]>) => res.result))
  }
  public searchByMechanicOrRetailerCode(searchKey: string, customerType: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getRetailerOrMechanicAutocompleteUrl, {
      params: new HttpParams().set('searchKey', searchKey).set('customerType', customerType)
    }).pipe(map((res: BaseDto<object[]>) => res.result))
  }
  public searchByDealerCode(searchKey: string): Observable<object[]> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getDealerCodeAutocompleteUrl, {
      params: new HttpParams().set('dealerCode', searchKey)
    }).pipe(map((res: BaseDto<object[]>) => res.result))
  }
  public getDetailsFromCustomerCode(code: string): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getCustomerDetailsUrl, {
      params: new HttpParams().set('customerCode', code)
    })
  }
  public getDetailsFromDealerCode(code: string): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getDealerDetailsUrl, {
      params: new HttpParams().set('id', code)
    })
  }
  public getDetailsFromMechanicOrRetailerCode(code: string): Observable<BaseDto<object[]>> {
    return this.httpClient.get<BaseDto<object[]>>(SparesPaymentReceiptApi.getRetailerOrMechanicDetailsUrl, {
      params: new HttpParams().set('id', code)
    })
  }
}
