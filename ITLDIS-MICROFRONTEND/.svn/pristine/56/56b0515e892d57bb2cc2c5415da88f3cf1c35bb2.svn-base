import { Injectable } from '@angular/core';
import { HttpClient, HttpParams,HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { SearchEnquiryFilter, SearchEnqiryList, EnquiryNoNameThesil,VillageTehsilDistrict, Product, SeriesByProduct, ModelBySeries, Variants, EnquiryStatus } from '../domains/search-enquiry-v2';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class SearchEnquiryV2WebService {

  constructor(
    private http : HttpClient,
  ) { }
  
  reopenEnquiry(enquiryNo:string){
      return this.http.get<BaseDto<Array<Object>>>(EnquiryApi.reopenEnquiryUrl, {
          params: new HttpParams().set('enquiryNo', enquiryNo)
      })
  }
  
  getStates(cityId:number){
    return this.http.get<BaseDto<Array<Object>>>(EnquiryApi.getStatesEnuiry,{
        params: new HttpParams()
            .append('cityId', cityId+"")
    })  
  }
  
  getEnquiryNumberNameMobileNoTehsil(search: string): Observable<Array<EnquiryNoNameThesil>> {
    return this.http.get<BaseDto<Array<EnquiryNoNameThesil>>>(EnquiryApi.getEnquiryNumberNameMobileNoTehsil,
      {
        params: new HttpParams()
          .append('search', search)
      }).pipe(map(dto => dto.result))
  }
  getVillageTehsilDistrict(value, stateId) : Observable<Array<VillageTehsilDistrict>>{
      return this.http.get<BaseDto<Array<VillageTehsilDistrict>>>(EnquiryApi.villageTehsilDistrictSearch,{
          params : new HttpParams().append("searchValue", value).append("stateId", stateId)
      }).pipe(map(dto => dto.result))
  }
  getAllProduct(): Observable<BaseDto<Array<Product>>> {
    return this.http.get<BaseDto<Array<Product>>>(EnquiryApi.getAllProduct)
  }
  getProductByGroup(prodGroup): Observable<BaseDto<Array<Product>>> {
    return this.http.get<BaseDto<Array<Product>>>(EnquiryApi.getProductByGroup, {
      params : new HttpParams().set('prodGroup', prodGroup)
    })
  }
  dropDownEnquiryStatus(): Observable<BaseDto<Array<EnquiryStatus>>> {
    return this.http.get<BaseDto<Array<EnquiryStatus>>>(EnquiryApi.dropDownEnquiryStatus)
  }

  getSeriesByProduct(product: string): Observable<BaseDto<Array<SeriesByProduct>>> {
    return this.http.get<BaseDto<Array<SeriesByProduct>>>(EnquiryApi.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }

  getModelBySeries(series: string): Observable<BaseDto<Array<ModelBySeries>>> {
    return this.http.get<BaseDto<Array<ModelBySeries>>>(EnquiryApi.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getAllVariant(): Observable<BaseDto<Array<Variants>>> {
    return this.http.get<BaseDto<Array<Variants>>>(EnquiryApi.getAllVariant)
  }


  getEnquirySearch(filter: SearchEnquiryFilter): Observable<BaseDto<Array<SearchEnqiryList>>> {

    return this.http.get<BaseDto<Array<SearchEnqiryList>>>(
      EnquiryApi.getEnquirySearch, {
      params: this.prepareHttpParams(filter)
    })
  }

  private prepareHttpParams(filter: SearchEnquiryFilter) {
    let httpParams = new HttpParams()

    for (const key of Object.keys(filter)) {
      if (`` + filter[key]) {
        if (key == 'toDate' || key == 'fromDate' || key == 'nextFollowUpToDate' || key == 'nextfollowUpFromDate' || key == 'tentativePurchaseToDate' || key == 'tentativePurchaseFromDate') {
          httpParams = httpParams.append(key, this.convertDateToServerFormat(filter[key]))
        }
        else httpParams = httpParams.append(key, filter[key])
      }
    }
    console.log(httpParams)
    return httpParams
  }

  convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' +  date.getDate()
      return formattedDate
    }
    return null
  }
  downloadExcelReport(filter: SearchEnquiryFilter): Observable<HttpResponse<Blob>>{
      return this.http.get<Blob>(EnquiryApi.downloadEnquiryReportExcelUrl, 
         { params: this.prepareHttpParams(filter), observe: 'response', responseType: 'blob' as 'json' }
      )
  }
  
}
