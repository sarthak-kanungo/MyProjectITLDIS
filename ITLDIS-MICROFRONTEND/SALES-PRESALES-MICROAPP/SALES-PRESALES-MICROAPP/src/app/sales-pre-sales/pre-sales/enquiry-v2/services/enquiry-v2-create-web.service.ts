import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { ViewEnquiry, FollowupHistory } from '../domains/enquiry';
import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

@Injectable()
export class EnquiryV2CreateWebService {

  private readonly followupHistoryeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getFollowUpHistory}`
  private readonly printEnquiryUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}${urlService.getEnquiryPrint}`
  constructor(
    private http : HttpClient
  ) { }

  postSaveEnquiry(formData) {
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.http.post(EnquiryApi.addEnquiry, formData, {
      headers: headers
    })
   }

   getEnquiryByEnquiryNumber(enquiryNumber: string): Observable<ViewEnquiry> {
    return this.http.get<BaseDto<ViewEnquiry>>(EnquiryApi.getEnquiryByEnquiryNumber,
      {
        params: new HttpParams()
            .append('enquiryNumber', enquiryNumber)
    }).pipe(map(dto => dto.result))
  }

  updateEnquiry(formData) {
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.http.put(EnquiryApi.updateEnquiry, formData, {
      headers: headers
    })
   }

   followupHistorye(enquiryNumber: string):Observable<BaseDto<Array<FollowupHistory>>>  {
    return this.http.get<BaseDto<Array<FollowupHistory>>> (`${this.followupHistoryeURL}`, {
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    })
  }
   printEnquiry(enquiryNumber: string, printStatus:string) {
       return this.http.get<Blob>(this.printEnquiryUrl, {
           params: new HttpParams().set('enquiryNumber', enquiryNumber)
                                   .set('printStatus', printStatus),
          observe: 'response', responseType: 'blob' as 'json'                         
       });
   }

   deleteAttachment(id:string) : Observable<BaseDto<Object>>{
     return this.http.delete<BaseDto<Object>>(EnquiryApi.deleteAttachment, {
       params: new HttpParams().set('id', id)
     });
   }

}
