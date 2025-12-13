import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExchangeBrand, ItemNumber, PopulateByItemNo, Model, AutoCompSubModel, AutoCompVariant } from '../domains/enquiry';
import { BaseDto } from 'BaseDto';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class ProductInterestedV2WebService {

  constructor(
    private http: HttpClient
  ) { }

  getExchangeBrand(): Observable<BaseDto<Array<ExchangeBrand>>> {
    return this.http.get<BaseDto<Array<ExchangeBrand>>>(EnquiryApi.getExchangeBrand)
  }

  getItemNumberModelProductSeries(search: string): Observable<Array<ItemNumber>> {
    return this.http.get<BaseDto<Array<ItemNumber>>>(EnquiryApi.getItemNumberModelProductSeries,
      {
        params: new HttpParams()
          .append('search', search)
      }).pipe(map(dto => dto.result))
  }

  getByItemNo(itemNo: string): Observable<PopulateByItemNo> {
    return this.http.get<BaseDto<PopulateByItemNo>>(EnquiryApi.getByItemNo,
      {
        params: new HttpParams()
          .append('itemNo', itemNo)
      }).pipe(map(dto => dto.result))
  }

  getModel(model: string): Observable<Array<Model>> {
    return this.http.get<BaseDto<Array<Model>>>(EnquiryApi.getModel,
      {
        params: new HttpParams()
          .append('model', model)
      }).pipe(map(dto => dto.result))
  }

  getSeriesAndProductByModel(model: string): Observable<PopulateByItemNo> {
    return this.http.get<BaseDto<PopulateByItemNo>>(EnquiryApi.getSeriesAndProductByModel,
      {
        params: new HttpParams()
          .append('model', model)
      }).pipe(map(dto => dto.result))
  }

  getSubModel(model: string): Observable<BaseDto<Array<AutoCompSubModel>>> {
    return this.http.get<BaseDto<Array<AutoCompSubModel>>>(EnquiryApi.getSubModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariant(model: string, subModel: string): Observable<Array<AutoCompVariant>> {
    return this.http.get<BaseDto<Array<AutoCompVariant>>>(EnquiryApi.getVariant,
      {
        params: new HttpParams()
          .append('model', model)
          .append('subModel', subModel)
      }).pipe(map(dto => dto.result))
  }

  getItemByModelSubModelVariant(model: string, subModel: string, variant: string): Observable<Array<ItemNumber>> {
    return this.http.get<BaseDto<Array<ItemNumber>>>(EnquiryApi.getItemByModelSubModelVariant,
      {
        params: new HttpParams()
          .append('model', model)
          .append('subModel', subModel)
          .append('variant', variant)
      }).pipe(map(dto => dto.result))
  }

  getItemByModel(itemNumber: string, model: string): Observable<Array<ItemNumber>> {
    return this.http.get<BaseDto<Array<ItemNumber>>>(EnquiryApi.getItemByModel,
      {
        params: new HttpParams()
          .append('itemNumber', itemNumber)
          .append('model', model)
      }).pipe(map(dto => dto.result))
  }


}
