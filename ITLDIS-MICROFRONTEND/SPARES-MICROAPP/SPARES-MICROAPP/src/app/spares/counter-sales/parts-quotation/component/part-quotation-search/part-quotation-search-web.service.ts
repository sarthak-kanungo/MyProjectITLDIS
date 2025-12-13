import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuotationNumber, AutoCustomerName } from '../../domain/part-quotation-domain';
import { BaseDto } from 'BaseDto';
import { PartQuotationApi } from '../../url-util/part-quotation-url';
import { map } from 'rxjs/operators';

@Injectable()
export class PartQuotationSearchWebService {

  constructor(
    private httpClient: HttpClient
    ) { }

    getQuotationNumberAutocomplete(quotationNumber: string): Observable<Array<QuotationNumber>> {
      return this.httpClient.get<BaseDto<Array<QuotationNumber>>>(PartQuotationApi.getQuotationNumberAutocomplete, {
        params: new HttpParams()
          .append('quotationNumber', quotationNumber)
      }).pipe(map(dto => dto.result))
    }

    getCustomerNameAutocomplete(customerName: string): Observable<Array<AutoCustomerName>> {
      return this.httpClient.get<BaseDto<Array<AutoCustomerName>>>(PartQuotationApi.getCustomerNameAutocomplete, {
        params: new HttpParams()
          .append('customerName', customerName)
      }).pipe(map(dto => dto.result))
    }
}
