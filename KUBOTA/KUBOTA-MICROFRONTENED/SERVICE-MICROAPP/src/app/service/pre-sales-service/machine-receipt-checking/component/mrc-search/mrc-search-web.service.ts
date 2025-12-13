import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { KaiInvoiceNumber, MrcNumberAuto, ChassisNumber } from '../../domain/machine-receipt-checking.domain';
import { BaseDto } from 'BaseDto';
import { HttpParams, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { DateService } from '../../../../../root-service/date.service';
import { environment } from 'src/environments/environment';
import { urlService } from 'src/app/webservice-config/baseurl';

@Injectable()
export class MrcSearchWebService {
  private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelByHODept`
  private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelHierForParent`

  constructor(private httpClient: HttpClient,
    private dateService: DateService, ) { }


  getKaiInvoiceNumber(invoiceNumber: string): Observable<Array<KaiInvoiceNumber>> {
    return this.httpClient.get<BaseDto<Array<KaiInvoiceNumber>>>(MrcUrl.searchKaiInvoiceAuto,
      {
        params: new HttpParams()
          .append('invoiceNumber', invoiceNumber)
      }).pipe(map(dto => dto.result))
  }

  getMrcNumber(searchString: string): Observable<Array<MrcNumberAuto>> {
    return this.httpClient.get<BaseDto<Array<MrcNumberAuto>>>(MrcUrl.searchMrcNumberAuto,
      {
        params: new HttpParams()
          .append('searchString', searchString)
      }).pipe(map(dto => dto.result))
  }

  getChassisNumber(chassisNumber: string): Observable<Array<ChassisNumber>> {
    return this.httpClient.get<BaseDto<Array<ChassisNumber>>>(MrcUrl.searchChassisNumberAuto,
      {
        params: new HttpParams()
          .append('chassisNumber', chassisNumber)
      }).pipe(map(dto => dto.result))
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getLevelByDepartment(departmentCode:any) {
    return this.httpClient.get(this.getLevelDropUrl, {
      params: new HttpParams().set('deptCode', departmentCode)
    })
  }
  
  getHierarchyByLevel(levelId:any,orgHierId){
    return this.httpClient.get(this.getHierDropUrl, {
      params: new HttpParams().set('levelId', levelId).set('hierId',orgHierId),
      
    })
  }
}
