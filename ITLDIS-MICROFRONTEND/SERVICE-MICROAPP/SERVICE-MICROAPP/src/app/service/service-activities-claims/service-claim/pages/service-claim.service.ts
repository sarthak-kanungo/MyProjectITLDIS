import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';

@Injectable()
export class ServiceClaimService {

  productServiceBehaviourSubject = new BehaviorSubject<any[]>(null);
  documentDetailsBehaviourSubject = new BehaviorSubject<any[]>(null);
  addIdForApprovalBehaviourSubject = new BehaviorSubject<bigint>(null);
  removeIdForApprovalBehaviourSubject = new BehaviorSubject<bigint>(null);
  
  static readonly claimPeriodUrl = `${environment.baseUrl}/${environment.api}/service/claim/getClaimPeriod`
  static readonly create = `${environment.baseUrl}/${environment.api}/service/claim/create`
  static readonly claimNumberSearchUrl = `${environment.baseUrl}/${environment.api}/service/claim/claimNumberSearch`
  static readonly viewClaimDetails = `${environment.baseUrl}/${environment.api}/service/claim/view`
  static readonly lookupUrl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`
  static readonly fetchClaimList =`${environment.baseUrl}/${environment.api}/service/claim/fetchClaimList`
  static readonly approvalUrl =`${environment.baseUrl}/${environment.api}/service/claim/approval`
  static readonly downloadServiceClaimExcelReport =`${environment.baseUrl}/${environment.api}/service/claim/downloadServiceClaimExcelReport`
  
  constructor(private httpClient: HttpClient) { }

  getClaimPeriod(){
    return this.httpClient.get(ServiceClaimService.claimPeriodUrl);
  }

  getLookup(code){
    return this.httpClient.get(ServiceClaimService.lookupUrl, {
      params : new HttpParams().set('code',code)
    });
  }

  getClaimNumber(searchText){
    return this.httpClient.get(ServiceClaimService.claimNumberSearchUrl, {
      params : new HttpParams().set('searchText',searchText)
    });
  }

  viewClaimDetails(claimId){
    return this.httpClient.get(ServiceClaimService.viewClaimDetails, {
      params : new HttpParams().set('id',claimId)
    });
  }

  saveClaim(saveObject){
    return this.httpClient.post(ServiceClaimService.create, saveObject);
  }

  fetchClaimList(searchObject){
    return this.httpClient.post(ServiceClaimService.fetchClaimList, searchObject);
  }

  approveRejectClaim(approvalObject){
    return this.httpClient.post(ServiceClaimService.approvalUrl, approvalObject);
  }
  downloadServiceClaimExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(ServiceClaimService.downloadServiceClaimExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
