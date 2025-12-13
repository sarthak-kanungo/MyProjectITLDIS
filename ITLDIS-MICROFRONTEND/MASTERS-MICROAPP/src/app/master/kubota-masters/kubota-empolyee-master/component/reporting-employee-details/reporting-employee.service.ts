import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { EmployeeNameByEmployeeCode } from '../empolyee-details/employee-details';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable({
  providedIn: 'root'
})
export class ReportingEmployeeService {
 private readonly getEmployeeNameByEmployeeCodeUrl=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.getEmployeeNameByEmployeeCode}`
  // GET /api/itldisemployee/getEmployeeNameByEmployeeCode
  constructor(private httpClient:HttpClient) { }
  getEmployeeNameByEmployeeCode(employeeCode:string):Observable<BaseDto<Array<EmployeeNameByEmployeeCode>>>{
    return this.httpClient.get<BaseDto<Array<EmployeeNameByEmployeeCode>>>(this.getEmployeeNameByEmployeeCodeUrl, {
      params: new HttpParams().set('employeeCode', employeeCode)
    })
  }
 
}
