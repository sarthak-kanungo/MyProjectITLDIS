import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { KaiInvoiceNumber, ChassisNumber, } from '../../domain/machine-receipt-checking.domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class MrcCreateWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService,
  ) { }


  getKaiInvoiceNumber(invoiceNumber: string): Observable<Array<KaiInvoiceNumber>> {
    return this.httpClient.get<BaseDto<Array<KaiInvoiceNumber>>>(MrcUrl.kaiInvoiceAuto,
      {
        params: new HttpParams()
          .append('invoiceNumber', invoiceNumber)
      }).pipe(map(dto => dto.result));
  }
  getChassisNumber(accpacInvoiceId: string): Observable<BaseDto<Array<ChassisNumber>>> {
    return this.httpClient.get<BaseDto<Array<ChassisNumber>>>(MrcUrl.chassisNumberAuto,
      {
        params: new HttpParams()
          .append('accpacInvoiceId', accpacInvoiceId)
      })
  }
  getCheckChassisNumber(chassisNumberId, accpacInvoiceId) {
    return this.httpClient.get(MrcUrl.checkChassisNumber, {
      params: new HttpParams().set('chassisNumberId', chassisNumberId)
        .set('accpacInvoiceId', accpacInvoiceId)
    })
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }
}
