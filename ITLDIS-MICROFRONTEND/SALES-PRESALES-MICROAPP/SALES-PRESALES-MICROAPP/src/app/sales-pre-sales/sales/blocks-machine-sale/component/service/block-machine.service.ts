import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { blockMachinesForSale } from '../../url-util/url.util';
import { statusModel } from '../block-machine-sale-search/models/models';

@Injectable({
  providedIn: 'root'
})
export class BlockMachineService {
  static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
  static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
  static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
  static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
  static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
  static readonly dropDownStatus = `${environment.baseUrl}${urlService.api}/dropDownStatus`
  
  constructor(private httpClient:HttpClient) { }
  getAllProduct() {
  return this.httpClient.get(BlockMachineService.getAllProduct)
    
  }
  getSeriesByProduct(product: string){
    return this.httpClient.get(BlockMachineService.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }
  dropDownStatus(): Observable<Array<statusModel>> {
    return this.httpClient.get<BaseDto<Array<statusModel>>>(BlockMachineService.dropDownStatus)
    .pipe(map(res => res.result));
  }
  

  getModelBySeries(series: string){
    return this.httpClient.get(BlockMachineService.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getSubModelByModel(model: string){
    return this.httpClient.get(BlockMachineService.getSubModelByModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariantBySubModel(subModel: string){
    return this.httpClient.get(BlockMachineService.getVariantBySubModel,
      {
        params: new HttpParams()
          .append('subModel', subModel)
      })
  }
  blockMachineForSaleSearch(searchFilter:any){
    return this.httpClient.post(blockMachinesForSale.blockMachineForSaleSearch, searchFilter);
  }

  changeActiveStatus(id) {
    return this.httpClient.get(blockMachinesForSale.changeActiveStatus,  {
      params: new HttpParams().set('id', id)
    });
  }
  changeActiveStatusByIds(jsonObj:any) : Observable<any> {
    return this.httpClient.post<BaseDto<any>>(blockMachinesForSale.changeActiveStatusByIds, jsonObj)
}

}