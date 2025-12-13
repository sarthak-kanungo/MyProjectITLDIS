import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubmitDto } from '../../domain/function-master-domain';
import { FunctionMasterApi } from '../../url-util/function-master-url';


@Injectable()
export class FunctionMasterPageService {

  constructor(
    private httpClient : HttpClient
  ) { }

  postSubmitDto(formData : SubmitDto) : Observable<SubmitDto>{
    return this.httpClient.post<SubmitDto> (FunctionMasterApi.submitData,formData)
  }
}
