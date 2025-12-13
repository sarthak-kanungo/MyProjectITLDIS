import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PdcApi } from '../../url-util/pdc-url';
import { FilterPdcSearch, SearchPdcListTable } from '../../domain/pdc-domain';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';

@Injectable()
export class PdcSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getPdcList(filter : FilterPdcSearch): Observable<BaseDto<Array<SearchPdcListTable>>> {
    return this.httpClient.post<BaseDto<Array<SearchPdcListTable>>>(
      PdcApi.searchPdc, filter)
  }

}
