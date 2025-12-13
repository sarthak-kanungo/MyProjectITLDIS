import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeIdAutoCreate, LoginIdStatusDropDown, AssignRoleDropDown, SubmitDto, itldisUserMasterDomain } from '../../domain/itldis-user-domain';
import { itldisUserApi } from '../../url-util/itldis-user-url';
import { BaseDto } from 'BaseDto';

@Injectable()
export class CreateNewUserService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getEmployeeIdAuto(): Observable<EmployeeIdAutoCreate> {
    return this.httpClient.get<EmployeeIdAutoCreate> (itldisUserApi.employeeIdCreate)
  }
  getLoginIdStatusDropDown(): Observable<LoginIdStatusDropDown> {
    return this.httpClient.get<LoginIdStatusDropDown> (itldisUserApi.loginIdStatus)
  }

  getAssignRoleDropDown(): Observable<BaseDto<Array<itldisUserMasterDomain>>> {
    return this.httpClient.get<BaseDto<Array<itldisUserMasterDomain>>> (itldisUserApi.assignRole)
  }

  getAssignFunctions(loginUserId): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>> (itldisUserApi.assignFunctions, {
      params: new HttpParams().set('loginUserId', loginUserId)
    })
  }

  getEmployeeCodeDropDown(employeeId,forCreate): Observable<BaseDto<Array<EmployeeIdAutoCreate>>> {
    return this.httpClient.get<BaseDto<Array<EmployeeIdAutoCreate>>> (itldisUserApi.employeeIdSearch, {
      params: new HttpParams().set('employeeId', employeeId).set('forCreate',forCreate)
    })
  } 


}
