import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { Observable } from 'rxjs';
import { DropDownVariant, DropDownSubModel, DropDownModel, DropDownSeries, DropDownProduct, autoItemNumber, searchItemMaster } from 'SearchMachineItem';
import { UtilsService } from '../../../../../utils/utils.service';
import { BaseDto } from 'BaseDto';

@Injectable({
  providedIn: 'root'
})
export class SearchMachineItemService {
  private readonly  searchItemNoUrl=`${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.itemNoAuto}`
  // GET /api/master/product/itemNoAuto
  private readonly getProductListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getProduct}`
  private readonly getSeriesListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSeries}`
  // GET /api/master/product/getAllSeries
  private readonly getModelListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllModel}`
  // GET /api/master/product/getAllModel
  private readonly getSubModelListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSubModel}`
  // GET /api/master/product/getAllSubModel
  private readonly getVariantListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`
  // GET /api/master/product/getAllVariant
  private readonly searchItemMasterUrl=`${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.searchItem}`
  // POST /api/master/product/searchItem
  constructor(private httpClient: HttpClient,
    private utilsService: UtilsService) { }

  searchItemNo(itemNo: string):Observable<BaseDto<Array<autoItemNumber>>> {
    return this.httpClient.get<BaseDto<Array<autoItemNumber>>>(this.searchItemNoUrl, {
      params: new HttpParams().set('itemNo', itemNo)
    })
  }
  
  getProductList():Observable<BaseDto<Array<DropDownProduct>>> {
    return this.httpClient.get<BaseDto<Array<DropDownProduct>>>(this.getProductListUrl)
  }
  getSeriesList() :Observable<BaseDto<Array<DropDownSeries>>>{
    return this.httpClient.get<BaseDto<Array<DropDownSeries>>>(this.getSeriesListUrl)
  }
  getModelList() :Observable<BaseDto<Array<DropDownModel>>>{
    return this.httpClient.get<BaseDto<Array<DropDownModel>>>(this.getModelListUrl)
  }
  getSubModelList() :Observable<BaseDto<Array<DropDownSubModel>>>{
    return this.httpClient.get<BaseDto<Array<DropDownSubModel>>>(this.getSubModelListUrl)
  }
  getVariantList(): Observable<BaseDto<Array<DropDownVariant>>> {
    return this.httpClient.get<BaseDto<Array<DropDownVariant>>>(this.getVariantListUrl)
  }
  searchItemMaster(searchValue: searchItemMaster) {
    searchValue = this.utilsService.removeEmptyKey<searchItemMaster>(searchValue);
    return this.httpClient.post(this.searchItemMasterUrl, searchValue)
  }
}
