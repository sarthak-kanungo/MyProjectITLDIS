import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { SourceCode, SourceName } from '../../domain/source-domain';
import { SourceApi } from '../../url-util/source-urls';

@Injectable()
export class SearchSourceWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getSourceCodeAutocomplete(sourceCode: string): Observable<Array<SourceCode>> {
    return this.httpClient.get<BaseDto<Array<SourceCode>>>(SourceApi.getSourceCodeAutocomplete, {
      params: new HttpParams()
        .append('sourceCode', sourceCode)
    }).pipe(map(dto => dto.result))
  }

  getSourceNameAutocomplete(sourceName: string): Observable<Array<SourceName>> {
    return this.httpClient.get<BaseDto<Array<SourceName>>>(SourceApi.getSourceNameAutocomplete, {
      params: new HttpParams()
        .append('sourceName', sourceName)
    }).pipe(map(dto => dto.result))
  }
}
