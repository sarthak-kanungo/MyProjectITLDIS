import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparesSalesInvoiceApi } from '../../url-utils/spares-sales-invoice-api';
import { SparePoApi } from '../../../../spares-purchase/spares-purchase-order/url-utils/spares-purchse-order-urls';

import { BaseDto } from 'BaseDto';
import { DateService } from '../../../../../root-service/date.service';
import { SparesSalesInvoice } from '../../domain/spares-sales-invoice.domain';

@Injectable()
export class SparesSalesInvoiceWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }
  public tcsPercList() : Observable<any>{
      return this.httpClient.get(SparePoApi.apiController+"/tcsPercList");
  }
  public getModesOfTransport(): Observable<BaseDto<Array<object>>> {
    return this.httpClient.get<BaseDto<Array<object>>>(SparePoApi.getTransferModeDropdown)
  }
  public getTransporter(): Observable<BaseDto<Array<object>>> {
    return this.httpClient.get<BaseDto<Array<object>>>(SparePoApi.getTransporterDropdown)
  }
  public getSalesTypeDropdown(): Observable<BaseDto<Array<object>>> {
    return this.httpClient.get<BaseDto<Array<object>>>(SparePoApi.getSalesTypeDropdown)
  }
  public getReferenceDocumentsType(): Observable<BaseDto<Array<object>>> {
    return this.httpClient.get<BaseDto<Array<object>>>(SparesSalesInvoiceApi.getReferenceDocumentsTypes)
  }
  public getSalesOrderAndJobCardAutoData(value: string, documentType: string): Observable<Array<object>> {
    return this.httpClient.get<BaseDto<Array<object>>>(SparesSalesInvoiceApi.documentAutoComplete, {
      params: new HttpParams().set('saleOrderNo', value).set('documentType', documentType)
    }).pipe(map((res: BaseDto<Array<object>>) => res.result))
  }
  public getSaleOrderDetailById(documentId: number): Observable<BaseDto<SparesSalesInvoice>> {
    return this.httpClient.get<BaseDto<SparesSalesInvoice>>(`${SparesSalesInvoiceApi.getReferenceDocument}/${documentId}`)
  }
  public getJobCardetailById(documentId: number): Observable<BaseDto<SparesSalesInvoice>> {
    return this.httpClient.get<BaseDto<SparesSalesInvoice>>(`${SparesSalesInvoiceApi.getJobCardDetails}`, {
      params: new HttpParams().set('jobCardId', documentId.toString())
    })
  }
  public getWcrDetailsById(documentId: number, claimType:string): Observable<BaseDto<SparesSalesInvoice>> {
    return this.httpClient.get<BaseDto<SparesSalesInvoice>>(`${SparesSalesInvoiceApi.getWcrDetails}`, {
        params: new HttpParams().set('wcrId', documentId.toString())
        .set('claimType', claimType)
    })
  }
  public getServerDate(): Observable<BaseDto<string>> {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }
}
