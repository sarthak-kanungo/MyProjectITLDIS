import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { FollowupHistoryDomain } from 'FolloupHistory';

@Injectable()
export class FollowupHistoryService {
  private readonly followupHistoryeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getFollowUpHistory}`
 // http://192.168.15.109:8383/api/salesandpresales/enquiry/getFollowUpHistory?enquiryNumber=ENQ%2FC7000%2F2019%2F0131
  constructor(
    private http : HttpClient
  ) { }

  followupHistorye(enquiryNumber: string):Observable<BaseDto<Array<FollowupHistoryDomain>>>  {
    return this.http.get<BaseDto<Array<FollowupHistoryDomain>>> (`${this.followupHistoryeURL}`, {
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    })
  }
}
