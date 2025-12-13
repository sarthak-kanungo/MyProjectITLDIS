import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { AutoCompSubModel, AutoCompVariant, ModelBySeries, Product, SearchAutocomplete, SeriesByProduct } from '../../domain/job-card-domain';
import { JobCardUrl } from '../../url-util/job-card-url';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';


@Injectable()
export class JobCardSearchWebService {
  private static readonly branchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/getBranchesUnderUser`
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  private readonly orgLevelByHODeptUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelByHODeptUrl}`
  private readonly orgLevelHierForParentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelHierForParentUrl}`
  private readonly jobcardStatusList = `${environment.baseUrl}${urlService.api}${urlService.service}${urlService.jobcard}/jobcardStatusList`
  constructor(
    private httpClient: HttpClient
  ) { }

  getjobcardStatusList(){
    return this.httpClient.get(this.jobcardStatusList)
  }
  searchByChassisNumber(chassisNo: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.chassisNumberUrl, {
      params: new HttpParams().set('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

  searchByJobCardNumber(jobCode: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.jobCardNumberUrl, {
      params: new HttpParams().set('jobCode', jobCode)
    }).pipe(map(dto => dto.result))
  }

  searchByEngineNumber(engineNo: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.engineNumberUrl, {
      params: new HttpParams().set('engineNo', engineNo)
    }).pipe(map(dto => dto.result))
  }

  searchByCsbNumber(searchString: string): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.autoCompleteSearchcsbNumber, {
      params: new HttpParams().set('searchString', searchString)
    }).pipe(map(dto => dto.result))
  }

  searchByBookingNumber(bookingNo: string, jobCardFlag): Observable<Array<SearchAutocomplete>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocomplete>>>(JobCardUrl.bookingNumberUrl, {
      params: new HttpParams().set('bookingNo', bookingNo).set('jobCardFlag', jobCardFlag)
    }).pipe(map(dto => dto.result))
  }

  getOrgLevelByHODept(deptCode:string){
    return this.httpClient.get(`${this.orgLevelByHODeptUrl}`, {
        params: new HttpParams().set('deptCode', deptCode)
    })
}

getOrgLevelHierForParent(levelId:string, hierId:string){
    return this.httpClient.get(`${this.orgLevelHierForParentUrl}`, {
        params: new HttpParams().set('levelId', levelId)
            .set('hierId', hierId)
    })
  }

  
  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(`${this.dealerCodeUrl}`, {
        params: new HttpParams().set('dealerCode', dealerCode)
      })
  }

  getAllProduct(): Observable<BaseDto<Array<Product>>> {
    return this.httpClient.get<BaseDto<Array<Product>>>(JobCardUrl.getAllProduct)
  }
  getSeriesByProduct(product: string): Observable<BaseDto<Array<SeriesByProduct>>> {
    return this.httpClient.get<BaseDto<Array<SeriesByProduct>>>(JobCardUrl.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }
  getModelBySeries(series: string): Observable<BaseDto<Array<ModelBySeries>>> {
    return this.httpClient.get<BaseDto<Array<ModelBySeries>>>(JobCardUrl.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }
  
  getSubModel(model: string): Observable<BaseDto<Array<AutoCompSubModel>>> {
    return this.httpClient.get<BaseDto<Array<AutoCompSubModel>>>(JobCardUrl.getSubModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariant(model: string, subModel: string): Observable<Array<AutoCompVariant>> {
    return this.httpClient.get<BaseDto<Array<AutoCompVariant>>>(JobCardUrl.getVariant,
      {
        params: new HttpParams()
          .append('model', model)
          .append('subModel', subModel)
      }).pipe(map(dto => dto.result))
  }

  getBranchCodeList(dealerId){
    return this.httpClient.get(JobCardSearchWebService.branchUrl,{
      params : new HttpParams().set('dealerId', dealerId)
    })
  }


}
