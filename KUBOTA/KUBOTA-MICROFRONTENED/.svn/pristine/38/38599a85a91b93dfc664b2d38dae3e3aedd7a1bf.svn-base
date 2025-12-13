import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { ProductIntrestedObj } from 'EnquiryProductIntrested';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MarketIntelligenceService {
  private readonly exchangeBrandURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getExchangeBrand}`;
  private readonly dropdownProductUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;
  private readonly feedbackCategory = `${environment.baseUrl}${urlService.api}/common/syslookup/lookupByCode`;
  private readonly submitMarketDataUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/marketIntelligence/create`;
  private readonly fetchSearchDataUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/marketIntelligence/`;
  private readonly downloadSearchReportUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/marketIntelligence/report`;
  private readonly getBanksFromDealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.dealerbankdetails}${urlService.getBankListByDealer}`;
  
  productIntrestedObj = {} as ProductIntrestedObj
  constructor(private http: HttpClient) { }

  
  dropdownFeedbackCategory() {
    return this.http.get(`${this.feedbackCategory}`,{
      params : new HttpParams().set('code','FEEDBACK_CATEGORY')
    })
  }

  getBanksFromDealerCode(dealerCode: string) {
    return this.http.get(`${this.getBanksFromDealerCodeUrl}`, {
      params: new HttpParams().set('dealerCode', dealerCode)
    })
  }

  dropdownExchangeBrand() {
    return this.http.get(`${this.exchangeBrandURL}`)
  }

  dropdownProduct() {
    return this.http.get(`${this.dropdownProductUrl}`)
  }

  submitMarketIntelligenceData(formData: any){
    return this.http.post(`${this.submitMarketDataUrl}`, formData)
  }

  fetchSearchData(searchObj){
    return this.http.post(`${this.fetchSearchDataUrl}`, searchObj)
  }

  downloadExcelReport(searchObj): Observable<HttpResponse<Blob>>{
    return this.http.post<Blob>(`${this.downloadSearchReportUrl}`, searchObj, { 
      observe: 'response', responseType: 'blob' as 'json' }
    );
  }
}
