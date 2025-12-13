import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SourceApi } from '../url-util/source-urls';
import { Observable } from 'rxjs';
import { Purpose } from '../domain/source-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class SourceCommonWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getPurpose(): Observable<Purpose>{
    return this.httpClient.get<BaseDto<Purpose>>(SourceApi.getPurpose).pipe(map(dto => dto.result))
  }
}
