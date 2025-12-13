import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SearchWcr, WcrFinalstatus } from '../warrenty-claim-request/domain/warrenty-claim-request.domain';
import { WcrApi } from '../warrenty-claim-request/url-utils/warrenty-claim-request-url';

@Injectable()
export class WcrPartsStatusService {


  constructor(private httpClient: HttpClient) {

   }

   getWcrForStatusChange():Observable<BaseDto<Array<SearchWcr>>> {
    return this.httpClient.get<BaseDto<Array<SearchWcr>>>(WcrApi.searchWcrCreditIssued);
  }

   updateStatus(data){
    return this.httpClient.post(WcrApi.wcrFinalStatusUpdate,data);
   }

   searchFinalStatus(): Observable<WcrFinalstatus> {
    return this.httpClient.get<BaseDto<WcrFinalstatus>>(WcrApi.getFinalStatus).pipe(map(res => res.result));
  }
}
