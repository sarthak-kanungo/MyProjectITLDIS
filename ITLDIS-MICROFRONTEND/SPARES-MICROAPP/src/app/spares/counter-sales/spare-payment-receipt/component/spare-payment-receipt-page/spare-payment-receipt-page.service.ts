import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AddPaymentReceiptData } from 'SparePaymentReceiptModule';
import { SparesPaymentReceiptApi } from '../../url-utils/spare-payment-receipt-urls';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class SparePaymentReceiptPageService {
    private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}`;
  constructor(
    private httpClient: HttpClient
  ) { }

  public saveSparesPaymentReceipt(receiptObject: AddPaymentReceiptData): Observable<BaseDto<object>> {
    return this.httpClient.post<BaseDto<object>>(SparesPaymentReceiptApi.savePaymentReceiptUrl, receiptObject)
  }
  public getPaymentReceiptById(receiptId: number): Observable<BaseDto<object>> {
    return this.httpClient.get<BaseDto<object>>(`${SparesPaymentReceiptApi.getSparePaymentReceiptByIdUrl}/${receiptId}`)
  }
  print(receiptNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printUrl+"/printPaymentReceipt", {
          params: new HttpParams().set('receiptNumber', receiptNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
