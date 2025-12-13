import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SapApi } from '../../url-util/sap-urls';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Heads } from '../../domain/sap.domain';

@Injectable()
export class HeadsWebService {

  constructor(
    private httpClient: HttpClient
  ) { }


  getAllHeadsByActivityTypeId(activityTypeId: number): Observable<Heads> {
    return this.httpClient.get<BaseDto<Heads>>(`${SapApi.getAllHeadsByActivityTypeId}`, {
      params: new HttpParams()
        .append('activityTypeId', activityTypeId.toString())
    }).pipe(map(dto => dto.result))
  }

}
