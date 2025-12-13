import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SearchEnquiryFilterDomain, SearchEnqiryListDomain } from 'EnquirySearchCriteria';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class EnquirySearchCriteriaContainerService {
  // private readonly modelURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllModel}`
  // private readonly subModelURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSubModel}`
  private readonly variantURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`
  // private readonly seriesURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSeriesByProduct}`;
  private readonly getEnquirySearchUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquirySearch}`


  private readonly seriesURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSeriesByProduct}`;

  private readonly modelURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getModelBySeries}`;


  private readonly subModelURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSubModel}`;
  private readonly dropdownProductUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;

  constructor(
    private http: HttpClient
  ) { }


  dropdownProduct() {
    return this.http.get(`${this.dropdownProductUrl}`)
  }



  dropdownVariant() {
    return this.http.get(`${this.variantURL}`)
  }

  dropdownSeries(searkKey: string) {
    return this.http.get(this.seriesURL, {
      params: new HttpParams().set('product', searkKey)
    })
  }
  dropdownmodel(searkKey: string) {
    return this.http.get(this.modelURL, {
      params: new HttpParams().set('series', searkKey)
    })
  }

  dropdownSubModel(searkKey: string) {
    return this.http.get(this.subModelURL, {
      params: new HttpParams().set('model', searkKey)
    })
  }

  getEnquiryList(filter: SearchEnquiryFilterDomain): Observable<BaseDto<Array<SearchEnqiryListDomain>>> {

    return this.http.get<BaseDto<Array<SearchEnqiryListDomain>>>(
      this.getEnquirySearchUrl, {
      params: this.prepareHttpParams(filter)
    })
  }

  private prepareHttpParams(filter: SearchEnquiryFilterDomain) {
    // console.log(filter)
    let httpParams = new HttpParams()

    for (const key of Object.keys(filter)) {
      // console.log(key, filter[key])
      if (`` + filter[key]) {
        if (key == 'toDate' || key == 'fromDate' || key == 'nextFollowUpToDate' || key == 'nextFollowUpFromDate' || key == 'tentativePurchaseToDate' || key == 'tentativePurchaseFromDate') {
          httpParams = httpParams.append(key, this.convertDateToOurFormat(filter[key]))
        }
        else httpParams = httpParams.append(key, filter[key])
      }
    }
    console.log(httpParams)
    return httpParams
  }

  private convertDateToOurFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      console.log(formattedDate)
      return formattedDate
    }
    return ''
  }



}
