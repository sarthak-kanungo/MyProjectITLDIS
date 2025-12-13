import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { UtilsService } from '../../../../../utils/utils.service';
import { Observable } from 'rxjs';
// import { autoEmployeeCode, autoEmployeeName, searchEmpMaster } from 'SearchEmployeeModule';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { autoEmployeeCode, autoEmployeeName, SearchEmpMaster } from './search-itldis-employee';

@Injectable({
  providedIn: 'root'
})
export class SearchitldisEmployeeService {
private readonly searchEmployeeCodeUrl=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.employeeCodeAuto}`
private readonly searchEmployeeNameUrl=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.employeeNameAuto}`
private readonly searchEmpMasterUrl=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.searchEmployee}`
private readonly changeActiveStatusUrl=`${environment.baseUrl}${urlService.api}${urlService.itldisemployee}${urlService.changeActiveStatus}`

  constructor(private httpClient:HttpClient,
    private utilsService: UtilsService) { }

    
  searchEmployeeCode(employeeCode: string):Observable<BaseDto<Array<autoEmployeeCode>>> {
    return this.httpClient.get<BaseDto<Array<autoEmployeeCode>>>(this.searchEmployeeCodeUrl, {
      params: new HttpParams().set('employeeCode', employeeCode)
    })
  }

  searchEmployeeName(employeeName: string):Observable<BaseDto<Array<autoEmployeeName>>> {
    return this.httpClient.get<BaseDto<Array<autoEmployeeName>>>(this.searchEmployeeNameUrl, {
      params: new HttpParams().set('employeeName', employeeName)
    })
  }
  
  searchEmpMaster(searchValue: SearchEmpMaster) {
    searchValue = this.utilsService.removeEmptyKey<SearchEmpMaster>(searchValue);
    return this.httpClient.post<BaseDto<any>>(this.searchEmpMasterUrl, searchValue)
  }

  changeActiveStatus(id) {
    return this.httpClient.get(this.changeActiveStatusUrl,  {
      params: new HttpParams().set('id', id)
    });
  }
}
