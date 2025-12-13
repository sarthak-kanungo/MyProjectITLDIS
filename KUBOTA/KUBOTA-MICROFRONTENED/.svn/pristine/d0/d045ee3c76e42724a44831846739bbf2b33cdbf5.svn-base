import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DealerDesignationMasterApi } from '../../url.utils/dealer-designation-master-urls';
import { DeptDropdownForDesignation } from '../../domain/dealer-designation-master-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class DealerDesignationMasterService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getDepartment(): Observable<DeptDropdownForDesignation> {
    return this.httpClient.get<DeptDropdownForDesignation>(DealerDesignationMasterApi.searchDealerDepartment)
  }

  checkDesignationCode(designationCode:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDesignationMasterApi.checkDesignationCode,{
        params: new HttpParams().append('designationCode',designationCode)
      }).pipe(map(res=>res.result))
  }

  checkDesignationName(designationName:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDesignationMasterApi.checkDesignationName,{
        params: new HttpParams().append('designationName',designationName)
      }).pipe(map(res=>res.result))
  }

  viewDesignation(designationId:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(DealerDesignationMasterApi.viewDesignation,{
        params: new HttpParams().append('designationId',designationId)
      }).pipe(map(res=>res.result))
  }
}
