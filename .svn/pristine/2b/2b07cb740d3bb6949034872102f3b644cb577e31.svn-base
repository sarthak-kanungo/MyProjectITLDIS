import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class CancelInvoiceFormApiService {

  private readonly getInvoiceCancellationTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceCancellationType }`;
  private readonly getInvoiceCancellationReasonUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceCancellationReason }`;
  private readonly getBrandsUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.enquiry }${ urlService.getBrands }`;
  private readonly getInvoiceCancellationOtherReasonUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceCancellationOtherReason }`;

  constructor(
    private httpClient: HttpClient
  ) { }
  getInvoiceCancellationType() {
    return this.httpClient.get<BaseDto<any>>(this.getInvoiceCancellationTypeUrl);
  }
  getInvoiceCancellationReason() {
    return this.httpClient.get<BaseDto<any>>(this.getInvoiceCancellationReasonUrl);
  }
  getBrand() {
    return this.httpClient.get<BaseDto<any>>(this.getBrandsUrl);
  }
  getInvoiceCancellationOtherReason() {
    return this.httpClient.get<BaseDto<any>>(this.getInvoiceCancellationOtherReasonUrl);
  }
}
