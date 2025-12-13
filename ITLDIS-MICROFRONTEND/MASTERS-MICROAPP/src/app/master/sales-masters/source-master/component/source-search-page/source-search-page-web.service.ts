import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FilterSourceSearch } from '../../domain/source-domain';
import { SourceApi } from '../../url-util/source-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class SourceSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  searchEnquiryMaster(filter : FilterSourceSearch){
    return this.httpClient.post(
      SourceApi.searchEnquiryMaster, filter)
  }

  changeActiveStatus(id: number){
    return this.httpClient.put(SourceApi.changeActiveStatus, null, {
      params: new HttpParams()
        .append('id', id.toString())
    })
  }
}
