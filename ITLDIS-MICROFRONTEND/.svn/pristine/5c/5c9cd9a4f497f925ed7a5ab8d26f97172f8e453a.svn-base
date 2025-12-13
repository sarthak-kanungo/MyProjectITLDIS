import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { TransporterApi } from '../../url-util/transporter-urls';
import { Observable } from 'rxjs';
import { TransporterCode, TransporterName } from '../../domain/transporter-domain';
import { map } from 'rxjs/operators';

@Injectable()
export class TransporterSearchWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getTransporterCodeAutocomplete(transporterCode: string): Observable<Array<TransporterCode>> {
    return this.httpClient.get<BaseDto<Array<TransporterCode>>>(TransporterApi.getTransporterCodeAutocomplete, {
      params: new HttpParams()
        .append('transporterCode', transporterCode)
    }).pipe(map(dto => dto.result))
  }

  getTransporterNameAutocomplete(transporterName: string): Observable<Array<TransporterName>> {
    return this.httpClient.get<BaseDto<Array<TransporterName>>>(TransporterApi.getTransporterNameAutocomplete, {
      params: new HttpParams()
        .append('transporterName', transporterName)
    }).pipe(map(dto => dto.result))
  }
}
