import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of, BehaviorSubject } from 'rxjs';
import { SelectList, SelectListAdapter } from '../../../../../core/model/select-list.model';
import { InvoiceService } from './invoice.service';
import { CustomerByCustomerCode } from '../../model/invoice-customer-detail-by-customer-code-adaptor.service';
import { InvoiceDcByCustomerCode, InvoiceDcByCustomerCodeAdaptorService } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class InvoiceApiService {

  private readonly getCustomerCodeAutoCompleteUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getCustomerCodeAutoComplete }`;
  private readonly getDcAndCustomerDetailsByCustomerCodeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getDcAndCustomerDetailsByCustomerCode }`;
  private readonly getInvoiceTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceType }`;
  private readonly getBankNameUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getBankName }`;

  private invoiceDcByCustomerCode$ = new BehaviorSubject<BaseDto<InvoiceDcByCustomerCode[]>>(null);
  private customerByCustomerCode$ = new BehaviorSubject<BaseDto<CustomerByCustomerCode>>(null);

  constructor(
    private httpClient: HttpClient,
    private selectListAdapter: SelectListAdapter
  ) { }

  searchByBankName(searchValue: string) {
    let keyMap = {
      id: 'id',
      value: 'bankName'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getBankNameUrl, {
      params: new HttpParams().set('bankName', searchValue)
    }).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    )
  }
  getInvoiceType() {
    let keyMap = {
      id: 'id',
      value: 'invoiceType'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getInvoiceTypeUrl).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    )
  }
  searchCustomerCode(searchValue: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getCustomerCodeAutoCompleteUrl, {
      params: new HttpParams().set('customerCode', searchValue)
    });
  }
  getCustomerDetailsByCustomerCode(customerCode: string) {
    this.httpClient.get<BaseDto<{ dcDetails: InvoiceDcByCustomerCode[], customerDetails: CustomerByCustomerCode }>>(this.getDcAndCustomerDetailsByCustomerCodeUrl, {
      params: new HttpParams().set('customerCode', customerCode)
    }).subscribe(res => {
      this.invoiceDcByCustomerCode$.next({ ...res, ...{ result: res.result.dcDetails } });
      this.customerByCustomerCode$.next({ ...res, ...{ result: res.result.customerDetails } });

    })
    return this.customerByCustomerCode$;
  }
  getDcByCustomerCode(searchValue: string) {
    return this.invoiceDcByCustomerCode$

    // let dc = this.invoiceDcByCustomerCodeAdaptorService.adapt<InvoiceDcByCustomerCode[]>(this.);
  }
}
