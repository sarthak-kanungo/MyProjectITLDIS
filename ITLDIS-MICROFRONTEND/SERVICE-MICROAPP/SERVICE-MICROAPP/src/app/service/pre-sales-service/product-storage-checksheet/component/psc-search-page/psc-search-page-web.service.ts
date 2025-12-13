import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilterSearchPsc, SearchPscListTable } from '../../domain/psc-domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { PscApi } from '../../url-util/psc.url';

@Injectable()
export class PscSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getPscList(filter : FilterSearchPsc): Observable<BaseDto<Array<SearchPscListTable>>> {
    return this.httpClient.post<BaseDto<Array<SearchPscListTable>>>(
      PscApi.searchPsc, filter)
  }
}
