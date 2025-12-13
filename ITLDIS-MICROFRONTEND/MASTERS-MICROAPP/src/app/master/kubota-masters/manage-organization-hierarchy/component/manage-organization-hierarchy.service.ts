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
export class ManageOrganizationHierarchyService {

  hoEmployeeId:string

  private readonly getEmpMasterdepartment=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.empMasterdepartmentForOrgHier}`
  private readonly levelForOrgHier=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.levelForOrgHier}`
  private readonly levelDetailsForOrgHier=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.levelDetailsForOrgHier}`
  
  private readonly validateOrganizationCodeUrl=`${environment.baseUrl}${urlService.api}/master/kaicommonmaster/manageOrgHierarchy/validateCode`

  private readonly addUrl=`${environment.baseUrl}${urlService.api}/master/kaicommonmaster/manageOrgHierarchy/add`

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

  addHierarchy(saveObject){
    return this.httpClient.post(this.addUrl, saveObject);
  }

  validateOrganizationCode(code){
    return this.httpClient.get(this.validateOrganizationCodeUrl, {
      params: new HttpParams().set('code', code)
    })
  }
}
