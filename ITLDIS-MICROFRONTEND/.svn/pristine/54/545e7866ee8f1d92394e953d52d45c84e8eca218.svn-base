import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { BOCancellation } from '../url-utils.ts/back-order-cancel-url';
import { SaveBOCData } from '../component/create-back-order-cancellation-page/back-order-cancellation-domain';
import { searchBackOrderCancellation } from '../component/search-back-order-cancellation/search-block-order-cancellation-domain';

@Injectable({
  providedIn: 'root'
})
export class BackOrderService {
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  constructor(private http:HttpClient) { }

  getDealerCodeList( dealerCode: string ) {
    return this.http.get( `${this.dealerCodeUrl}`, {
        params: new HttpParams().set( 'dealerCode', dealerCode )
    } )
}

autoGenerateBOC(bocNo: string): Observable<any> {
  return this.http.get<BaseDto<any>>(BOCancellation.autoGetBOCNo, {
    params: new HttpParams().set('bocNo', bocNo)
  }).pipe(map(res => res.result))
}
 searchDealercode(dealerCode: string): Observable<any> {
  return this.http.get<BaseDto<any>>(BOCancellation.getDealerCode, {
    params: new HttpParams().set('dealerCode', dealerCode)
  }).pipe(map(res => res.result))
}

downloadBackOrderCancellationReport(bocId:string,bocNo:string, printStatus:string){
  return this.http.get<Blob>(BOCancellation.printPDf, {
      params: new HttpParams()
      .set('bocId', bocId)
      .set('bocNo', bocNo)
      .set('printStatus', printStatus),
     observe: 'response', responseType: 'blob' as 'json'                         
  });
}
saveBackOrderCancellation(saveBoc:SaveBOCData) {
  return this.http.post<BaseDto<any>>(BOCancellation.saveBOC,saveBoc);
}
getViewDetailsforBOC(bocNo: string): Observable<any> {
  return this.http.get<BaseDto<any>>(BOCancellation.viewBackOrderCancellation, {
    params: new HttpParams().append('bocNo', bocNo)
  }).pipe(map(res => res.result))
}
searchBOC(backOrderData: searchBackOrderCancellation): Observable<BaseDto<Array<searchBackOrderCancellation>>> {
  return this.http.post<BaseDto<Array<searchBackOrderCancellation>>>(BOCancellation.searchBackOrderCancellation, backOrderData)
}
searchBOCApproval(backOrderData: searchBackOrderCancellation): Observable<BaseDto<Array<searchBackOrderCancellation>>> {
  return this.http.post<BaseDto<Array<searchBackOrderCancellation>>>(BOCancellation.searchBOCApproval, backOrderData)
}
getItemDetailsFromDealer(dealerCode: string): Observable<any> {
  return this.http.get<BaseDto<any>>(BOCancellation.getBOCItemDetails, {
    params: new HttpParams().set('dealerCode', dealerCode)
  }).pipe(map(res => res.result))
}
autoCompleteDealerCode(dealerCode: string): Observable<any> {
  return this.http.get<BaseDto<any>>(BOCancellation.autoCompleteDealerCode, {
    params: new HttpParams().set('dealerCode', dealerCode)
  }).pipe(map(res => res.result))
}

approveBOC(saveBoc:SaveBOCData) {
  return this.http.post<BaseDto<any>>(BOCancellation.boCancellationApproval,saveBoc);
}
}
