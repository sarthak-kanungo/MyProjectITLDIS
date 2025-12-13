import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class ReportService {


  private static readonly branchUrl = `${environment.baseUrl}${urlService.api}/service/report/getBranchesUnderUser`
  private static readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
  private static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
  private static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
  private static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
  private static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
  private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelByHODept`
  private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelHierForParent`   
  private static readonly currentStockSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/searchClosingStockReport`;
  private static readonly nonMovingPartStockSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/searchNonMovingPartsStockReport`;
  private static readonly currentStockexportUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/downloadClosingStockReport`;
  private static readonly nonMovingPartStockexportUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/downloadNonMovingPartsStockReport`;
  private static readonly stateUrl = `${environment.baseUrl}${urlService.api}/warranty/pcr/getDealerStates`
  private static readonly backOrderPartsSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/searchBackOrderPartsReport`;
  private static readonly backOrderPartsExportUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/downloadBackOrderPartsReport`;
  private static readonly itemMovementReportSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/searchitemMovementReport`;
  private static readonly itemMovementReportExportUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/downloaditemMovementReport`;

  private static readonly inventoryMovementReportSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/searchInventoryMovementReport`;
  private static readonly inventoryMovementReportExportUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}/reports/downloadInventoryMovementReport`;


  constructor(private httpClient: HttpClient) { }

  getAllProduct() {
    return this.httpClient.get(ReportService.getAllProduct)
  }

  getSeriesByProduct(product: string){
    return this.httpClient.get(ReportService.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }

  getModelBySeries(series: string){
    return this.httpClient.get(ReportService.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getSubModelByModel(model: string){
    return this.httpClient.get(ReportService.getSubModelByModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariantBySubModel(subModel: string){
    return this.httpClient.get(ReportService.getVariantBySubModel,
      {
        params: new HttpParams()
          .append('subModel', subModel)
      })
  }

  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(ReportService.dealerCodeUrl, {
        params: new HttpParams().set('dealerCode', dealerCode)
    })
  }
  getBranchCodeList(dealerId){
    return this.httpClient.get(ReportService.branchUrl,{
      params : new HttpParams().set('dealerId', dealerId)
    })
  }

  getStockOnDateReport(searchObj){
    return this.httpClient.post(ReportService.currentStockSearchUrl, searchObj);
  }
  
  getBackOrderPartsReport(searchObj){
    return this.httpClient.post(ReportService.backOrderPartsSearchUrl, searchObj);
  }

  exportBackOrderPartsReport(searchObj){
    return this.httpClient.post(ReportService.backOrderPartsExportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }

  getItemMovementReport(searchObj){
    return this.httpClient.post(ReportService.itemMovementReportSearchUrl, searchObj);
  }

  exportItemMovementReport(searchObj){
    return this.httpClient.post(ReportService.itemMovementReportExportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }
  
  getInventoryMovementReport(searchObj){
    return this.httpClient.post(ReportService.inventoryMovementReportSearchUrl, searchObj);
  }

  exportInventoryMovementReport(searchObj){
    return this.httpClient.post<Blob>(ReportService.inventoryMovementReportExportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }
  
  getNonMovingPartStockReport(searchObj){
    return this.httpClient.post(ReportService.nonMovingPartStockSearchUrl, searchObj);
  }

  exportStockOnDateReport(searchObj){
    return this.httpClient.post<Blob>(ReportService.currentStockexportUrl, searchObj,
    {observe: 'response', responseType: 'blob' as 'json' });
  }

  exportNonMovingPartStockReport(searchObj){
    return this.httpClient.post<Blob>(ReportService.nonMovingPartStockexportUrl, searchObj,
    {observe: 'response', responseType: 'blob' as 'json' });
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

  
  getStates(dealerId:number){
    return this.httpClient.get<BaseDto<Array<Object>>>(ReportService.stateUrl,{
        params: new HttpParams()
            .append('dealerId', dealerId+"")
    })  
  }
}
