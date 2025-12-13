import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { Observable } from 'rxjs/Observable';
import { LogsheetType, AutoCompleteResult, LogSheet, LogSheetJobResult, SubmitLogSheet } from '../../domain/log-sheet.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';

@Injectable()
export class LogSheetWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  dropDownLogsheetType(): Observable<LogsheetType[]> {
    return this.httpClient.get<BaseDto<LogsheetType[]>>(LogSheetApi.dropDownLogsheetType)
    .pipe(map(res => res.result))
  }

  autoCompleteChassisNoInJobCard(chassisNo: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(LogSheetApi.autoCompleteChassisNoInJobCard, {
      params: new HttpParams().set('chassisNo', chassisNo).set('preSalesFlag', 'true').set('isFor','LOGSHEET')
    }).pipe(map(res => res.result))
  }

  getChassisDetailsByChassisNoInJobCard(chassisNo: string): Observable<LogSheet> {
    return this.httpClient.get<BaseDto<LogSheet>>(LogSheetApi.getChassisDetailsByChassisNoInJobCard, {
      params: new HttpParams().set('chassisNo', chassisNo).set('preSalesFlag', 'true').set('jobCardFlag', 'false')
    }).pipe(map(res => res.result))
  }
  getPcrNumberByJobCardId(jobCardId: string): Observable<LogSheet> {
    return this.httpClient.get<BaseDto<LogSheet>>(LogSheetApi.getPcrNumberByJobCardId, {
      params: new HttpParams().set('jobCardId', jobCardId)
    }).pipe(map(res => res.result))
  }

  searchAutoCompleteJobCode(jobCode: string, chassis?:string): Observable<AutoCompleteResult> {
    if(chassis == undefined) chassis = '';
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(LogSheetApi.searchAutoCompleteJobCode, {
      params: new HttpParams().set('jobCode', jobCode).set('type','LOGSHEET').set('chassis', chassis)
    }).pipe(map(res => res.result))
  }

  getJobCardDetails(id: string): Observable<LogSheetJobResult> {
    return this.httpClient.get<BaseDto<LogSheetJobResult>>(`${PcrApi.jobCardForPcr}/${id}`).pipe(map(res => res.result))
  }

 
  saveWarrantyLogsheet(logsheetData: SubmitLogSheet) {
    let formData: FormData = new FormData();
    formData.append('warrantyLogsheet', new Blob([JSON.stringify(logsheetData.warrantyLogsheet)], { type: 'application/json' }))
    if (logsheetData.multipartFileList) {
      logsheetData.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(LogSheetApi.saveWarrantyLogsheet, formData, {headers});
  }




}
