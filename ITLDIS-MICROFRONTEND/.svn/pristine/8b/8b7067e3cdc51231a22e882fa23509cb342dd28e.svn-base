import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EmployeeCode, TitleDropdown, DivisionDropdown, DepartmentDropdown, DesidnationDropdown, LicenceTypeDropdown, ReportingEmployeeCodeAuto,  StatusDropdown, BloodsDropdown } from '../../domain/dealer-employee-domain';
import { DealerEmployeeApi } from '../../url-util/dealer-employee-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class EmployeeMasterService {

  // private readonly employeeCodeAutocompleteUrl=`${environment.baseUrl}${urlService.api}${urlService.dealerEmployeeMaster}${urlService.employeeCodeAuto}`

  constructor(
    private httpClient : HttpClient

  ) { }

 /*  getemployeeCodeAutocomplete(): Observable<BaseDto<EmployeeCode>> {
    return this.httpClient.get<BaseDto<EmployeeCode>> (DealerEmployeeApi.employeeCode)
  }

  getTitleDropdown(): Observable<TitleDropdown> {
    return this.httpClient.get<TitleDropdown> (DealerEmployeeApi.titleDropdown)
  }

  getStatusDropdown(): Observable<StatusDropdown> {
    return this.httpClient.get<StatusDropdown> (DealerEmployeeApi.titleDropdown)
  }

  getDivisionDropdown(): Observable<DivisionDropdown> {
    return this.httpClient.get<DivisionDropdown> (DealerEmployeeApi.divisionDropdown)
  }
  getDepartmentDropdown(): Observable<DepartmentDropdown> {
    return this.httpClient.get<DepartmentDropdown> (DealerEmployeeApi.departmentDropdown)
  }
  getDesidnationDropdown(): Observable<DesidnationDropdown> {
    return this.httpClient.get<DesidnationDropdown> (DealerEmployeeApi.desidnationDropdown)
  }
  getLicenceTypeDropdown(): Observable<LicenceTypeDropdown> {
    return this.httpClient.get<LicenceTypeDropdown> (DealerEmployeeApi.licenceTypeDropdown)
  }
  getReportingEmployeeCodeAuto(): Observable<ReportingEmployeeCodeAuto> {
    return this.httpClient.get<ReportingEmployeeCodeAuto> (DealerEmployeeApi.reportingEmployeeCodeAuto)
  } */
  
  getEmployeeDetailsById(employeeId: string):Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.getEmployeeDetailsById, {
      params: new HttpParams().append('employeeId', employeeId)
    }).pipe(map(res=>res.result))
  }

  checkDuplicateEmployeeCode(employeeCode: string):Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.checkDuplicateEmpCode, {
      params: new HttpParams().append('employeeCode', employeeCode)
    }).pipe(map(res=>res.result))
  }

  // getDepartmentDropdown(): Observable<DepartmentDropdown> {
  //   return this.httpClient.get<BaseDto<DepartmentDropdown>> (DealerEmployeeApi.getEmployeeMasterdepartment)
  // }

  departmentDropdown(): Observable<DepartmentDropdown> {
    return this.httpClient.get<BaseDto<DepartmentDropdown>> (DealerEmployeeApi.getEmployeeMasterdepartment)
    .pipe(map(res => res.result));
  }
  
  designationsDropdown(): Observable<DesidnationDropdown> {
    return this.httpClient.get<BaseDto<DesidnationDropdown>> (DealerEmployeeApi.getEmployeeMasterDesignation)
    .pipe(map(res => res.result));
  }

  licencesDropdown(): Observable<LicenceTypeDropdown> {
    return this.httpClient.get<BaseDto<LicenceTypeDropdown>> (DealerEmployeeApi.getEmployeeMasterlicences)
    .pipe(map(res => res.result));
  }
  
}
