import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DealerEmployeeApi } from '../../url-util/dealer-employee-urls';
import { DealerMasterDropdown,}from '../../domain/dealer-employee-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/internal/operators/map';

@Injectable()
export class FamilyDetailsService {

  constructor(
    private httpClient : HttpClient

  ) { }

  getReleationshipDropdown(): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>> (DealerEmployeeApi.relationshipDropdown)
    .pipe(map(res => res.result));
  }
}
