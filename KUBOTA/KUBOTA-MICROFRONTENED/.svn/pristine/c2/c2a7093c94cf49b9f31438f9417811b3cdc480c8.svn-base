import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InstallationApi } from '../../url-util/installation-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ViewInstallation, Installation } from '../../domain/installation-domain';

@Injectable()
export class InstallationPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  addDeliveryInstallation(InstallationSendData: Installation){
    let formData: FormData = new FormData();
    formData.append('serviceMachineInstallation', new Blob([JSON.stringify(InstallationSendData.serviceMachineInstallation)], { type: 'application/json' }))
    if (InstallationSendData.multipartFileList) {
      InstallationSendData.multipartFileList.forEach(element => {
        formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(InstallationApi.AddDeliveryInstallation, formData, { headers })
  }

  getDiById(id: string): Observable<ViewInstallation> {
    return this.httpClient.get<BaseDto<ViewInstallation>>(`${InstallationApi.getDiById}/${id}`
     ).pipe(map(dto => dto.result))
  }
  
}
