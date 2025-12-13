import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { MachineSeries, AutoServiceStaffName,SpecificationDropDown } from '../domain/re-installation-domain';
import { ReInstallationApi } from '../url-util/re-installation-urls';

@Injectable()
export class ReInstallationCommonWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getSeries(): Observable<MachineSeries> {
    return this.httpClient.get<BaseDto<MachineSeries>>(`${ReInstallationApi.getSeries}`
     ).pipe(map(dto => dto.result))
  }


  serviceStaffNameAuto(serviceStaffName: string): Observable<Array<AutoServiceStaffName>> {
    return this.httpClient.get<BaseDto<Array<AutoServiceStaffName>>>(ReInstallationApi.serviceStaffNameAuto, {
      params: new HttpParams()
        .append('serviceStaffName', serviceStaffName)
    }).pipe(map(dto => dto.result))
  }
  
  specificationDropdown(checkpointId: string): Observable<Array<SpecificationDropDown>> {
      return this.httpClient.get<BaseDto<Array<SpecificationDropDown>>>(ReInstallationApi.specificationDropdown, {
        params: new HttpParams().set('checkpointId', checkpointId)
      }).pipe(map(dto => dto.result))
    }
}
