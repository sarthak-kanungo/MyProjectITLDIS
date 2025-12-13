import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { InstallationApi } from '../../url-util/installation-urls';
import { DropDownSpecification } from '../../domain/installation-domain';

@Injectable()
export class DeliveryInstallationWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  specificationDropdown(checkpointId: string): Observable<Array<DropDownSpecification>> {
    return this.httpClient.get<BaseDto<Array<DropDownSpecification>>>(InstallationApi.specificationDropdown, {
      params: new HttpParams().set('checkpointId', checkpointId)
    }).pipe(map(dto => dto.result))
  }

}
