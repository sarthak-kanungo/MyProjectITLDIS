import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceClaimApprovalDetailsService {
  static readonly getClaimForApprovalApi = `${environment.baseUrl}/${environment.api}/service/accpac/claimSearchForApproval`
  static readonly submitClaimApprovalApi = `${environment.baseUrl}/${environment.api}/service/accpac/submitClaimForApproval`
  static readonly viewClaimApprovalApi = `${environment.baseUrl}/${environment.api}/service/accpac/viewManagementApprovedClaim`
  static readonly claimApprovalApi = `${environment.baseUrl}/${environment.api}/service/accpac/claimManagementApproval`
  // Add Api for hierarchy By Ankit Rai
  //create
  static readonly getWarrantyClaimHierarchyById = `${environment.baseUrl}/${environment.api}/service/accpac/getWarrantyClaimHierarchyById`
  // 
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`;

  private readonly autoCompletePcrNoUrl = `${environment.baseUrl}${urlService.api}/warranty/pcr/autoCompletePcrNo`
  private readonly searchAutoCompleteWcrNoUrl = `${environment.baseUrl}${urlService.api}/warranty/Wcr/searchAutoCompleteWcrNo`;
  private readonly searchWcrTypeUrl = `${environment.baseUrl}${urlService.api}/warranty/Wcr/searchWcrType`;
  private readonly searchAutoCompleteJobCodeUrl = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteJobCode`;

  constructor(private httpClient: HttpClient) { }

  getClaimDetails(requestFrom:string, id:string){
    return this.httpClient.get(ServiceClaimApprovalDetailsService.viewClaimApprovalApi,{
      params : new HttpParams().set('requestFrom',requestFrom).set('id',id)
    })
  }
  // Service for Hier Data
  getHierData(id: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(`${ServiceClaimApprovalDetailsService.getWarrantyClaimHierarchyById}/${id}`
     ).pipe(map(dto => dto.result))
  }
  

  getClaimListForApproval(requestFrom:string){
    return this.httpClient.get(ServiceClaimApprovalDetailsService.getClaimForApprovalApi,{
      params : new HttpParams().set('requestFrom',requestFrom)
    })
  }

  searchClaimListForApproval(requestBody:any){
    return this.httpClient.post(ServiceClaimApprovalDetailsService.getClaimForApprovalApi, requestBody)
  }

  submitClaimApprovalApi(saveObject){
    return this.httpClient.post(ServiceClaimApprovalDetailsService.submitClaimApprovalApi, saveObject);
  }

  
  approveClaim(claimId: number, claimType:string, remark:string, approvalType:string){
    let data = {claimId : claimId, claimType:claimType, remark:remark, approvalType:approvalType};
    return this.httpClient.post(ServiceClaimApprovalDetailsService.claimApprovalApi, data);
  }

  searchWcrType(): Observable<any> {
    return this.httpClient.get(this.searchWcrTypeUrl);
  }
 
  autoCompletePcrNo(pcrNo: string): Observable<any> {
    return this.httpClient.get(this.autoCompletePcrNoUrl, {
      params: new HttpParams().set('pcrNo', pcrNo)
    });  
  }

  searchAutoCompleteWcrNo(wcrNo: string): Observable<any> {
    return this.httpClient.get(this.searchAutoCompleteWcrNoUrl, {
      params: new HttpParams().append('wcrNo',wcrNo)
    });
  }


  searchAutoCompleteJobCode(jobCode: string): Observable<any> {
    return this.httpClient.get(this.searchAutoCompleteJobCodeUrl, {
      params: new HttpParams().set('jobCode', jobCode)
    });
  }

  getDealerCodeList(dealerCode:string): Observable<any>{
    return this.httpClient.get(this.dealerCodeUrl, {
        params: new HttpParams().set('dealerCode', dealerCode)
      })
  }

}
