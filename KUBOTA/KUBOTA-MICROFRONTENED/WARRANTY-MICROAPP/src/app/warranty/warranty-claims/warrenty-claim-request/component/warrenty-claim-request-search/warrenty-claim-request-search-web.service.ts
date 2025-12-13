import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { WcrApi } from '../../url-utils/warrenty-claim-request-url';
import { Observable } from 'rxjs';
import { WcrDropdown, SearchWcr, WcrFinalstatus } from '../../domain/warrenty-claim-request.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { AutoCompleteResult } from '../../../retro-fitment-campaign/domain/retro-fitment-campaign.domain';

@Injectable()
export class WarrentyClaimRequestSearchWebService {
  constructor(
    private httpClient: HttpClient
  ) {}

  searchWcrType(): Observable<WcrDropdown> {
    return this.httpClient.get<BaseDto<WcrDropdown>>(WcrApi.searchWcrType).pipe(map(res => res.result));
  }
  searchFinalStatus(): Observable<WcrFinalstatus> {
    return this.httpClient.get<BaseDto<WcrFinalstatus>>(WcrApi.getFinalStatus).pipe(map(res => res.result));
  }
  searchStatus(): Observable<WcrDropdown> {
    return this.httpClient.get<BaseDto<WcrDropdown>>(WcrApi.searchStatus).pipe(map(res => res.result));
  }
  searchWcr(warrantyWcr: SearchWcr): Observable<BaseDto<Array<SearchWcr>>> {
    return this.httpClient.post<BaseDto<Array<SearchWcr>>>(WcrApi.searchWcr, warrantyWcr);
  }
  updateWcrAsReceived(wcrId){
    return this.httpClient.get(WcrApi.updateWcrAsReceived, {
      params : new HttpParams().set('wcrId', wcrId)
    })
  }

  getInvoiceDetail(wcrId){
    return this.httpClient.get(WcrApi.getInvoiceDetail, {
      params : new HttpParams().set('wcrId', wcrId)
    })
  }
  searchAutoCompleteWcrNo(wcrNo: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(WcrApi.searchAutoCompleteWcrNo, {
      params: new HttpParams().append('wcrNo',wcrNo)
    }).pipe(map(res => res.result))
  }

  downloadWcrExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(WcrApi.downloadWcrExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
  uploadFile(updatedata:any, file:any){
    let formData: FormData = new FormData();

    formData.append('invoiceData', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
    formData.append('attachedFile', file);
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post<any> (WcrApi.invoiceUploadUrl, formData,{headers})
  }
  downloadInvoice(id):Observable<HttpResponse<Blob>>{
    return this.httpClient.get<Blob>(WcrApi.invoiceDownloadUrl,  {
      params : new HttpParams().set("id", id),
      observe: 'response', responseType: 'blob' as 'json'
     })
  }
  kaiInvoiceUpload(updatedata:any, file:any){
    let formData: FormData = new FormData();

    formData.append('invoiceData', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
    formData.append('attachedFile', file);
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post<any> (WcrApi.kaiInvoiceUpload, formData,{headers})
  }
  // updateWcrAsVerified(wcrId){
  //   return this.httpClient.get(WcrApi.invoiceVerifiedUrl, {
  //     params : new HttpParams().set('wcrId', wcrId)
  //   })
  // }

  updateWcrAsVerified(data:object): Observable<any> {
    return this.httpClient.post<any>(WcrApi.invoiceVerifiedUrl, data)
  }
}
