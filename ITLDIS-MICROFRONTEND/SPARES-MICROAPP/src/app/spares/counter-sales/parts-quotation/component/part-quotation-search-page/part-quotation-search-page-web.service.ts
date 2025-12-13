import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilterQuotationSearch } from '../../domain/part-quotation-domain';
import { PartQuotationApi } from '../../url-util/part-quotation-url';

@Injectable()
export class PartQuotationSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getQuotationSearch(filter : FilterQuotationSearch){
    return this.httpClient.post(PartQuotationApi.getQuotationSearch, filter)
  }
}
