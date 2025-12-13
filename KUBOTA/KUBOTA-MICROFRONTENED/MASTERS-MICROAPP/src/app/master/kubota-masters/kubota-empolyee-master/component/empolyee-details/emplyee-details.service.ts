import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { UtilsService } from '../../../../../utils/utils.service';
import { AutoDesiganationLevel, SaveEmployee, DropDesiganationLevel, DropDoenDepartments, ReportingEmployeeDetail, EmployeeDetail, ReportingEmployee, DirectRepotees } from '../empolyee-details/employee-details';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class EmplyeeDetailsService {
  private readonly searchDesiganationLevelUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotaemployee}${urlService.designationLevelAuto}`
  private readonly submitEmployeeUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotaemployee}${urlService.add}`
  private readonly searchDesiganationUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotaemployee}${urlService.designationLevel}`
  private readonly getEmployeeByEmployeeCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotaemployee}${urlService.getEmployeeByEmployeeCode}`
  private readonly updateEmployeeUrl = `${environment.baseUrl}${urlService.api}${urlService.kubotaemployee}${urlService.update}`

  directRepotees : DirectRepotees[] = [];
  reportingEmployee= {} as ReportingEmployee;
  reportingEmployeeDetail = {} as ReportingEmployeeDetail;
  reportingEmployeeForm: FormGroup;
  constructor(private httpClient: HttpClient,  
    private utilsService: UtilsService) { }

  searchDesiganationLevel(desiganationLevel, departmentid): Observable<BaseDto<Array<AutoDesiganationLevel>>> {
    return this.httpClient.get<BaseDto<Array<AutoDesiganationLevel>>>(this.searchDesiganationLevelUrl, {
      params: new HttpParams().set('desiganationLevel', desiganationLevel).set('departmentid', departmentid)
    })
  }
  submitEmployee(formData: SaveEmployee) {
    return this.httpClient.post(this.submitEmployeeUrl, formData)
  }
  updateEmployee(formData: SaveEmployee) {
    return this.httpClient.post(this.updateEmployeeUrl, formData)
  }
  getDesiganationLevel(): Observable<BaseDto<Array<DropDesiganationLevel>>> {
    return this.httpClient.get<BaseDto<Array<DropDesiganationLevel>>>(this.searchDesiganationUrl)
  }
  // private readonly getKaiBranchDropUrl = `${environment.baseUrl}${urlService.api}${urlService.depot}${urlService.getActiveKaiBranch}`
  // // GET /api/depot/getActiveKaiBranch
  // getKaiBranchDrop(): Observable<BaseDto<Array<DropDoenKaibranch>>> {
  //   return this.httpClient.get< BaseDto<Array<DropDoenKaibranch>>>(this.getKaiBranchDropUrl)
  // }
  private readonly getDeprtmentDropUrl= `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.departmentNameDropdown}`
  // GET /api/department/departmentNameDropdown
  getDeprtmentDrop(): Observable<BaseDto<Array<DropDoenDepartments>>> {
    return this.httpClient.get< BaseDto<Array<DropDoenDepartments>>>(this.getDeprtmentDropUrl)
  }

  getKubotaEmployeeById(employeeCode): Observable<BaseDto<Array<EmployeeDetail>>>
  {
    return this.httpClient.get<BaseDto<Array<EmployeeDetail>>>(this.getEmployeeByEmployeeCodeUrl, {
      params: new HttpParams().set('employeeCode', employeeCode)
    })
  }
}
