import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {PcrApi} from '../../url-utils/product-concern-report.urls';
import {SubmitPCR, SubmitUpdatePcr, ViewPcr, PCRDomain, ApproveQuantity, JobCardHistory} from '../../domain/product-concern-report.domain';
import { Observable } from 'rxjs/Observable';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class PcrPageWebService {
  private readonly printPCRUrl = `${environment.baseUrl}${urlService.api}${urlService.warranty}${urlService.reports}/printPCR`;
  managementApprovalCheckSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  
  constructor(
    private httpClient: HttpClient
  ) { }

  saveWarrantyPcr(pcrDetails: SubmitPCR) {
    const formData: FormData = new FormData();
    formData.append('warrantyPcr', new Blob([JSON.stringify(pcrDetails.warrantyPcr)], { type: 'application/json' }))
    console.log('pcrDetails.multipartFileList', pcrDetails.multipartFileList)
    if (pcrDetails.multipartFileList) {
      pcrDetails.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    } else {
      pcrDetails.multipartFileList = null;
    }
    const headers = new HttpHeaders();
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.httpClient.post(PcrApi.saveWarrantyPcr, formData, {headers});
  }

  updatePcr(updatePcrDetails: SubmitUpdatePcr) {
    
    // debugger
    const formData: FormData = new FormData();
    formData.append('warrantyPcr', new Blob([JSON.stringify(updatePcrDetails.warrantyPcr)], { type: 'application/json' }))
    console.log('updatePcrDetails.multipartFileList', updatePcrDetails.multipartFileList)
    if (updatePcrDetails.multipartFileList) {
      updatePcrDetails.multipartFileList.forEach(element => {
        console.log('multipartFileList--',element);
        
            formData.append('multipartFileList', element['file'])
      });
    } else {
      updatePcrDetails.multipartFileList = null;
    }
    if (updatePcrDetails.multipartVideoList) {
      updatePcrDetails.multipartVideoList.forEach(element => {
        console.log('multipartVideoList--',element);
        
            formData.append('multipartVideoList', element['file'])
      });
    } else {
      updatePcrDetails.multipartVideoList = null;
    }
    const headers = new HttpHeaders();
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.httpClient.post(PcrApi.updatePcr, formData, {headers});
  }

  warrantyPcrView(pcrNo: string): Observable<ViewPcr> {
    return this.httpClient.get<BaseDto<ViewPcr>>(PcrApi.warrantyPcrView, {
      params: new HttpParams().append('pcrNo', pcrNo)
    }).pipe(map(res => res.result))
  }
  
  warrantyPcrGoodwill(pcrNo: string): Observable<ViewPcr> {
      return this.httpClient.get<BaseDto<ViewPcr>>(PcrApi.warrantyPcrGoodwill, {
        params: new HttpParams().append('pcrNo', pcrNo)
      }).pipe(map(res => res.result))
    }
  
  updatePcrFromJobcard(pcrNo: string): Observable<ViewPcr> {
    return this.httpClient.get<BaseDto<ViewPcr>>(PcrApi.updatePcrFromJobcard, {
      params: new HttpParams().append('pcrNo', pcrNo)
    }).pipe(map(res => res.result))
  }

  getPCRDetails(id: string): Observable<PCRDomain> {
    return this.httpClient.get<BaseDto<PCRDomain>>(`${PcrApi.jobCardForPcr}/${id}`).pipe(map(res => res.result));
  }

  approveWarrantyPcr(approveData: ApproveQuantity) {
    return this.httpClient.post(PcrApi.approveWarrantyPcr, approveData);
  }

  getSystemDateUrl(): Observable<string> {
    return this.httpClient.get<BaseDto<string>>(PcrApi.getSystemDateUrl).pipe(map(res => res.result))
    
  }

  serviceHistory(id: number): Observable<JobCardHistory[]> {
    return this.httpClient.get<BaseDto<JobCardHistory[]>>(`${PcrApi.serviceHistory}/${id}`).pipe(map(res => res.result));
  }
  
  enablePcrForGoowill(id: number, enableType:string){
      return this.httpClient.get(`${PcrApi.enableGoodwill}/${id}`,{
          params : new HttpParams().append('enableType',enableType)
      });
  }
  

  printPCR(pcrNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printPCRUrl, {
          params: new HttpParams().set('pcrNo', pcrNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }

  getRejectionReasonLov(){
    return this,this.httpClient.get(PcrApi.syslookupByCode, {
      params : new HttpParams().set('code','PCR_REJECT_REASON')
    })
  }
  //
}
