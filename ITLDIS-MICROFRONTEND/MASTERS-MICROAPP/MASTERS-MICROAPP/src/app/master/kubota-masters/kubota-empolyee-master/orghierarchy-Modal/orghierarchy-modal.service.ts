import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class OrghierarchyModalService {

  hoEmployeeId:string

  private readonly getEmpMasterdepartment=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.empMasterdepartmentForOrgHier}`
  private readonly levelForOrgHier=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.levelForOrgHier}`
  private readonly levelDetailsForOrgHier=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.levelDetailsForOrgHier}`
  private readonly assigneDept=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.assigneDeptInOrgHier}`
  private readonly getOrgHierDetailsForHoEmp=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.orgHierDetailsForHoEmp}`
  private readonly updateHoOrgHierStatus=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.updateHoOrgHierStatus}`
  constructor(private httpClient:HttpClient) { }

  orgHierDepartmentDropdown(): Observable<any> {
    return this.httpClient.get<BaseDto<any>> (this.getEmpMasterdepartment)
    .pipe(map(res => res.result));
  }

  getLevelForOrgHier(deptCode: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(this.levelForOrgHier, {
      params: new HttpParams().append('deptCode', deptCode)
    }).pipe(map(res=>res.result))
  }

  getLevelDetailsForOrgHier(levelId,orgHierId): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(this.levelDetailsForOrgHier, {
      params: new HttpParams().set('levelId', levelId).set('orgHierId', orgHierId)
    }).pipe(map(res=>res.result))
  }

  assigneDeptOrgHier(formData) : Observable<any>{
    return this.httpClient.post<any> (this.assigneDept,formData)
  } 

  getOrgHierDetailsForEmployee(hoEmpCode: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(this.getOrgHierDetailsForHoEmp, {
      params: new HttpParams().append('hoEmpCode', hoEmpCode)
    }).pipe(map(res=>res.result))
  }
  updateHoOrgHierEmployeeStatus(formData): Observable<any>{
    return this.httpClient.put<any>(this.updateHoOrgHierStatus,formData)
  }
}
