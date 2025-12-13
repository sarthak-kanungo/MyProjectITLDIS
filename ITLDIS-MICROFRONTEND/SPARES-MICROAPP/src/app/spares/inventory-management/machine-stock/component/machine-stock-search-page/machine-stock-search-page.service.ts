import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { SearchMachineStock, Model, PopulateByItemNo, SubModel, Variants, Series, Product } from '../../domain/machine-stock';
import { MachineStockApi } from '../../url-utils/machine-stock-url'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { BaseDto } from 'BaseDto';

@Injectable()
export class MachineStockSearchPageService {

  constructor(
          public http: HttpClient
  ) { }
  
  searchStock(formData: SearchMachineStock) {
    return this.http.post(MachineStockApi.searchMachineStock, formData)
  }
  
  getAllProduct() {
      return this.http.get<BaseDto<Array<Product>>>(MachineStockApi.getAllProduct)
    }

  
  getSeriesByProduct(product: string){
      return this.http.get<BaseDto<Array<Series>>>(MachineStockApi.getSeriesByProduct,
        {
          params: new HttpParams()
            .append('product', product)
        }).pipe(map(dto => dto.result))
    }

  getModelBySeries(series: string){
      return this.http.get<BaseDto<Array<Model>>>(MachineStockApi.getModelBySeries,
        {
          params: new HttpParams()
            .append('series', series)
        }).pipe(map(dto => dto.result))
    }


   getSubModel(model: string){
      return this.http.get<BaseDto<Array<SubModel>>>(MachineStockApi.getSubModel,
        {
          params: new HttpParams()
            .append('model', model)
        }).pipe(map(dto => dto.result))
    }

    getVariant(model: string, subModel: string) {
      return this.http.get<BaseDto<Array<Variants>>>(MachineStockApi.getVariant,
        {
          params: new HttpParams()
            .append('model', model)
            .append('subModel', subModel)
        }).pipe(map(dto => dto.result))
    }
    // searchByEngineNumber(engineNumber: string) {
    //   let keyMap = {
    //     id: 'id',
    //     value: 'value'
    //   };
    //   return this.httpClient.get<BaseDto<Array<SelectList>>>(this.searchByEngineNumberUrl, {
    //     params: new HttpParams()
    //       .set('engineNumber', engineNumber)
    //       .set('userId', userId)
    //   })
    //     .pipe(
    //       map(res => {
  
    //         res.result = this.selectListAdapter.adapt(res.result, keyMap);
    //         console.log(' res.result', res.result);
    //         return res;
    //       })
    //     );
    // }
    // searchChassisNumbers(chassisNumber) {
    //   let keyMap = {
    //     id: 'id',
    //     value: 'chassisNo'
    //   };
    //   return this.httpClient.get<BaseDto<Array<SelectList>>>(this.getChassisNumbersUrl, {
    //     params: new HttpParams().set('chassisNumber', chassisNumber)
    //   }).pipe(
    //     map(res => {
    //       res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
    //       return res;
    //     })
    //   );
    // }

    downloadMachineStockExcel(filter): Observable<HttpResponse<Blob>>{
      return this.http.post<Blob>(MachineStockApi.getMachineStockExcel, filter,
         {observe: 'response', responseType: 'blob' as 'json' }
      )
    }
}
