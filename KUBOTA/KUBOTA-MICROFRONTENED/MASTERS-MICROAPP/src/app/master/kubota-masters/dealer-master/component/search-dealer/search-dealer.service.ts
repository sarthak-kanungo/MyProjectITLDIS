import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DelaerMasterApi } from '../../url-utils/dealer-master-urls';
import { Observable } from 'rxjs';
import { DealerMasterDropdown } from '../../domain/dealer-master.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class SearchDealerService {

  constructor(
    private httpClient: HttpClient
  ) { }

  dealerNameAuto(dealerName: string): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>>(DelaerMasterApi.dealerNameAuto, {
      params: new HttpParams().append('dealerName', dealerName)
    }).pipe(map(res => res.result));
  }
  dealerCodeAuto(dealerCode: string): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>>(DelaerMasterApi.dealerCodeAuto, {
      params: new HttpParams().append('dealerCode', dealerCode)
    }).pipe(map(res => res.result));
  }
  allocatedTerritoryDropdown(): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>>(DelaerMasterApi.allocatedTerritoryDropdown)
    .pipe(map(res => res.result));
  }
  areaLevelDropdown(): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>>(DelaerMasterApi.areaLevelDropdown)
    .pipe(map(res => res.result));
  }
}
