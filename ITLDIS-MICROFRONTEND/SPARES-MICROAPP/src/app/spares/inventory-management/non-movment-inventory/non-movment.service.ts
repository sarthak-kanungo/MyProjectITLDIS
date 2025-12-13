import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class NonMovmentService {
  
   private  readonly nonMovSearch=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/nonMovInvSearch`;
   private  readonly saveNonMovInventory=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/saveNonMovInventory`
   private readonly autoSearchPartNumber=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/autoGetNonMovSpareItems`
   private readonly getAgingType=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/getAgingType`
   public readonly dealerAuctionPartList=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/auctionPartsList`
   public readonly nonMovInvSearchForHo=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/nonMovInvSearchForHo`
   public readonly downloadExcel=`${environment.baseUrl}${urlService.api}/spares/nonMovInv/nonMovInvSearchForHoReportExcel`
  constructor(
    private http:HttpClient,
  ) { }


   dropdownAginType(){
    return this.http.get(this.getAgingType);
  }
  // getNonMovSearch(searchData:any):Observable<any>{
  //   return this.http.post(this.nonMovSearch,searchData)
  // }
  getautoSearchPartNumber(itemStr:string):Observable<any>{
    return this.http.get<any>(this.autoSearchPartNumber, {
      params: new HttpParams().append('itemStr', itemStr)
    }).pipe(map(res=>res.result)) 
  }
  searchApprovalData(searchData:any){
    return this.http.post(this.nonMovSearch,searchData);
   }

   submitNonMov(submitData:any):Observable<any>{
   return this.http.post(this.saveNonMovInventory,submitData);
   }

   getAcutionList(searchData:any){
    return this.http.post(this.dealerAuctionPartList,searchData);
   }

   getnonMovInvSearchForHo(searchDataForkai:any){
    return this.http.post(this.nonMovInvSearchForHo,searchDataForkai)
   }

   downloadExcelForHo(downloadReport: any): Observable<HttpResponse<Blob>> {
    return this.http.post<Blob>(this.downloadExcel, downloadReport, {
      observe: 'response',
      responseType: 'blob' as 'json'
    });
  }
}
