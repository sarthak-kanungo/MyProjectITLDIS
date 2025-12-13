import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { ProductIntrestedObj } from 'EnquiryProductIntrested';


@Injectable()
export class EnquiryProductInstrestedContainerService {
  private readonly exchangeBrandURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getExchangeBrand}`;
  private readonly getModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getModel}`;
  private readonly getSubModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSubModel}`;
  private readonly getVariantUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getVariant}`;
  private readonly getSeriesAndProductByModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSeriesAndProductByModel}`;
  private readonly getItemByModelSubModelVariantUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemByModelSubModelVariant}`;


  // private readonly itemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemNumberModelProductSeries}`;


  private readonly getItemByModelUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getItemByModel}`;

  //http://192.168.15.109:8383/api/salesandpresales/enquiry/getItemByModel?itemNumber=w
  //http://192.168.15.109:8383/api/master/product/getItemByModelSubModelVariant?model=A211N&subModel=4WD&variant=STD
  //http://192.168.15.109:8383/api/master/product/getSeriesAndProductByModel?model=A211N
  productIntrestedObj = {} as ProductIntrestedObj
  constructor(
    private http: HttpClient
  ) { }


  dropdownExchangeBrand() {
    return this.http.get(`${this.exchangeBrandURL}`)
  }

  searchModel(model) {
    return this.http.get(`${this.getModelUrl}`, {
      params: new HttpParams().set('model', model)
    })
  }


  searchSubModel(model) {
    return this.http.get(`${this.getSubModelUrl}`, {
      params: new HttpParams().set('model', model)
    })
  }

  searchVariant(model, subModel) {
    return this.http.get(`${this.getVariantUrl}`, {
      params: new HttpParams().set('model', model).set('subModel', subModel)
    })
  }

  getSeriesAndProductByModel(model) {
    return this.http.get(`${this.getSeriesAndProductByModelUrl}`, {
      params: new HttpParams().set('model', model)
    })
  }

  getItemByModelSubModelVariant(model, subModel, variant) {
    return this.http.get(`${this.getItemByModelSubModelVariantUrl}`, {
      params: new HttpParams().set('model', model).set('subModel', subModel).set('variant', variant)
    })
  }

  getItemByModel(itemNumber, model) {
    return this.http.get(`${this.getItemByModelUrl}`, {
      params: new HttpParams().set('itemNumber', itemNumber).set('model', model)
    })
  }
}
