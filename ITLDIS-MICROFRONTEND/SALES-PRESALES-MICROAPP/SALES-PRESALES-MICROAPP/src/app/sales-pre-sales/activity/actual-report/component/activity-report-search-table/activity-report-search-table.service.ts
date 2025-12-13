import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FilterSearchReport, SearchActivityReport } from 'ActualReportModule';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class ActivityReportSearchTableService {
  private readonly branchUrl = `${environment.baseUrl}${urlService.api}/service/report/getBranchesUnderUser`
  private readonly activityTypeUrl=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchactivitytype}`
  private readonly activityNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getActivityProposalNumberForSearch`
  private readonly getSearchDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}${urlService.searchBy}`

  constructor(private httpClient: HttpClient) { }

  
  getActivityType(){
    return this.httpClient.get(this.activityTypeUrl)
  }

  getActivityNo(activityNumber: string) {
    return this.httpClient.get(`${this.activityNoUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
    })
  }

  getSearchData(sendData: FilterSearchReport): Observable<BaseDto<Array<SearchActivityReport>>> {
    return this.httpClient.post<BaseDto<Array<SearchActivityReport>>>(this.getSearchDataUrl, sendData)
  }

  getBranchCodeList(){
    return this.httpClient.get(this.branchUrl)
  }
}
