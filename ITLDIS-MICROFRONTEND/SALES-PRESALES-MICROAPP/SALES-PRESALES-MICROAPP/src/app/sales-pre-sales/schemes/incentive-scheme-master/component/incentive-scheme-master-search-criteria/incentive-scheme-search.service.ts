import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable({
  providedIn: 'root'
})
export class IncentiveSchemeSearchService {
  private readonly searchSchemeMasterUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/incentiveSchemeMaster/searchIncentiveScheme`
  private readonly searchSchemeNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/incentiveSchemeMaster/getIncentiveSchemesNo`
  constructor(private httpClient: HttpClient) { }


  searchSchemeMaster(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.convertDateToDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(this.searchSchemeMasterUrl, searchData)
  }

  convertDateToDDMMYYYY(simpleDate: Date) {
    let day = new Date(simpleDate).getDate();
    let month = new Date(simpleDate).getMonth() + 1;
    let year = new Date(simpleDate).getFullYear();
    let finalDate = `${day}-${month}-${year}`;
    return finalDate;
  }

  getSchemeNoList(searchValue:string){
    return this.httpClient.get(this.searchSchemeNoUrl, {
      params : new HttpParams().set('searchValue', searchValue)
    })
  }
}
