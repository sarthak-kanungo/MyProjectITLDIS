import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubmitDto, SearchDto, DealerMasterDropdown } from '../../domain/dealer-employee-domain';
import { DealerEmployeeApi } from '../../url-util/dealer-employee-urls';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class EmployeeMasterCreatePageService {

  constructor(
    private httpClient: HttpClient
  ) { }

  employeeCodeAuto(dealerId:any,employeeCode: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.employeeCodeAuto, {
      params: new HttpParams().append('dealerId', dealerId).set('employeeCode',employeeCode)
    }).pipe(map(res=>res.result))
  }
  employeeNameAuto(firstName: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.employeeNameAuto, {
      params: new HttpParams().append('firstName', firstName)
    }).pipe(map(res=>res.result))
  }
  statusDropdown(): Observable<DealerMasterDropdown> {
    return this.httpClient.get<BaseDto<DealerMasterDropdown>>(DealerEmployeeApi.statusDropdown)
    .pipe(map(res => res.result));
  }
  getEmployeeName(employeeCode: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.getEmployeeName, {
      params: new HttpParams().append('employeeCode', employeeCode)
    }).pipe(map(res=>res.result))
  }


  postSubmitDto(formData : SubmitDto) : Observable<SubmitDto>{
    return this.httpClient.post<SubmitDto> (DealerEmployeeApi.saveDealer,formData)
  } 

  updateEmployeeMaster(formData : SubmitDto) : Observable<SubmitDto>{
    return this.httpClient.post<SubmitDto> (DealerEmployeeApi.updateEmployeeMaster,formData)
  } 

  dealerDetailsAuto(dealerDetalis: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.dealerDetails, {
      params: new HttpParams().append('dealerDetalis', dealerDetalis).append('isFor','DLR_EMP_MST')
    }).pipe(map(res=>res.result))
  }

  reportingEmployeeAuto(dealerCode:any,searchText: any,empCode:string ): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.getReportingEmployee, {
     // params: new HttpParams().append('reportingEmployee', reportingEmployee),
      params: new HttpParams().set('dealerCode',dealerCode).set('searchText', searchText).set('empCode', empCode)
    }).pipe(map(res=>res.result))
  }

  viewEmployeeMaster(dealerEmpId: string): Observable<SubmitDto> {
    return this.httpClient.get<BaseDto<SubmitDto>>(DealerEmployeeApi.viewEmployeeMaster, {
      params: new HttpParams().set('dealerEmpId', dealerEmpId)
    }).pipe(map(dto => dto.result))
  }

  validateMobileNo(mobileno:string, id:number){
    if(id){}else{
      id=0;
    }
    return this.httpClient.get(DealerEmployeeApi.validateMobileNo, {
      params: new HttpParams().set('mobileno', mobileno).set('id',id+"")
    })
  }

}
