import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { SaveInvoice } from '../../model/save-invoice-adaptor.service';
import { CancelInvoice } from '../../model/cancel-invoice.adaptor';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class InvoiceCreateApiService {
  private readonly saveSalesInvoiceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.saveSalesInvoice }`;
  private readonly getInvoiceByIdUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceById }`;
  private readonly cancelInvoiceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.cancelInvoice }`;
  private readonly printInvoiceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.reports }${ urlService.printInvoice }`;
  constructor(private httpClient: HttpClient) { }
  saveSalesInvoice(invoice: SaveInvoice) {
    return this.httpClient.post(this.saveSalesInvoiceUrl, invoice);
  }
  getInvoiceById(invoiceId: string) {
    return this.httpClient.get(`${ this.getInvoiceByIdUrl }/${ invoiceId }`);
  }
  cancelInvoice(cancelInvoice: CancelInvoice) {
    return this.httpClient.post(this.cancelInvoiceUrl, cancelInvoice);
  }
  printInvoice(invoiceNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printInvoiceUrl, {
          params: new HttpParams().set('invoiceNumber', invoiceNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
