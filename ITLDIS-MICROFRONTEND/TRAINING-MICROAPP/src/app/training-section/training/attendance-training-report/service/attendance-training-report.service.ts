import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { TrainingApi } from '../url-utils/attendance-training-report-url';
import { FieldLevel } from '../domain/attendance-training-report.domain';
@Injectable({
  providedIn: 'root'
})
export class TrainingattendanceTrainingReportService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  getattendanceDataforViewEdit(): Observable<any>{
    return
  }


  getTrainingZoneAndRegion(orgLevel: any, orghierId:any):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getTrainingZoneRegion,{
      params: new HttpParams().append('levelId',orgLevel).set('orghierId',orghierId)
    })                       ;
  }

  getTsmNameList(name:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getTSMName,{
      params: new HttpParams().append('employeeName',name)
    })                       ;
  }

  getDesignationList(name:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getDealerDesignation,{
      params: new HttpParams().append('deptCodeAndName',name)
    })                       ;
  }

  reportSearchSearch(data:any){
    return this.httpClient.post<any> (TrainingApi.trainingReportSearch,data)
  }

  downloadExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(TrainingApi.downloadTrainingReportExcel, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
