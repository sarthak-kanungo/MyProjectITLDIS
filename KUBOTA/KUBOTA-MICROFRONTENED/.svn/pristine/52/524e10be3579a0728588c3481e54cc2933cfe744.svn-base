import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilterInstallationSearch, SearchInstallationList } from '../../domain/installation-domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { InstallationApi } from '../../url-util/installation-urls';

@Injectable()
export class InstallationSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  searchDi(filter : FilterInstallationSearch): Observable<BaseDto<Array<SearchInstallationList>>> {
    return this.httpClient.post<BaseDto<Array<SearchInstallationList>>>(
      InstallationApi.searchDi, filter)
  }
}
