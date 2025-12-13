import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { CustomerOrderNumber, CustomerName } from '../../domain/so.domain';
import { SoApi } from '../../url-utils/so-urls';

@Injectable()
export class SoSearchWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getCustomerOrderNo(salesOrderNumber: string): Observable<Array<CustomerOrderNumber>> {
    return this.httpClient.get<BaseDto<Array<CustomerOrderNumber>>>(SoApi.getSalesOrderNumberAutocomplete, {
      params: new HttpParams()
        .append('salesOrderNumber', salesOrderNumber)
    }).pipe(map(dto => dto.result))
  }

  getCustomerName(customerName: string): Observable<Array<CustomerName>> {
    return this.httpClient.get<BaseDto<Array<CustomerName>>>(SoApi.getCustomerNameAutocomplete, {
      params: new HttpParams()
        .append('customerName', customerName)
    }).pipe(map(dto => dto.result))
  }
}
