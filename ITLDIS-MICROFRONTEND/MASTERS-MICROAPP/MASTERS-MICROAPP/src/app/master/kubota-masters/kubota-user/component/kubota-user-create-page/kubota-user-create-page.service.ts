import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginUser, SubmitDto } from '../../domain/itldis-user-domain';
import { itldisUserApi } from '../../url-util/itldis-user-url';
import { BaseDto } from 'BaseDto';


@Injectable()
export class itldisUserCreatePageService {

  constructor(
    private httpClient : HttpClient

  ) { }

  postSubmitDto(formData : SubmitDto) : Observable<BaseDto<SubmitDto>>{
    return this.httpClient.post<BaseDto<SubmitDto>> (itldisUserApi.submitData,formData)
  }

  updateUser(formData : SubmitDto) : Observable<BaseDto<SubmitDto>>{
    return this.httpClient.post<BaseDto<SubmitDto>> (itldisUserApi.updateData,formData)
  }

  viewitldisUserDetails(userName): Observable<BaseDto<LoginUser>> {
    return this.httpClient.get<BaseDto<LoginUser>> (itldisUserApi.viewitldisUserDetails, {
      params: new HttpParams().set('userName', userName)
    })
  } 
  
}
