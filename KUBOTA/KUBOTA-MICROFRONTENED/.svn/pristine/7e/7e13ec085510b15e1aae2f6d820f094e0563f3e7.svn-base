import { Injectable } from '@angular/core';
import { TransporterApi } from '../../url-util/transporter-urls';
import { HttpParams, HttpClient } from '@angular/common/http';
import { FilterTransporterSearch } from '../../domain/transporter-domain';

@Injectable()
export class TransporterSearchPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  searchTransporterMaster(filter: FilterTransporterSearch) {
    return this.httpClient.post(
      TransporterApi.searchTransporterMaster, filter)
  }

  changeActiveStatus(id: number) {
    return this.httpClient.put(TransporterApi.changeActiveStatus, null, {
      params: new HttpParams()
        .append('id', id.toString())
    })
  }
}
