import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { urlService } from '../../webservice-config/baseurl';

@Injectable()
export class SalesPreSalesCommonService {
  // http://192.168.15.109:8383/api/master/product/getItemNumberModelProductSeries?search=w

  private readonly itemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemNumberModelProductSeries}`;


  private readonly activityNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchActivityNumber}`
  private readonly productURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`
  private readonly getByItemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getByItemNo}`;


  constructor(
    private http: HttpClient
  ) { }
  searchItemNo(search, variant) {
    return this.http.get(`${this.itemNoUrl}`, {
      params: new HttpParams().set('search', search).set('variant',variant),
    })
  }
  getActivityNo(activityNumber) {
    return this.http.get(`${this.activityNoUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber).set('functionality','SEARCH')
    })
  }
  dropdownProduct() {
    return this.http.get(`${this.productURL}`)
  }
  searchByItemNo(itemNo) {
    return this.http.get(`${this.getByItemNoUrl}`, {
      params: new HttpParams().set('itemNo', itemNo)
    })
  }

  itemNumberGetDataForPatch(itemNumber) {
    return this.http.get(`${this.getByItemNoUrl}`, {
      params: new HttpParams().set('itemNo', itemNumber)
    })
  }
}
