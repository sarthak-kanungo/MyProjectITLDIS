import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { CreateQuotation } from './create-quotation';

@Injectable()
export class QuotationCreateService {

  private readonly addQuotationUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.addQuotation }`;
  private readonly printQuotationUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.reports }${ urlService.printQuotation }`;
  private readonly getQuotationByQuotationNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getQuotationByQuotationNumber }`;

  constructor(
    private httpClient: HttpClient
  ) { }
  addQuotation(data: CreateQuotation) {
    return this.httpClient.post(this.addQuotationUrl, data);
  }
  getQuotationByQuotationNumber(quotationNumber: string) {
    return this.httpClient.get(this.getQuotationByQuotationNumberUrl, {
      params: new HttpParams().set('quotationNumber', quotationNumber)
    })
  }
  printQuotation(quotationNumber: string, printStatus:string,gstOrWithoutgst:string) {
      return this.httpClient.get<Blob>(this.printQuotationUrl, {
          params: new HttpParams().set('quotationNumber', quotationNumber)
                                  .set('printStatus', printStatus)
                                  .set('gstOrWithoutgst', gstOrWithoutgst),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
