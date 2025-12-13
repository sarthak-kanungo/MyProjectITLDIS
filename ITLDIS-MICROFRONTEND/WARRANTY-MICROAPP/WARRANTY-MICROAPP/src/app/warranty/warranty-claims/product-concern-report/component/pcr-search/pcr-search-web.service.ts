import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PcrApi } from '../../url-utils/product-concern-report.urls';
import { Observable, of } from 'rxjs';
import { SearchPCRAutoComplete, SearchByModel, PcrStatusModel } from '../../domain/product-concern-report.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { urlService } from 'src/app/webservice-config/baseurl';

@Injectable()
export class PcrSearchWebService {
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private static readonly branchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/getBranchesUnderUser`
  private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelByHODept`
  private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelHierForParent`
  private static readonly stateUrl = `${environment.baseUrl}${urlService.api}/warranty/pcr/getDealerStates`
  
  constructor(
    private httpClient: HttpClient
  ) { }

  autoCompletePcrNo(pcrNo: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(PcrApi.autoCompletePcrNo, {
      params: new HttpParams().set('pcrNo', pcrNo)
    }).pipe(map(res => res.result))
  }
  autoCompleteSearchChassisNo(chassisNo: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(PcrApi.autoCompleteSearchChassisNo, {
      params: new HttpParams().set('chassisNo', chassisNo)
    }).pipe(map(res => res.result))
  }
  searchAutoCompleteEngineNo(engineNo: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(PcrApi.searchAutoCompleteEngineNo, {
      params: new HttpParams().set('engineNo', engineNo)
    }).pipe(map(res => res.result))
  }
  searchAutoCompleteJobCode(jobCode: string): Observable<SearchPCRAutoComplete> {
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(PcrApi.searchAutoCompleteJobCode, {
      params: new HttpParams().set('jobCode', jobCode)
    }).pipe(map(res => res.result))
  }
  dropDownModel(): Observable<SearchByModel> {
    return this.httpClient.get<BaseDto<SearchByModel>>(PcrApi.dropDownModel)
    .pipe(map(res => res.result));
  }
  
  dropDownStatusModel(): Observable<SearchByModel> {
    return this.httpClient.get<BaseDto<SearchByModel>>(PcrApi.dropDownModel)
    .pipe(map(res => res.result));
  }

  dropDownStatus(): Observable<Array<PcrStatusModel>> {
    return this.httpClient.get<BaseDto<Array<PcrStatusModel>>>(PcrApi.dropDownStatus)
    .pipe(map(res => res.result));
  }

  getAllProduct() {
    return this.httpClient.get(PcrApi.getAllProduct)
  }

  getSeriesByProduct(product: string){
    return this.httpClient.get(PcrApi.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }

  getModelBySeries(series: string){
    return this.httpClient.get(PcrApi.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getSubModelByModel(model: string){
    return this.httpClient.get(PcrApi.getSubModelByModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariantBySubModel(subModel: string){
    return this.httpClient.get(PcrApi.getVariantBySubModel,
      {
        params: new HttpParams()
          .append('subModel', subModel)
      })
  }

  getByItemNo(itemNo: string){
    return this.httpClient.get(PcrApi.getItemNoAutoComplete,
      {
        params: new HttpParams()
          .append('partNumber', itemNo)
      }).pipe(map(res => res['result']))
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

  getBranchCodeList(dealerId){
    return this.httpClient.get(PcrSearchWebService.branchUrl, {
      params : new HttpParams().set('dealerId', dealerId)
    })
  }

  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(`${this.dealerCodeUrl}`, {
        params: new HttpParams().set('dealerCode', dealerCode)
      })
  }

  getStates(dealerId:number){
    return this.httpClient.get<BaseDto<Array<Object>>>(PcrSearchWebService.stateUrl,{
        params: new HttpParams()
            .append('dealerId', dealerId+"")
    })  
  }
}
