import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { PdcApi } from '../../url-util/pdc-url';
import { ChassisNoForSearch } from '../../domain/pdc-domain';

@Injectable()
export class PdcSearchWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  pdcCreateAutoCompleteChassisNo(chassisNo: string): Observable<Array<ChassisNoForSearch>> {
    return this.httpClient.get<BaseDto<Array<ChassisNoForSearch>>>(PdcApi.pdcCreateAutoCompleteChassisNo, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }
}
