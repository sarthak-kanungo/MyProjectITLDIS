import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ManageApprovalService {

  private readonly getFinalApprovalList=`${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/getIfFinalApprover`;
  private readonly autoSearchTransType= `${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/autoGetTransName`;
  private readonly searchtranHierManagementSearch=`${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/tranHierManagementSearch`
  private readonly viewtranHierManagementView=`${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/tranHierManagementView` 
  private readonly updateApproval=`${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/updateTranHierSeq`;

private readonly hoDesignationLevel=`${environment.baseUrl}${urlService.api}${urlService.master}/kaicommonmaster/mngmtHierarchy/autoGetHoDesigLevel`
  constructor(private httpClient:HttpClient) {

   }

   getTransactionType(transName:string):Observable<any>{
    return this.httpClient.get(this.autoSearchTransType, {
      params: new HttpParams()
          .append('transName', transName)
  })
   }

   gethoDesignationLevel(desigStr:string){
    return this.httpClient.get(this.hoDesignationLevel, {
      params: new HttpParams()
          .append('desigStr', desigStr)
  })
   }
   getFinalApproval():Observable<any>{
    return this.httpClient.get(this.getFinalApprovalList);
   }

   searchApprovalData(searchData:any){
    return this.httpClient.post(this.searchtranHierManagementSearch,searchData);
   }

   viewTransType(transName:string){
    return this.httpClient.get(this.viewtranHierManagementView, {
      params: new HttpParams()
          .append('transName', transName)
  })
   }

   updateApprovalsData(approvedData:any){
    return this.httpClient.post(this.updateApproval,approvedData);
   }
}
