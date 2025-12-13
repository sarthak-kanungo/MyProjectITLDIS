import { Injectable } from '@angular/core';
import { SubmitDto } from '../../../party-master/domain/party-master-domain';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DealerDepartmentMasterApi } from '../../url.utils/dealer-department-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class DealerDepartmentMasterPageService {

  constructor(
    private httpClient: HttpClient
  ) { }

  submitDealerDepartmentMaster(formData): Observable<any> {
    return this.httpClient.post<any>(DealerDepartmentMasterApi.submitData, formData)
  }

  updateDepartment(departmentCode,departmentName,id): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.update, {
      params: new HttpParams().set('departmentCode', departmentCode).set('departmentname', departmentName).set('id', id)
    }).pipe(map(res=>res.result))
  }
}
