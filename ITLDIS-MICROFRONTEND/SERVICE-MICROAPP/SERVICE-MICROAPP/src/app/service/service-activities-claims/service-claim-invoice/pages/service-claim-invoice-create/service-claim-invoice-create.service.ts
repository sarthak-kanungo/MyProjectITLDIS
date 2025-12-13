import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceClaimInvoiceCreateService {
  
  static readonly getInvoiceDetailsUrl = `${environment.baseUrl}/${environment.api}/service/activityClaim/viewDealerInvoice`
  static readonly printUrl = `${environment.baseUrl}/${environment.api}/service/reports/printDealerInvoice`
 
  viewInvoiceSubject:BehaviorSubject<any> = new BehaviorSubject(null);
  constructor(private httpClient : HttpClient) { }

  getInvoiceDetails(id){
    return this.httpClient.get(ServiceClaimInvoiceCreateService.getInvoiceDetailsUrl, {
      params : new HttpParams().set('invoiceId', id)
    })
  }

  print(id, printStatus, invoiceNo){
    
    return this.httpClient.get<Blob>(ServiceClaimInvoiceCreateService.printUrl, {
        params: new HttpParams().set('id', id)
                                .set('invoiceNo', invoiceNo)
                                .set('printStatus', printStatus),
        observe: 'response', responseType: 'blob' as 'json'                         
    }); 
  }

}
