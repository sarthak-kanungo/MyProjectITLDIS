import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { SearchStockAdjustment } from '../../domain/stock-adjustment.domain';
import { StockAdjustmentApi } from '../../url-utils/stock-adjustment-urls';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';


@Injectable()
export class SearchPageWebService {
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private readonly orgLevelByHODeptUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelByHODeptUrl}`
  private readonly orgLevelHierForParentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelHierForParentUrl}`
    
      
  constructor(
    public httpClient: HttpClient
  ) { }
  searchStock(formData: SearchStockAdjustment) {
    return this.httpClient.post(StockAdjustmentApi.searchStockAdjDetails, formData)
  }
   getDealerCodeList( dealerCode: string ) {
      return this.httpClient.get( `${this.dealerCodeUrl}`, {
          params: new HttpParams().set( 'dealerCode', dealerCode )
      } )
  }
  getOrgLevelByHODept( deptCode: string ) {
    return this.httpClient.get( `${this.orgLevelByHODeptUrl}`, {
        params: new HttpParams().set( 'deptCode', deptCode )
    } )
}

getOrgLevelHierForParent( levelId: string, hierId: string ) {
    return this.httpClient.get( `${this.orgLevelHierForParentUrl}`, {
        params: new HttpParams().set( 'levelId', levelId )
            .set( 'hierId', hierId )
    } )
}
 getLookupByCode(code){
    return this.httpClient.get(StockAdjustmentApi.lookupUrl,{
      params: new HttpParams().set('code', code)
    })
  }

  searchAdjustmentNoAuto(val:string){
      return this.httpClient.get(StockAdjustmentApi.adjustmentNoAuto, {
          params : new HttpParams().set("searchVal",val)
      }).pipe(map(dto => dto['result']))
  }
  
  downloadExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(StockAdjustmentApi.downloadReportExcelUrl, filter,
      {observe: 'response', responseType: 'blob' as 'json' }
    )
}
}
