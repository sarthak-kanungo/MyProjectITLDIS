import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { employeeBankApproval } from '../../../bank-detail-approval/bank-detail-approval/url-utils/bank-approval';
import { DealerDepartmentMasterApi } from '../../../dealer-department-master/url.utils/dealer-department-master-urls';
import { DealerDesignationMasterApi } from '../../../dealer-designation-master/url.utils/dealer-designation-master-urls';
import { DealerEmployeeApi } from '../../../dealer-employee-master/url-util/dealer-employee-urls';
import { submitData } from '../model/employee';
import { bankDetailApi } from '../url-util/bank-detail-url';

@Injectable({
  providedIn: 'root'
})
export class EmployeServiceService {
  
  constructor(private http:HttpClient) { }
  getEmployeeCodeAuto(mobileNo:string, employeeCode: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DealerEmployeeApi.employeeCodeForSearchPage, {
      params: new HttpParams().append('mobilenumber',mobileNo).set('employeeCode', employeeCode)
    }).pipe(map(res=>res.result))
  }
  getCodeMobile(mobileNo:string, employeeCode: string): Observable<any> {
    return this.http.get<BaseDto<any>>(bankDetailApi.getCodeMobile, {
      params: new HttpParams().append('dealerEmpMob',mobileNo).set('dealerEmpCode', employeeCode)
    }).pipe(map(res=>res.result))
  }
  getEmployeeNameAuto(employeeName: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DealerEmployeeApi.employeeNameAuto, {
      params: new HttpParams().append('firstName', employeeName)
    }).pipe(map(res=>res.result))
  }
  searchDepartmentCode(deptCod: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DealerDepartmentMasterApi.departmentCodeAndName, {
      params: new HttpParams().append('deptCodeAndName', deptCod)
    }).pipe(map(res=>res.result))
  }
  searchDesignation(designationAuto: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DealerDesignationMasterApi.searchDesignation, {
      params: new HttpParams().append('designation', designationAuto)
    }).pipe(map(res=>res.result))
  }
  updateEmployeeBankDetails(formData : submitData) : Observable<submitData>{
    return this.http.post<submitData> (bankDetailApi.updateEmployeeBankDetails,formData)
  } 
  
  searchEmployeeBankDetail(searchFilter:any){
    return this.http.post(bankDetailApi.searchEmployeeBankDetail, searchFilter);
  }
  searchEmployeeBankApproval(searchFilter:any){
    return this.http.post(employeeBankApproval.searchEmployeeBankApproval, searchFilter);
  }
  // employeeBankGroupApproval( remark:string, delearEmployeeId:string, approvalStatus:string){
  //   let data = {  remark:remark, approvalStatus:approvalStatus};
  //   return this.http.post(employeeBankApproval.employeeBankGroupApproval, data);
  // }
  employeeBankGroupApproval(jsonObj:any) : Observable<any> {
    return this.http.post<BaseDto<any>>(employeeBankApproval.employeeBankGroupApproval, jsonObj)
}
}
