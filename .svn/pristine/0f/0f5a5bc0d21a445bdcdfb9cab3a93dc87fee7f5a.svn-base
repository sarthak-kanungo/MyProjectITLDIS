import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilterReInstallation } from '../../domain/re-installation-domain';
import { ReInstallationApi } from '../../url-util/re-installation-urls';

@Injectable()
export class ReInstallationSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  searchRi(filter : FilterReInstallation){
    return this.httpClient.post(ReInstallationApi.searchRi, filter)
  }
}
