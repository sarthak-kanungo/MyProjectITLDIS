import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DepartmentNameDropdown } from '../../domain/dealer-department-master-domain';
import { DealerDepartmentMasterApi } from '../../url.utils/dealer-department-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class DealerDepartmentMasterService {

  constructor(
    private httpClient: HttpClient
  ) { }


  // searchDepartmentCode(dealervalue: string): Observable<any> {
  //   return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.departmentCode, {
  //     params: new HttpParams().append('dealervalue', dealervalue)
  //   }).pipe(map(res=>res.result))
  // }
  
  // getDepartmentNameCreateDropdown(): Observable<BaseDto<DepartmentNameDropdown>> {
  //   return this.httpClient.get<BaseDto<DepartmentNameDropdown>>(DealerDepartmentMasterApi.departmentNameDropdown)
  // }

  checkDepartmentCode(departmentCode:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.checkDepartmentCode,{
        params: new HttpParams().append('departmentCode',departmentCode)
      }).pipe(map(res=>res.result))
  }

  checkDepartmentName(departmentName:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.checkDepartmentName,{
        params: new HttpParams().append('departmentName',departmentName)
      }).pipe(map(res=>res.result))
  }

  viewDepartment(departmentId:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.viewDepartment,{
        params: new HttpParams().append('departmentId',departmentId)
      }).pipe(map(res=>res.result))
  }
}
