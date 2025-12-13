import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { SubmitServiceActivityReport, ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityReportCreatePageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  saveServiceActivityReport(activityReport : SubmitServiceActivityReport){

    let formData: FormData = new FormData();
    formData.append('serviceActivityReport', new Blob([JSON.stringify(activityReport.serviceActivityReport)], { type: 'application/json' }))
    if (activityReport.multipartFileList) {
      activityReport.multipartFileList.forEach(element => {
        formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');

    return this.httpClient.post(ServiceActivityReportApi.saveServiceActivityReport, formData, { headers })
  }

  getActivityReportById(id: string): Observable<ViewServiceActivityReport> {
    return this.httpClient.get<BaseDto<ViewServiceActivityReport>>(`${ServiceActivityReportApi.getActivityReportById}/${id}`
     ).pipe(map(dto => dto.result))
  }
  
 printPCR(activityReportId:string,activityReportNo:string, printStatus:string){
  return this.httpClient.get<Blob>(ServiceActivityReportApi.printServiceActivityReport, {
      params: new HttpParams()
      .set('activityReportId', activityReportId)
      .set('activityReportNo', activityReportNo)
      .set('printStatus', printStatus),
     observe: 'response', responseType: 'blob' as 'json'                         
  });
}
}
