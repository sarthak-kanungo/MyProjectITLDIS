import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginUser, SubmitDto } from '../../domain/kubota-user-domain';
import { KubotaUserApi } from '../../url-util/kubota-user-url';
import { BaseDto } from 'BaseDto';


@Injectable()
export class KubotaUserCreatePageService {

  constructor(
    private httpClient : HttpClient

  ) { }

  postSubmitDto(formData : SubmitDto) : Observable<BaseDto<SubmitDto>>{
    return this.httpClient.post<BaseDto<SubmitDto>> (KubotaUserApi.submitData,formData)
  }

  updateUser(formData : SubmitDto) : Observable<BaseDto<SubmitDto>>{
    return this.httpClient.post<BaseDto<SubmitDto>> (KubotaUserApi.updateData,formData)
  }

  viewKubotaUserDetails(userName): Observable<BaseDto<LoginUser>> {
    return this.httpClient.get<BaseDto<LoginUser>> (KubotaUserApi.viewKubotaUserDetails, {
      params: new HttpParams().set('userName', userName)
    })
  } 
  
}
