import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { Observable } from 'rxjs/Observable';
import { AutoCompleteResult, PartDetails } from '../../domain/log-sheet.domain';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';

@Injectable()
export class implementService {
    static readonly dropDownImplCategory = `${PcrApi.apiController}/dropDownImplCategory`;
  constructor(
    private httpClient: HttpClient
  ) { }
  dropDownImplCatg(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(PcrApi.dropDownImplCategory)
    .pipe(map(res => res.result))
  }
}



