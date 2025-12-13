import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutoItemNo, DetailsByItemNo } from '../../domain/part-quotation-domain';
import { BaseDto } from 'BaseDto';
import { PartQuotationApi } from '../../url-util/part-quotation-url';
import { map } from 'rxjs/operators';

@Injectable()
export class PartQuotationTableWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  autocompletePartNo(itemNumber: string, itemId: string): Observable<Array<AutoItemNo>> {
    return this.httpClient.get<BaseDto<Array<AutoItemNo>>>(PartQuotationApi.autocompletePartNo, {
      params: new HttpParams()
        .append('itemNumber', itemNumber)
        .append('itemId', itemId)
    }).pipe(map(dto => dto.result))
  }

  getSparePartDetailsForQuotation(itemNumber: string, state : string): Observable<DetailsByItemNo> {
    return this.httpClient.get<BaseDto<DetailsByItemNo>>(PartQuotationApi.getSparePartDetailsForQuotation, {
      params: new HttpParams()
        .append('itemNumber', itemNumber)
        .append('state', state)
    }).pipe(map(dto => dto.result))
  }

}
