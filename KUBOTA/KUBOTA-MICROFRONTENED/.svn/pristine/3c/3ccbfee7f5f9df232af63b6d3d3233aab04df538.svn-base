import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DropdownActivityType, ActivityEffectivenessDropDown, ActivityClaimNo, AutoCompActivityNo } from '../../domain/service-activity-claim.domain';
import { BaseDto } from 'BaseDto';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceActivityClaimSearchWebService {
  static readonly lookupUrl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`
  
  constructor(
    private httpClient: HttpClient
  ) { }

  getAllActivityType(): Observable<DropdownActivityType> {
    return this.httpClient.get<BaseDto<DropdownActivityType>>(`${ServiceActivityClaimApi.getAllActivityType}`).pipe(map(dto => dto.result))
  }
  getActivityEffectiveness(): Observable<ActivityEffectivenessDropDown>{
    return this.httpClient.get<BaseDto<ActivityEffectivenessDropDown>>(ServiceActivityClaimApi.getActivityEffectiveness).pipe(map(dto => dto.result))
  }

  getActivityClaimNumber(claimNumber: string): Observable<Array<ActivityClaimNo>> {
    return this.httpClient.get<BaseDto<Array<ActivityClaimNo>>>(ServiceActivityClaimApi.getActivityClaimNumberForSearch, {
      params: new HttpParams()
        .append('claimNumber', claimNumber)
    }).pipe(map(dto => dto.result))
  }

  getActivityNumberForSearch(activityNumber: string): Observable<Array<AutoCompActivityNo>> {
    return this.httpClient.get<BaseDto<Array<AutoCompActivityNo>>>(ServiceActivityClaimApi.getActivityNumberForSearch, {
      params: new HttpParams()
        .append('activityNumber', activityNumber)
    }).pipe(map(dto => dto.result))
  }

  getLookup(code){
    return this.httpClient.get(ServiceActivityClaimSearchWebService.lookupUrl, {
      params : new HttpParams().set('code',code)
    });
  }
}
