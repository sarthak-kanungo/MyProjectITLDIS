import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { WcrReport, WcrDetail, WcrReportAutoComplete, WcrReportSearch } from '../../domain/wcr-report.domain';
import { WcrReportApi } from '../../url-utils/wcr-report-url'
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class WcrReportCreatePageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  wcrReport(wcrReport: WcrReportSearch): Observable<Array<WcrDetail>> {
    return this.httpClient.post<BaseDto<Array<WcrDetail>>>(WcrReportApi.wcrReport, wcrReport).pipe(map(res => res.result))
  }
  getDealerCodeAutocomplete(dealerCode: string): Observable<WcrReportAutoComplete> {
    return this.httpClient.get<BaseDto<WcrReportAutoComplete>>(WcrReportApi.getDealerCodeAutocomplete, {
      params: new HttpParams().append('dealerCode', dealerCode)
    }).pipe(map(res => res.result))
  }
}
