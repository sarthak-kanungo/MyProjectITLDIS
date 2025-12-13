import { urlService } from './../../../../../webservice-config/baseurl';
import { Injectable } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';

import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable()
export class ActivityClaimCreationService {
  private activityClaimFrom: FormGroup
  httpClient: any;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  private readonly searchActivityNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.searchActivityNumberForClaim}`;
  private readonly getActivityClaimHeaderData = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/getActivityClaimHeaderData`;
  private readonly getActivityClaimHeads = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/getActivityClaimHeads`;
  private readonly getActivityReportPhotosByProposalId = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/getActivityReportPhotosByProposalId`;
  private readonly getActivityEffectivenessUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.getActivityEffectiveness}`;
  private readonly getClaimByIdUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/getMarketingActivityClaimById`;
  //getMarketingActivityClaimById
  private readonly saveActivityClaimUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/saveMarketingActivityClaim`;
  private readonly getGstDropDown = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/getGstDropDown`;
  private readonly approveMarketingActivityClaim=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.approveMarketingActivityClaim}`;

  private readonly editMarketingActivityClaim=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.editMarketingActivityClaim}`
   private readonly getReportImagesByProposalId=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}${urlService.getReportImagesByProposalId}`
  getActivityNumber(activityNumber) {
    return this.http.get(`${this.searchActivityNumberUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
    })
  }
  getActivityClaimHeaderDetails(activityNumberId) {
    return this.http.get(`${this.getActivityClaimHeaderData}`, {
      params: new HttpParams().set('activityNumberId', activityNumberId)
    })
  }

  getActivityClaimHeadsDetails(activityNumberId) {
    return this.http.get(`${this.getActivityClaimHeads}`, {
      params: new HttpParams().set('activityNumberId', activityNumberId)
    })
  }
  getActivityReportPhotos(activityNumberId) {
    return this.http.get(`${this.getActivityReportPhotosByProposalId}`, {
      params: new HttpParams().set('activityNumberId', activityNumberId)
    })
  }
  getActivityClaimById(id: number) {
    return this.http.get(`${this.getClaimByIdUrl}/${id}`)
  }

  DropDowngetGst() {
    return this.http.get(`${this.getGstDropDown}`)
  }


  getActivityEffectiveness() {
    return this.http.get(this.getActivityEffectivenessUrl)
  }

  saveActivityClaim(formData: FormData) {
    return this.http.post(this.saveActivityClaimUrl, formData)
  }

  updateForm(formData:any){
    return this.http.post(this.editMarketingActivityClaim,formData)
  }
  approveClaim(domain: any) {
    return this.http.post(this.approveMarketingActivityClaim, domain)
  }
  getActivityReport(proposalId:string) {
    return this.http.get(`${this.getReportImagesByProposalId}`, {
      params: new HttpParams().set('proposalId', proposalId)
    })
  }
}
