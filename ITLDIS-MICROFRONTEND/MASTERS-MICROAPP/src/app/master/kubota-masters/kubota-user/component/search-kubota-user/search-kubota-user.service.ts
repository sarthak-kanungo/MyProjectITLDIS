import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EmployeeIdAuto, EmployeeIdAutoCreate, EmployeeNameAuto, EmployeeSearchFilter, EmployeeSearchList } from '../../domain/itldis-user-domain';
import { itldisUserApi } from '../../url-util/itldis-user-url';
import { BaseDto } from 'BaseDto';
import { UtilsService } from '../../../../../utils/utils.service';



@Injectable()
export class SearchitldisUserService {

  constructor(
    private httpClient : HttpClient,
    private utilsService: UtilsService
  ) { }

  getEmployeeIdAuto(): Observable<EmployeeIdAuto> {
    return this.httpClient.get<EmployeeIdAuto> (itldisUserApi.employeeIdSearch)
  }

  getEmployeeNameAuto(): Observable<EmployeeNameAuto> {
    return this.httpClient.get<EmployeeNameAuto> (itldisUserApi.employeeNameSearch)
  }

  getEmployeeSearch(filter: EmployeeSearchFilter) {
    filter = this.utilsService.removeEmptyKey<EmployeeSearchFilter>(filter);
    console.log("filter_service--->", filter);
    return this.httpClient.post<BaseDto<any>> (itldisUserApi.employeeSearch, filter);
  }

  getEmployeeCodeDropDown(employeeId,searchparam): Observable<BaseDto<Array<EmployeeIdAutoCreate>>> {
    return this.httpClient.get<BaseDto<Array<EmployeeIdAutoCreate>>> (itldisUserApi.employeeIdSearch, {
      params: new HttpParams().set('employeeId', employeeId).set('forCreate',searchparam)
    })
  }
}
