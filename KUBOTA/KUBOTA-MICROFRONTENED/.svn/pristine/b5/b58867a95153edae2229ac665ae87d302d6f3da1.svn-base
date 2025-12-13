import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient ,HttpParams} from '@angular/common/http';
import { SparesSalesInvoice,InvoiceDetails } from '../../domain/spares-sales-invoice.domain';
import { SparesSalesInvoiceApi } from '../../url-utils/spares-sales-invoice-api';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class SparesSalesInvoiceCreatePageWebService {
    private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}`;
  constructor(
    private httpClient: HttpClient
  ) { }

  public saveInvoice(invoiceData: InvoiceDetails): Observable<BaseDto<InvoiceDetails>> {
    return this.httpClient.post<BaseDto<InvoiceDetails>>(SparesSalesInvoiceApi.saveInvoice, invoiceData)
  }
  public getSparesSalesInvoiceById(invoiceId: number): Observable<BaseDto<SparesSalesInvoice>> {
    return this.httpClient.get<BaseDto<SparesSalesInvoice>>(`${SparesSalesInvoiceApi.getSpareInvoiceById}/${invoiceId}`)
  }
  
  print(paramname:string,docNo:string, printStatus:string, type:string){
      
      return this.httpClient.get<Blob>(this.printUrl+"/printSalesInvoice", {
          params: new HttpParams().set(paramname, docNo)
                                  .set('printStatus', printStatus)
                                  .set('type', type),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}

