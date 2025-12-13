import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { Observable } from 'rxjs/Observable';
import { ViewLogSheet } from '../../domain/log-sheet.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class LogSheetPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  warrantyLogsheetViewByLogsheetNo(logsheetNo: string): Observable<ViewLogSheet> {
    return this.httpClient.get<BaseDto<ViewLogSheet>>(LogSheetApi.warrantyLogsheetViewByLogsheetNo, {
      params: new HttpParams().append('LogsheetNo', logsheetNo)
    }).pipe(map(res => res.result))
  }
}
