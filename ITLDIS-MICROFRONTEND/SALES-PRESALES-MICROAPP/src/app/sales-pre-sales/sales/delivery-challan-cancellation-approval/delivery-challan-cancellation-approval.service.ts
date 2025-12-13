import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { urlService } from '../../../webservice-config/baseurl';

@Injectable({
  providedIn: 'root'
})
export class DeliveryChallanCancellationApprovalService {
  private readonly deliveryChallanCancellationApprovalUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.deliveryChallanCancellationApproval}`; 
  constructor(
          private httpClient: HttpClient
     ) { }
  
  public getDeliveryPendingListForApproval(page:number,size:number) {
      return this.httpClient.get(this.deliveryChallanCancellationApprovalUrl,
              {
                  params: new HttpParams().set('page', page+'')
                                  .set('size', size+'')
              }
       );
   } 
}
