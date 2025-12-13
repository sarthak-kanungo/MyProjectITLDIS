import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DelaerMasterApi } from '../../url-utils/dealer-master-urls';
import { SearchDealerMaster, SearchResponse } from '../../domain/dealer-master.domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class DealerMasterSearchService {

  constructor(
    private httpClient: HttpClient
  ) { }

  searchDealer(searchDetail: SearchDealerMaster): Observable<BaseDto<Array<SearchResponse>>> {
    return this.httpClient.post<BaseDto<Array<SearchResponse>>>(DelaerMasterApi.searchDealer, searchDetail)
  }
}
