import { Injectable } from '@angular/core';
import { HttpClient, HttpParams,HttpResponse } from '@angular/common/http';
import { SparePoApi } from '../../url-utils/spares-purchse-order-urls';
import { DateService } from '../../../../../root-service/date.service';
import { map } from 'rxjs/operators';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { Observable } from 'rxjs';
@Injectable()
export class PurchaseOrderSearchWebService {
  
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private readonly orgLevelByHODeptUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelByHODeptUrl}`
  private readonly orgLevelHierForParentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelHierForParentUrl}`
    
      
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

      
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
     
  public searchByPONumber(searchKey: string) {
    console.log("searchByPONumber_service--->", searchKey)
    return this.httpClient.get(SparePoApi.getPurchaseOrderNumberAutoComplete, {
      params: new HttpParams().set('poNumber', searchKey)
    }).pipe(map(res => res['result']))
  }
  public searchByPartNumber(searchKey: string) {
      return this.httpClient.get(SparePoApi.apiController+'/getItemNumberSearchAutoComplete', {
        params: new HttpParams().set('itemNumber', searchKey)
      }).pipe(map(res => res['result']))
  }
  public searchSparesPO(searchObj: object) {
    this.removeNullFromObjectAndFormatDate(searchObj);
    return this.httpClient.post(SparePoApi.searchPurchaseOrder, searchObj);
  }
  public getSparesPOCount(searchObj: object) {
    this.removeNullFromObjectAndFormatDate(searchObj);
    return this.httpClient.post(SparePoApi.searchPurchaseOrderCount, searchObj);
  }
  public getSparePoTypes() {
    return this.httpClient.get(SparePoApi.getSparePoType);
  }
  public getSparePoStatus() {
    return this.httpClient.get(SparePoApi.getPurchaseOrderStatusDropdown);
  }
  public getAllPoBranch() {
    return this.httpClient.get(SparePoApi.dbEntityController);
  }
  public getDealerships() {
    return this.httpClient.get(SparePoApi.dbEntityController);
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "")) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }
  downloadExcelReport(filter, reportType): Observable<HttpResponse<Blob>>{
      if(reportType=='ExportExcel')
        return this.httpClient.post<Blob>(SparePoApi.downloadSparePOReportExcelUrl, filter,
          {observe: 'response', responseType: 'blob' as 'json' }
        )
      else
        return this.httpClient.post<Blob>(SparePoApi.downloadSparePurchaseDetailReport, filter,
          {observe: 'response', responseType: 'blob' as 'json' }
        )
  }
 
}
