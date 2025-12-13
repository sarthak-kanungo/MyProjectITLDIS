import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { WcrApi } from "../../url-utils/warrenty-claim-request-url";
import { Observable } from "rxjs/Observable";
import { Wcr, WcrSubmit, ViewWcrByWcrNo, WcrDomain } from "../../domain/warrenty-claim-request.domain";
import { BaseDto } from "BaseDto";
import { map } from "rxjs/operators";
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class WarrentyClaimRequestCreatePageService {

    private readonly printWCRUrl = `${environment.baseUrl}${urlService.api}${urlService.warranty}${urlService.reports}/printWCR`;
    
  constructor(private httpClient: HttpClient) {}

  pcrWarrantyClaim(pcrNo: string, id: string, name:string): Observable<WcrDomain> {
    if(name === 'PCR'){  
        return this.httpClient
          .get<BaseDto<WcrDomain>>(WcrApi.pcrWarrantyClaim, {
            params: new HttpParams().append("pcrNo", pcrNo).append("id", id)
          })
          .pipe(map(res => res.result));
    }else{
        return this.httpClient
        .get<BaseDto<WcrDomain>>(WcrApi.goodwillWarrantyClaim, {
          params: new HttpParams().append("goodwillNo", pcrNo).append("id", id)
        })
        .pipe(map(res => res.result));
    }
  }

  viewWarrantyWcr(wcrNo: string): Observable<WcrDomain> {
    return this.httpClient
      .get<BaseDto<WcrDomain>>(WcrApi.viewWarrantyWcr, {
        params: new HttpParams().append("wcrNo", wcrNo)
      })
      .pipe(map(res => res.result));
  }

  saveWcr(wcrDetails: WcrSubmit) {
    return this.httpClient.post(WcrApi.saveWcr, wcrDetails);
  }
  
  approveWcr(wcrDetails:any){
      return this.httpClient.post(WcrApi.approveWcr, wcrDetails);  
  }
  
  printWCR(wcrNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printWCRUrl, {
          params: new HttpParams().set('wcrNo', wcrNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
