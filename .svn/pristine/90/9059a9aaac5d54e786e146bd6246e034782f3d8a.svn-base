import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EmployeeCodeAuto, MobileNoAuto } from '../../domain/dealer-employee-domain';
import { DealerEmployeeApi, } from '../../url-util/dealer-employee-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';


@Injectable()
export class SearchDealerEmployeeService {

  constructor(
    private httpClient : HttpClient
  ) { }

  // getEmployeeCodeAuto(): Observable<EmployeeCodeAuto> {
  //   return this.httpClient.get<EmployeeCodeAuto> (DealerEmployeeApi.employeeCodeAuto)
  // }

  /* getMobileNoAuto(): Observable<MobileNoAuto> {
    return this.httpClient.get<MobileNoAuto> (DealerEmployeeApi.mobileNoAuto)
  } */

  getEmployeeCodeAuto(mobileNo:string, employeeCode: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.employeeCodeForSearchPage, {
      params: new HttpParams().append('mobilenumber',mobileNo).set('employeeCode', employeeCode)
    }).pipe(map(res=>res.result))
  }

  getEmployeeNameAuto(employeeName: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.employeeNameAuto, {
      params: new HttpParams().append('firstName', employeeName)
    }).pipe(map(res=>res.result))
  }
  getStates(countryId:any, stateName:string):Observable<any>{
    return this.httpClient.get<any>(DealerEmployeeApi.getStates,{
      params: new HttpParams().set('countryId',countryId).set('stateName',stateName)
    })                  
  }
}
