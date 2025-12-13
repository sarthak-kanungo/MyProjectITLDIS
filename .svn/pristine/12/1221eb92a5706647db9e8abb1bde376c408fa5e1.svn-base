import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class SearchInvoiceApiService {
  private readonly searchByInvoiceNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchByInvoiceNumber }`;
  private readonly searchByChassisNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchByChassisNumber }`;
  private readonly searchByCustomerNameUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchByCustomerName }`;
  private readonly searchByMobileNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchByMobileNumber }`;
  private readonly searchByEnquiryNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchByEnquiryNumber }`;
  private readonly getInvoiceTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceType }`;
  private readonly getInvoiceStatusUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getInvoiceStatus }`;
  private readonly getEnquiryTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.enquiry }${ urlService.getEnquiryType }`;
  private readonly getItemNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.machineMaster }${ urlService.autoCompleteItems }`;
  
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService,
  ) { }
  searchByInvoiceNumber(invoiceNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByInvoiceNumberUrl, {
      params: new HttpParams().set('invoiceNumber', invoiceNumber)
    });
  }
  searchByChassisNumber(chassisNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByChassisNumberUrl, {
      params: new HttpParams().set('chassisNumber', chassisNumber)
    });
  }
  searchByCustomerName(customerName: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByCustomerNameUrl, {
      params: new HttpParams().set('customerName', customerName)
    });
  }
  searchByMobileNumber(mobileNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByMobileNumberUrl, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    });
  }
  searchByEnquiryNumber(enquiryNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByEnquiryNumberUrl, {
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    });
  }
  getInvoiceType() {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getInvoiceTypeUrl);
  }
  getEnquiryType() {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getEnquiryTypeUrl);
  }
  getInvoiceStatus() {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getInvoiceStatusUrl);
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
  searchItemNo(itemNo: string) {
      return this.httpClient.get<BaseDto<SelectList[]>>(`${ this.getItemNoUrl }`, {
        params: new HttpParams().set('itemNo', itemNo)
            .set('productGroup','')
            .set('functionality','INVOICE_SEARCH')
      });
    }
}
