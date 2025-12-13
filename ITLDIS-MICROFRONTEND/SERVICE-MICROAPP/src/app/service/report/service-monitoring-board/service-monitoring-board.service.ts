import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs-compat/operator/map';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceMonitoringBoardService {
  
  private static readonly branchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/getBranchesUnderUser`
  private static readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
  private static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
  private static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
  private static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
  private static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
  private static readonly autoCompleteSearchChassisNo = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompleteChassisNoInJobCard`;
      
  private static readonly searchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/searchSMB`;
  private static readonly exportUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/exportSMB`;
 
  constructor(private httpClient: HttpClient) { }

  autoCompleteSearchChassisNo(chassisNo: string){
    return this.httpClient.get(ServiceMonitoringBoardService.autoCompleteSearchChassisNo, {
      params: new HttpParams().set('chassisNo', chassisNo).set('preSalesFlag','false').set('isFor','SMB')
    });
  }

  getAllProduct() {
    return this.httpClient.get(ServiceMonitoringBoardService.getAllProduct)
  }

  getSeriesByProduct(product: string){
    return this.httpClient.get(ServiceMonitoringBoardService.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }

  getModelBySeries(series: string){
    return this.httpClient.get(ServiceMonitoringBoardService.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getSubModelByModel(model: string){
    return this.httpClient.get(ServiceMonitoringBoardService.getSubModelByModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariantBySubModel(subModel: string){
    return this.httpClient.get(ServiceMonitoringBoardService.getVariantBySubModel,
      {
        params: new HttpParams()
          .append('subModel', subModel)
      })
  }

  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(ServiceMonitoringBoardService.dealerCodeUrl, {
        params: new HttpParams().set('dealerCode', dealerCode)
    })
  }
  getBranchCodeList(dealerId){
    return this.httpClient.get(ServiceMonitoringBoardService.branchUrl,{
      params : new HttpParams().set('dealerId', dealerId)
    })
  }

  getSMBSearchRecords(searchObj){
    return this.httpClient.post(ServiceMonitoringBoardService.searchUrl, searchObj);
  }
  
  exportSMBSearchRecords(searchObj){
    return this.httpClient.post<Blob>(ServiceMonitoringBoardService.exportUrl, searchObj,
    {observe: 'response', responseType: 'blob' as 'json' });
  }
}
