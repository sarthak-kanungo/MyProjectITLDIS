import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SpecificationDropDown } from '../../domain/pdc-domain';
import { PdcApi } from '../../url-util/pdc-url';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class PdcChecklistWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  specificationDropdown(checkpointId: string): Observable<Array<SpecificationDropDown>> {
    return this.httpClient.get<BaseDto<Array<SpecificationDropDown>>>(PdcApi.specificationDropdown, {
      params: new HttpParams().set('checkpointId', checkpointId)
    }).pipe(map(dto => dto.result))
  }

}
