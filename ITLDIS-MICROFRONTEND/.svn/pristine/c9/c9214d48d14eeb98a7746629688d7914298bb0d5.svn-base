import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';

@Injectable()
export class SearchResultChannelFinanceIndentService {
  private readonly getAllDealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.dealerEmployeeMaster}${urlService.getDealerCodes}`;
  private readonly searchCfiUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.searchBy}`;
  private readonly searchByIndentNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.searchIndentNumber}`;
  private readonly getAllDealerCategoryUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.getDealerCategory}`;
  private readonly getAllBanksUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.dealerbankdetails}${urlService.getBankList}`;

  constructor(
    private httpClient: HttpClient
  ) { }

  searchCfi(searchData: object) {
    // Object.keys(searchData).forEach((key, index) => {
    //   console.log("key ", searchData[key]);
    //   if (searchData[key] === null) {
    //     delete searchData[key]
    //   } else if ((key === 'fromDate') || (key === 'toDate')) {
    //     searchData[key] = this.convertDateToDDMMYYYY(searchData[key]);
    //   }
    // })
    return this.httpClient.post(this.searchCfiUrl, searchData)
  }
  searchByIndentNumber(searkKey: string) {
    return this.httpClient.get(this.searchByIndentNumberUrl, {
      params: new HttpParams().set('indentNumber', searkKey)
    })
  }
  getAllBanks() {
    return this.httpClient.get(this.getAllBanksUrl)
  }
  getAllDealerCodes() {
    return this.httpClient.get(this.getAllDealerCodeUrl)
  }
  getAllDealerCategory() {
    return this.httpClient.get(this.getAllDealerCategoryUrl)
  }
  convertDateToDDMMYYYY(simpleDate: Date) {
    let day = new Date(simpleDate).getDate();
    let month = new Date(simpleDate).getMonth() + 1;
    let year = new Date(simpleDate).getFullYear();
    let finalDate = `${day}-${month}-${year}`;
    return finalDate;
  }
}
