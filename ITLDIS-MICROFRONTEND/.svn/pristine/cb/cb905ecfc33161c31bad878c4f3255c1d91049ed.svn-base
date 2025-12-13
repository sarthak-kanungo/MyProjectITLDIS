import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PdiUrl } from '../../url-util/pdi-url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Result, DropDownDataSpecification } from '../../domain/pdi-domain';

@Injectable()
export class PdiPageWebService {

  constructor(private httpClient: HttpClient) { }

  savePdiData(data) {
    console.log(data)  
    return this.httpClient.post(PdiUrl.savePdiDataUrl, data)
  }
  getViewData(id: number): Observable<Result> {
    return this.httpClient.get<BaseDto<Result>>(`${PdiUrl.dataForViewUrl}/${id}`).pipe(map(dto => dto.result))
  }

  getDropDownCheckListData(checkpointId: string): Observable<DropDownDataSpecification> {
    return this.httpClient.get<BaseDto<DropDownDataSpecification>>(PdiUrl.getDropDownCheckListDataUrl, {
      params: new HttpParams().set('checkpointId', checkpointId)
    }).pipe(map(dto => dto.result))
  }

}
