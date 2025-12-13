import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { spareDescripancy } from '../url-utils/spare-descripancy-url';
import { submitSpareDescripancyClaim } from './spare-claim-domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SpareClaimService {

  constructor(
    private httpClient:HttpClient,
  ) { 

  }

  getClaimType(){
    return this.httpClient.get(spareDescripancy.claimType);
  }

  autoSearchGrn(grnStr:any,descrClaimType:any){
    return this.httpClient.get(spareDescripancy.autoSearchGrnNo,{
      params: new HttpParams().set('grnStr', grnStr).set('descrClaimType',descrClaimType)
    })
  }

  autoSearchClaimNo(claimNoString:string){
    return this.httpClient.get(spareDescripancy.autoseachClaimNo,{
      params: new HttpParams().set('claimNoString', claimNoString)
    })
  }
  // 
  getStatus(){
    return this.httpClient.get(spareDescripancy.getClaimStatus);
  }

  getAutoSearchItemNo(grnId:string,itemStr:string){
    return this.httpClient.get(spareDescripancy.autoSearchItemNo,{
      params:new HttpParams().set('grnId',grnId).set('itemStr',itemStr)
    })
  }

  getPartDetails(grnId:string,itemNo:string){
    return this.httpClient.get(spareDescripancy.getItemDetails,{
      params:new HttpParams().set('grnId',grnId).set('itemNo',itemNo)
    })
  }

  getGrnDetails(grnId:any){
    return this.httpClient.get(spareDescripancy.grnDetails,{
      params:new HttpParams().set('grnId',grnId)
    })
  }

  saveSpareDescripancyClaim(saveData:submitSpareDescripancyClaim):Observable<any>{
    let formData: FormData = new FormData();
    formData.append('discrepancyClaim', new Blob([JSON.stringify(saveData.discrepancyClaim)], { type: 'application/json' }))
    if (saveData.multipartFileList) {
      saveData.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(spareDescripancy.createDescripancyClaim, formData, {headers});
  }

  searchSpareClaim(searchData:any){
    return this.httpClient.post(spareDescripancy.searchSpareClaim,searchData);
  }

  approveOrReject(approveData:any){
    return this.httpClient.post(spareDescripancy.approveorReject,approveData)
  }

  // view

  viewSpareDescripancy(discrClaimNo: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(spareDescripancy.viewSpareClaimDescripancy, {
      params: new HttpParams().append('discrClaimNo', discrClaimNo)
    }).pipe(map(res => res.result))
  }

  printSpareDescClaim(downloadData:any):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      // Add any other headers if needed
    });

    return this.httpClient.post<Blob>(
      spareDescripancy.printSpareDescripancyClaim,
      downloadData,
      {
        headers: headers,
        observe: 'response',
        responseType: 'blob' as 'json'
      }
    );
    // return this.httpClient.post<Blob>(spareDescripancy.printSpareDescripancyClaim,{
    //  observe: 'response', responseType: 'blob' as 'json'
    // }
    // );
  }

}
