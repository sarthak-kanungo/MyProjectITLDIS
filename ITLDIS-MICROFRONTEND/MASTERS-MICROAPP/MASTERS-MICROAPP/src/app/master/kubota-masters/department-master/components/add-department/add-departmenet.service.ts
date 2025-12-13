import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { SubmitDepartment } from 'SearchDepartment';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddDepartmenetService {
  private readonly submitDepartmenetUrl = `${environment.baseUrl}${urlService.api}${urlService.department}`
  private readonly tocheckKaiDepartmentCode = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.tocheckKaiDepartmentCode}`
  private readonly tocheckKaiDepartmentName = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.tocheckKaiDepartmentName}`
  // POST /api/department
  constructor(private httpClient: HttpClient) { }
  submitDepartmenet(formData:SubmitDepartment) {
    return this.httpClient.post(this.submitDepartmenetUrl, formData)
  }

  checkKaiDepartmentCode(departmentCode:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(this.tocheckKaiDepartmentCode,{
        params: new HttpParams().append('departmentCode',departmentCode)
      }).pipe(map(res=>res.result))
  }

  checkKaiDepartmentName(departmentName:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(this.tocheckKaiDepartmentName,{
        params: new HttpParams().append('departmentName',departmentName)
      }).pipe(map(res=>res.result))
  }
}
