import { Injectable } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ActivityReportEnquiryDetails } from 'ActualReportModule';

@Injectable()
export class EnquiryDetailsService {
  private readonly enquiryDetailsFromActivityNumber = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingactivityreport}/getActivityReportEnquiryDetails`
  private readonly marketingActivityProposalSearch = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/marketingActivityProposalSearch`
  constructor(
    private http: HttpClient
  ) { }

  getEnquiryDeatilsFromActivityNumber(activityProposalId,fromDate,toDate): Observable<BaseDto<Array<ActivityReportEnquiryDetails>>> {
    return this.http.get<BaseDto<Array<ActivityReportEnquiryDetails>>>(this.enquiryDetailsFromActivityNumber, {
      params: {
        'activityProposalId': activityProposalId,
        'fromDate': fromDate,
        'toDate': toDate
      } 
    })
  }
  
}
