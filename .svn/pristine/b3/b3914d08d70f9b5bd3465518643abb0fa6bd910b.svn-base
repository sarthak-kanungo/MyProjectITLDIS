import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeIdAutoCreate, LoginIdStatusDropDown, AssignRoleDropDown, SubmitDto, KubotaUserMasterDomain } from '../../domain/kubota-user-domain';
import { KubotaUserApi } from '../../url-util/kubota-user-url';
import { BaseDto } from 'BaseDto';

@Injectable()
export class CreateNewUserService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getEmployeeIdAuto(): Observable<EmployeeIdAutoCreate> {
    return this.httpClient.get<EmployeeIdAutoCreate> (KubotaUserApi.employeeIdCreate)
  }
  getLoginIdStatusDropDown(): Observable<LoginIdStatusDropDown> {
    return this.httpClient.get<LoginIdStatusDropDown> (KubotaUserApi.loginIdStatus)
  }

  getAssignRoleDropDown(): Observable<BaseDto<Array<KubotaUserMasterDomain>>> {
    return this.httpClient.get<BaseDto<Array<KubotaUserMasterDomain>>> (KubotaUserApi.assignRole)
  }

  getAssignFunctions(loginUserId): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>> (KubotaUserApi.assignFunctions, {
      params: new HttpParams().set('loginUserId', loginUserId)
    })
  }

  getEmployeeCodeDropDown(employeeId,forCreate): Observable<BaseDto<Array<EmployeeIdAutoCreate>>> {
    return this.httpClient.get<BaseDto<Array<EmployeeIdAutoCreate>>> (KubotaUserApi.employeeIdSearch, {
      params: new HttpParams().set('employeeId', employeeId).set('forCreate',forCreate)
    })
  } 


}
