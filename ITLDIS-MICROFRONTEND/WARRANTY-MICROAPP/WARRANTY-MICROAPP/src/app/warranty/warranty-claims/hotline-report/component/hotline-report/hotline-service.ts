 import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';
import { Observable } from 'rxjs';
import { HotlineReportApi } from '../../url-utils/hotline-report-url';
import { AutoCompleteResult, CompleteChassis, Htr, PartDetails, SearchAutocomplete, searchHotline, submitHTR } from '../../domain/hotline-report.domain';

@Injectable()
export class hotlineReport {
    // static readonly dropDownImplCategory = `${PcrApi.apiController}/dropDownImplCategory`;
  constructor(
    private httpClient: HttpClient
  ) { }
  dealerDepoList(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.dealerDepoList)
    .pipe(map(res => res.result))
  }
  failureCodeList(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.condionFailureCode)
    .pipe(map(res => res.result))
  }
  hotlinPlanList(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.hotlinePlant)
    .pipe(map(res => res.result))
  }
  departmentList(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.hoDeparment)
    .pipe(map(res => res.result))
  }
  getDepartmentInchargeByDepartment(deptId: string){
    return this.httpClient.get(HotlineReportApi.deptIncharge,
      {
        params: new HttpParams()
          .append('deptId', deptId)
      })
  }
  statusList(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.hotlineStatus)
    .pipe(map(res => res.result))
  }
  // 
  getFailureType(): Observable<Array<any>>{
    return this.httpClient.get<BaseDto<Array<any>>>(HotlineReportApi.getFailureType)
    .pipe(map(res => res.result))
  }
  // 
  autoCompletePartNumber(partNumber: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(HotlineReportApi.autoCompletePartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
  getPartDetailsByPartNumber(partNumber: string): Observable<PartDetails> {
    return this.httpClient.get<BaseDto<PartDetails>>(HotlineReportApi.getPartDetailsByPartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
  autoCompleteChassis(chassisNo: string): Observable<CompleteChassis> {
    return this.httpClient.get<BaseDto<CompleteChassis>>(HotlineReportApi.chassisNo, {
      params: new HttpParams().set('chassisNo', chassisNo)
    }).pipe(map(res => res.result))
  }
  searchHotlineNo(hotlineNo: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(HotlineReportApi.searchHotlineNo, {
      params: new HttpParams().append('hotlineNo', hotlineNo)
    }).pipe(map(res => res.result))
  }
  searchHotlineReport(holineData: searchHotline): Observable<BaseDto<Array<searchHotline>>> {
    return this.httpClient.post<BaseDto<Array<searchHotline>>>(HotlineReportApi.searchHotlineReport, holineData)
  }
  saveHotlineReport(HtrData:submitHTR,btnname ) {
    let finaldata;
    let formData: FormData = new FormData();
    if(btnname==='Submitted'){
     finaldata=HtrData.warrantyHotlineReport    
    }
   if(btnname==='Answered'){
        finaldata=HtrData
        HtrData.status="Answered"
        // finaldata.vendorResponse=finaldata.abcccc;
        if(finaldata.image){
          finaldata.image.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
        }
    }
    formData.append('hotlineReport', new Blob([JSON.stringify(finaldata)], { type: 'application/json' }))
    if (HtrData.multipartFileList) {
      HtrData.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(HotlineReportApi.submitHotlineReport, formData, {headers});
  }
  viewHotlineReport(hotlineNo: string): Observable<Htr> {
    return this.httpClient.get<BaseDto<Htr>>(HotlineReportApi.viewHotlineReport, {
      params: new HttpParams().append('hotlineNo', hotlineNo)
    }).pipe(map(res => res.result))
  }
  viewPdf(hotlineId:string,hotlineNo:string, printStatus:string){
    return this.httpClient.get<Blob>(HotlineReportApi.printWarrantyHotlineReport, {
        params: new HttpParams()
        .set('hotlineId', hotlineId)
        .set('hotlineNo', hotlineNo)
        .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }

}





