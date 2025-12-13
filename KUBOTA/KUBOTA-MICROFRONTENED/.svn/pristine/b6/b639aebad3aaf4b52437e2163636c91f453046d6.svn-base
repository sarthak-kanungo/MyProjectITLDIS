import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SaveAndSubmitReInstallation, ViewReInstallation } from '../../domain/re-installation-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ReInstallationApi } from '../../url-util/re-installation-urls';

@Injectable()
export class ReInstallationPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  saveAndSubmitReInstallation(formData : SaveAndSubmitReInstallation){
    return this.httpClient.post(ReInstallationApi.reInstallation, formData)
  }

  getRiById(id: string): Observable<ViewReInstallation> {
    return this.httpClient.get<BaseDto<ViewReInstallation>>(`${ReInstallationApi.getRiById}/${id}`
     ).pipe(map(dto => dto.result))
  }

}
