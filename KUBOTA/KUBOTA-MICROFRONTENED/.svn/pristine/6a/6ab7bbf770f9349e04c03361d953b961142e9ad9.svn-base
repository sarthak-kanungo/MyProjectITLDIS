import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PdiUrl } from '../../url-util/pdi-url';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { DropDownDataSpecification } from '../../domain/pdi-domain';
import { map } from 'rxjs/operators';

@Injectable()
export class PdiChecklistWebService {

  constructor(private httpClient: HttpClient) { }


  getDropDownCheckListData(checkpointId: string): Observable<DropDownDataSpecification> {
    return this.httpClient.get<BaseDto<DropDownDataSpecification>>(PdiUrl.getDropDownCheckListDataUrl, {
      params: new HttpParams().set('checkpointId', checkpointId)
    }).pipe(map(dto => dto.result))
  }
}
