import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { QuotationSearchFilterDomain, QuotationListSearchDomain } from 'SearchQuotationsModule';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class SearchQuotationContainerService {
  private readonly getSourceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.enquirySource }${ urlService.getSource }`;
  private readonly getProductUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getAllProduct }`;
  private readonly getSeriesByProductUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getSeriesByProduct }`;
  private readonly getModelBySeriesUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getModelBySeries }`;
  private readonly getSubModelUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getSubModel }`;
  private readonly getVariantBySubModelUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getVariantBySubModel }`;
  private readonly getItemNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getItemNo }`;
  private readonly getQuotationSearchUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getQuotationSearch }`;
  private readonly getQuotationCodeListUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getQuotationCode }`;
  private readonly getSalesPersonUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.dealerEmployeeMaster }${ urlService.getSalesPerson }`;
  private readonly getEnquiryStatusUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.enquiry }${ urlService.getEnquiryStatus }`;

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService,
    
  ) { }

  searchSourceTypeList() {
    return this.httpClient.get(`${ this.getSourceUrl }`)
  }

  searchProductTypeList() {
    return this.httpClient.get(`${ this.getProductUrl }`)
  }

  searchSeariesTypeList(product: string) {
    return this.httpClient.get(`${ this.getSeriesByProductUrl }`, {
      params: new HttpParams().set('product', product)
    });
  }
  getModelBySeriesList(series: string) {
    return this.httpClient.get(`${ this.getModelBySeriesUrl }`, {
      params: new HttpParams().set('series', series)
    });
  }
  searchSubModelTypeList(model: string) {
    return this.httpClient.get(this.getSubModelUrl, {
      params: new HttpParams().set('model', model)
    });
  }
  searchVariantTypeList(subModel: string) {
    return this.httpClient.get(`${ this.getVariantBySubModelUrl }`, {
      params: new HttpParams().set('subModel', subModel)
    });
  }
  getItemNo(itemNo) {
    return this.httpClient.get(`${ this.getItemNoUrl }`, {
      params: new HttpParams().set('itemNo', itemNo)
    })
  }
  getQuotationCodeList(quotationCode: string) {
    return this.httpClient.get(`${ this.getQuotationCodeListUrl }`, {
      params: new HttpParams().set('quotationCode', quotationCode)
    })
  }
  getSalesPerson() {
    return this.httpClient.get(this.getSalesPersonUrl);
  }
  getEnquiryStatus() {
    return this.httpClient.get(this.getEnquiryStatusUrl);
  }

  searchUsingFilter(filter: QuotationSearchFilterDomain): Observable<BaseDto<Array<QuotationListSearchDomain>>> {

    return this.httpClient.get<BaseDto<Array<QuotationListSearchDomain>>>(
      this.getQuotationSearchUrl, {
      params: this.prepareHttpParams(filter)
    })
  }

  private prepareHttpParams(filter: QuotationSearchFilterDomain) {
    let httpParams = new HttpParams()
    for (const key of Object.keys(filter)) {
      if (!!filter[key] || `${ filter[key] }` === '0') {
        if (key == 'quotationToDate' || key == 'quotationFromDate') {
          //httpParams = httpParams.append(key, this.convertDateToOurFormat(filter[key]) || null)
          httpParams = httpParams.append(key, this.dateService.getDateIntoYYYYMMDD(filter[key]) || null)     /* added by vinay to covert date into  YYYYMMDD*/
        }
        else httpParams = httpParams.append(key, filter[key])
      }
    }
    return httpParams
  }

  private convertDateToOurFormat(dt: string) {
    if (dt) {
      return new Date(dt).toJSON().slice(0, 10).split('-').reverse().join('-')
    }
    return ''
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
}
