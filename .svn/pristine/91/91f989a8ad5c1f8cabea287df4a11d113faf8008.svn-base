import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { MarketingActivityCreateDomain } from 'ActivityProposalModule';


@Injectable()
export class MarketingActivityCreateContainerService {
  private readonly activityTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquirySource}${urlService.getSource}`

  private readonly activityPurposeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquirySource}${urlService.getPurpose}`

  private readonly activityNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchActivityNumber}`

  private readonly getMaxAllowedBudget = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getMaxAllowedBudget`
  private readonly activityProposalByIdUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getMarketingActivityById`
  private readonly activityTypeByPurposeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getEnquirySourceBySourcePurpose`

  private readonly activityCategoryUrl =  `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquirySource}${urlService.getActivityCategory}`   
  //  /api/salesandpresales/activitytypebudgetmaster/getActivityTypeByPurpose
  //submit
  marketingActivityCreateDomain = {} as MarketingActivityCreateDomain

  constructor(private httpClient: HttpClient) { }

  getPurposeActivity() {
    return this.httpClient.get(this.activityPurposeUrl)
  }
  getActivityType() {
    return this.httpClient.get(this.activityTypeUrl)
  }
  getActivityCategoryList(){
      return this.httpClient.get(this.activityCategoryUrl)
  }
  getActivityTypeByPurpose(sourcePurposeId: number){
  return this.httpClient.get(`${this.activityTypeByPurposeUrl}`,{
      params: new HttpParams().set('sourcePurposeId',sourcePurposeId.toString())
  })
  }
  
  getActivityNo(activityNumber) {
    return this.httpClient.get(`${this.activityNoUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
    })
  }

  maximumLimitByActivityType(activityTypeId, fromDate, toDate) {
    return this.httpClient.get(`${this.getMaxAllowedBudget}`, {
      params: new HttpParams().set('activityTypeId', activityTypeId)
          .set('fromDate', fromDate)
          .set('toDate', toDate)
    })
  }
 
  activityTypeByPurposeGenConv(activityPurpose){
    this.httpClient.get(`${this.activityTypeByPurposeUrl}`,{
      params: new HttpParams().set('activityPurpose', activityPurpose)
    })
  }
 
  getActivityProposalById(id: string) {
    return this.httpClient.get(`${this.activityProposalByIdUrl}/${id}`)
  }
}

