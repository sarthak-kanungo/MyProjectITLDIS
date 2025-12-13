import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { ViewActualReport, SubmitActivityReport } from 'ActualReportModule';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';

@Injectable()
export class ActivityReportCreateService {
  private actualReportFrom: FormGroup
  private readonly activityTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquirySource}${urlService.getSource}`
  private readonly getActivityNumberForReport = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}/getActivityNumberForReport`
  private readonly getActivityReportHeaderData = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}/getActivityReportHeaderData`
  private readonly submitPost = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}/saveMarketingActivityReport`
  private readonly viewReportUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}${urlService.activityReportById}`
  private readonly printActivityReportUrl =  `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}/getActivityReportPrint`   
      
  constructor(private fb: FormBuilder, private httpClient: HttpClient) { }

  createActivityReportForm() {
    this.actualReportFrom = this.fb.group({
      activityNumber: ['',Validators.required],
      activityCreationDate: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      actualFromDate: ['', Validators.compose([Validators.required])],
      actualToDate: ['', Validators.compose([Validators.required])],
      totalEnquiries: [{ value: '', disabled: true }],
      totalRetails: [{ value: '', disabled: true }],
      totalBookings: [{ value: '', disabled: true }],
    })
    return this.actualReportFrom;
  }

  viewActivityReportForm() {
    this.actualReportFrom = this.fb.group({
      activityNumber: [{ value: '', disabled: true }],
      activityCreationDate: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      actualFromDate: [{ value: '', disabled: true }],
      actualToDate: [{ value: '', disabled: true }],
      totalEnquiries: [{ value: '', disabled: true }],
      enquiriesPending: [{ value: '', disabled: true }],
      totalRetails: [{ value: '', disabled: true }],
      totalBookings: [{ value: '', disabled: true }],
    })
    return this.actualReportFrom;
  }

  getActivityType() {
    return this.httpClient.get(this.activityTypeUrl)
  }
  getActivityNo(activityNumber) {
    return this.httpClient.get(`${this.getActivityNumberForReport}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
    })
  }
  getActivityReportHeaderDetails(activityProposalId) {
    return this.httpClient.get(`${this.getActivityReportHeaderData}`, {
      params: new HttpParams().set('activityProposalId', activityProposalId)
    })
  }

  postReport(domain: SubmitActivityReport){
      
    let formData: FormData = new FormData();
    formData.append('marketingActivityReport', new Blob([JSON.stringify(domain.marketingActivityReport)], { type: 'application/json' }))
    domain.multipartFileList.forEach(markImg => {
      formData.append('multipartFileList', markImg['file'])
    })
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');

    return this.httpClient.post(this.submitPost, formData, {
      headers: headers
    })
  }

  getActivityReportById(id: string): Observable<BaseDto<ViewActualReport>> {
    return this.httpClient.get<BaseDto<ViewActualReport>>(`${this.viewReportUrl}/${id}`)
  }
  
  
  printActivityReport(activityNumber: string, printStatus:string) {
      return this.httpClient.get<Blob>(this.printActivityReportUrl, {
          params: new HttpParams().set('activityReportNumber', activityNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
