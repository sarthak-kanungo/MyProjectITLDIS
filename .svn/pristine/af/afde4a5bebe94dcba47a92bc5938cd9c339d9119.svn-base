import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class IncentiveSchemeClaimService {

  private readonly wholesaleIncentiveSchemeDetailsUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/wholesaleIncentiveSchemeDetails`
  private readonly retailsIncentiveSchemeDetailsUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/retailsIncentiveSchemeDetails`
  private readonly saveSchemeClaimDetailsUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/saveIncentiveSchemeDetails`
  private readonly fetchSchemeClaimDetailsUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/viewIncentiveSchemeDetails`
  private readonly searchIncentiveSchemeClaimUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/searchIncentiveSchemeClaim`
  private readonly getIncentiveSchemesClaimNoUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/getIncentiveSchemesClaimNo`
  private readonly approveSchemeClaimDetailsUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/approveIncentiveClaim`
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  
  private readonly invoiceUploadUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/invoiceUpload`
  private readonly invoiceDownloadUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/invoiceDownload`
  private readonly invoiceVerifiedUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/invoiceVerify`
  private readonly invoiceGenerationUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveScheme/claim/generateInvoice`
  
  constructor(private httpClient: HttpClient) { }

  getWholesaleIncentiveSchemeDetails(month:any){
    return this.httpClient.get(this.wholesaleIncentiveSchemeDetailsUrl,{
      params : new HttpParams().set('month',month) 
    })
  }
  
  getRetailsIncentiveSchemeDetails(month:any){
    return this.httpClient.get(this.retailsIncentiveSchemeDetailsUrl,{
      params : new HttpParams().set('month',month) 
    })
  }

  submitClaim(claimDetails:any){
    return this.httpClient.post(this.saveSchemeClaimDetailsUrl, claimDetails);
  }

  approveClaim(claimDetails:any){
    return this.httpClient.post(this.approveSchemeClaimDetailsUrl, claimDetails);
  }

  fetchClaimDetails(id:any){
    return this.httpClient.get(this.fetchSchemeClaimDetailsUrl, {
      params : new HttpParams().set('id',id)
    })
  }

  searchClaimList(searchFilter:any){
    return this.httpClient.post(this.searchIncentiveSchemeClaimUrl, searchFilter);
  }

  getIncentiveSchemesClaimNo(searchValue:any){
    return this.httpClient.get(this.getIncentiveSchemesClaimNoUrl,{
      params : new HttpParams().set('searchValue', searchValue)
    })
  }

  getDealerList(dealerCode:any){
    return this.httpClient.get(this.dealerCodeUrl, {
      params: new HttpParams().set('dealerCode', dealerCode)
    });
  }

  uploadFile(updatedata:any, file:any){
    let formData: FormData = new FormData();

    formData.append('invoiceData', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
    formData.append('attachedFile', file);
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post<any> (this.invoiceUploadUrl, formData,{headers})
  }
  downloadInvoice(id):Observable<HttpResponse<Blob>>{
    return this.httpClient.get<Blob>(this.invoiceDownloadUrl,  {
      params : new HttpParams().set("id", id),
      observe: 'response', responseType: 'blob' as 'json'
     })
  }
  
  generateInvoice(id){
    return this.httpClient.get(this.invoiceGenerationUrl,  {
      params : new HttpParams().set("id", id)
     })
  }

  updateWcrAsVerified(wcrId){
    return this.httpClient.get(this.invoiceVerifiedUrl, {
      params : new HttpParams().set('wcrId', wcrId)
    })
  }
}
