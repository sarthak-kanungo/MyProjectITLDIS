import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceClaimInvoiceSearchServiceService {
  
  static readonly invoiceSearchUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/getDealerInvoiceList`
  static readonly invoiceNumberSearchUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/dealerInvoiceNumberSearch`
  static readonly claimNumberSearchUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/claimNumberSearch`
  static readonly activityNumberSearchUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/activityNumberSearch`
  
  static readonly invoiceUploadUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/invoiceUpload`
  static readonly dealerInvoiceUpload = `${environment.baseUrl}/${environment.api}/service/activityClaim/dealerInvoiceUpload`
  static readonly invoiceVerifiedUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/invoiceVerification`
  static readonly invoiceDownloadUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/invoiceDownload`
  static readonly getInvoiceDetail = `${environment.baseUrl}/${environment.api}/service/activityClaim/getInvoiceDetail`
  static readonly kaiInvoiceVerify=`${environment.baseUrl}/${environment.api}/service/activityClaim/kaiInvoiceVerify`
  invoiceNoData: any;
  invoiceDateData: any;
  invoicedjdj: any;
  constructor(private httpClient : HttpClient) {
   
   }

  getDealerInvoiceList(searchObj){
    return this.httpClient.post(ServiceClaimInvoiceSearchServiceService.invoiceSearchUrl, searchObj);
  }
  getInvoiceList(searchVal, invoiceType){
    return this.httpClient.get(ServiceClaimInvoiceSearchServiceService.invoiceNumberSearchUrl, {
      params : new HttpParams().set('searchVal',searchVal).set('invoiceType',invoiceType)
    });
  }
  getClaimList(searchVal, invoiceType){
    return this.httpClient.get(ServiceClaimInvoiceSearchServiceService.claimNumberSearchUrl, {
      params : new HttpParams().set('searchVal',searchVal).set('invoiceType',invoiceType)
    });
  }
  getActivityList(searchVal, invoiceType){
    return this.httpClient.get(ServiceClaimInvoiceSearchServiceService.activityNumberSearchUrl, {
      params : new HttpParams().set('searchVal',searchVal).set('invoiceType',invoiceType)
    });
  }
  // updateAsVerified(invoiceID){
  //   return this.httpClient.get(ServiceClaimInvoiceSearchServiceService.invoiceVerifiedUrl, {
  //     params : new HttpParams().set('invoiceId', invoiceID)
  //   })
  // }
  kaiVerifyInvoice(data:object): Observable<any> {
    return this.httpClient.post<any>(ServiceClaimInvoiceSearchServiceService.kaiInvoiceVerify, data)
  }
  getInvoiceDetail(invoiceID){
    return this.httpClient.get(ServiceClaimInvoiceSearchServiceService.getInvoiceDetail, {
      params : new HttpParams().set('invoiceId', invoiceID)
    })
  }
  uploadFile(updatedata:any, file:any){
      let formData: FormData = new FormData();

      formData.append('invoiceData', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
      formData.append('attachedFile', file);
      
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');
      return this.httpClient.post<any> (ServiceClaimInvoiceSearchServiceService.invoiceUploadUrl, formData,{headers})
  }
  // 
  uploadFileForDealer(updatedata:any, file:any){
    let formData: FormData = new FormData();

    formData.append('invoiceData', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
    formData.append('attachedFile', file);
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post<any> (ServiceClaimInvoiceSearchServiceService.dealerInvoiceUpload, formData,{headers})
}
  downloadInvoice(id):Observable<HttpResponse<Blob>>{
    return this.httpClient.get<Blob>(ServiceClaimInvoiceSearchServiceService.invoiceDownloadUrl,  {
      params : new HttpParams().set("id", id),
      observe: 'response', responseType: 'blob' as 'json'
     })
  }
  private sharedDataSubject = new BehaviorSubject<any>(null);
  sharedData$ = this.sharedDataSubject.asObservable();

  private sharedDatasSubject = new BehaviorSubject<any>(null);
  sharedDatas$ = this.sharedDatasSubject.asObservable();

  setInvoiceNo(data: any) {
    debugger
   console.log(data,'set')
    this.sharedDataSubject.next(data);
    this.invoicedjdj=data
    this.getInvoiceNo()
  }

  getInvoiceNumber(){
    console.log(this.invoicedjdj,'this.invoicedjdj')
    return  this.invoicedjdj
  }

  setInvoiceDate(data: any) {
    this.sharedDatasSubject.next(data);
  }

  getInvoiceNo(): any {
    // debugger
    // console.log(this.sharedDataSubject.value,'d')
    // return this.sharedDataSubject.getValue();
   return  this.sharedDataSubject.value
  }

  getInvoiceDate(): any {
    return this.sharedDatasSubject.getValue();
  }




}
