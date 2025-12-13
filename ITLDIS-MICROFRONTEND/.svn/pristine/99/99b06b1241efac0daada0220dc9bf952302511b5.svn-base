import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { SearchActivityClaimListDomain, ActivityClaimListSearchDomain } from 'ActivityClaimModule';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchActivityClaimService {
  constructor(
    private httpClient: HttpClient
  ) { }

  private readonly searchClaimNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.searchClaimNumber}`;
  private readonly searchActivityNumberOnSearchUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.searchActivityNumber}`;
  private readonly searchClaimStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.searchClaimStatus}`;
  private readonly getActivityTypesUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchactivitytype}`
  static readonly generateDealerInvoice = `${environment.baseUrl}${urlService.api}/service/activityClaim/generateDealerInvoice`
  private static readonly stateUrl = `${environment.baseUrl}${urlService.api}/warranty/pcr/getDealerStates`
 
  private readonly searchByUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.searchBy}`


  getClaimNumber(claimNumber) {
    return this.httpClient.get(`${this.searchClaimNumberUrl}`, {
      params: new HttpParams().set('claimNumber', claimNumber)
    })
  }
  getActivityNumber(activityNumber) {
    return this.httpClient.get(`${this.searchActivityNumberOnSearchUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
    })
  }
  getClaimStatus() {
    return this.httpClient.get(`${this.searchClaimStatusUrl}`, {

    })
  }
  getStates(dealerId:number){
    return this.httpClient.get<BaseDto<Array<Object>>>(SearchActivityClaimService.stateUrl,{
        params: new HttpParams()
            .append('dealerId', dealerId+"")
    })  
  }
  getActivityType() {
    return this.httpClient.get(this.getActivityTypesUrl)
  }
  // searchUsingFilter(formData: SearchActivityClaimListDomain) {
  //   return this.httpClient.post( this.searchByUrl, formData)
  // }

  searchUsingFilter(filter: SearchActivityClaimListDomain): Observable<BaseDto<Array<ActivityClaimListSearchDomain>>> {
    console.log("------------filter ", filter);
    return this.httpClient.post<BaseDto<Array<ActivityClaimListSearchDomain>>>(
      this.searchByUrl, filter)
  }

  generateInvoice(claimId:number, invoiceType:string){
    return this.httpClient.get(SearchActivityClaimService.generateDealerInvoice, {
      params : new HttpParams().set('claimId', claimId.toString()).set('invoiceType', invoiceType)
    })
  }
}
