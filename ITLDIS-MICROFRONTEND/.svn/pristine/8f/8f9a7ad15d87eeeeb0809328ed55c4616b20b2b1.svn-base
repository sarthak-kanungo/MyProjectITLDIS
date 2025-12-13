import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SubmitDto } from '../../../party-master/domain/party-master-domain';
import { Observable } from 'rxjs';
import { DealerDesignationMasterApi } from '../../url.utils/dealer-designation-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class DealerDesignationMasterPageService {

  viewDataId:any

  constructor(
    private httpClient: HttpClient
  ) { }

  submitDealerDesignationMaster(formData): Observable<any> {
    return this.httpClient.post<any>(DealerDesignationMasterApi.submitData, formData)
  }

  updateDesignation(departmentId,designationcode,designation,id): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerDesignationMasterApi.update, {
      params: new HttpParams().set('departmentId', departmentId).set('designationcode', designationcode).set('designation', designation).set('id', id)
    }).pipe(map(res=>res.result))
  }
}
