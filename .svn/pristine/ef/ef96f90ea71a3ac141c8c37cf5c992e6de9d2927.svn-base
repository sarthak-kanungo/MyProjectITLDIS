import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EmployeeIdAuto, EmployeeIdAutoCreate, EmployeeNameAuto, EmployeeSearchFilter, EmployeeSearchList } from '../../domain/kubota-user-domain';
import { KubotaUserApi } from '../../url-util/kubota-user-url';
import { BaseDto } from 'BaseDto';
import { UtilsService } from '../../../../../utils/utils.service';



@Injectable()
export class SearchKubotaUserService {

  constructor(
    private httpClient : HttpClient,
    private utilsService: UtilsService
  ) { }

  getEmployeeIdAuto(): Observable<EmployeeIdAuto> {
    return this.httpClient.get<EmployeeIdAuto> (KubotaUserApi.employeeIdSearch)
  }

  getEmployeeNameAuto(): Observable<EmployeeNameAuto> {
    return this.httpClient.get<EmployeeNameAuto> (KubotaUserApi.employeeNameSearch)
  }

  getEmployeeSearch(filter: EmployeeSearchFilter) {
    filter = this.utilsService.removeEmptyKey<EmployeeSearchFilter>(filter);
    console.log("filter_service--->", filter);
    return this.httpClient.post<BaseDto<any>> (KubotaUserApi.employeeSearch, filter);
  }

  getEmployeeCodeDropDown(employeeId,searchparam): Observable<BaseDto<Array<EmployeeIdAutoCreate>>> {
    return this.httpClient.get<BaseDto<Array<EmployeeIdAutoCreate>>> (KubotaUserApi.employeeIdSearch, {
      params: new HttpParams().set('employeeId', employeeId).set('forCreate',searchparam)
    })
  }
}
