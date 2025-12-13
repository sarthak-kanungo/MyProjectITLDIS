import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { SearchWarrantyPcr, SearchWarrantyList } from '../../domain/product-concern-report.domain';
import { PcrApi } from '../../url-utils/product-concern-report.urls';
import { Observable } from 'rxjs/Observable';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PcrSearchPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }
  

  public specialApprovalRequired(id, remark){
    return this.httpClient.get(PcrApi.pcrSpecialApproval, {
      params : new HttpParams().set('pcrId', id).set('remark', remark)
    })
  }
  public searchWarrantyPcr(warrantyPcr: SearchWarrantyPcr): Observable<BaseDto<Array<SearchWarrantyList>>> {
    return this.httpClient.post<BaseDto<Array<SearchWarrantyList>>>(PcrApi.searchWarrantyPcr, warrantyPcr)
    
  }

  downloadPcrExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(PcrApi.downloadPcrExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}


