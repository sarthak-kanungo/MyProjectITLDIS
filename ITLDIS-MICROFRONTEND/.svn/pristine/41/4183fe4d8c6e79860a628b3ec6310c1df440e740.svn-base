import { Injectable, EventEmitter } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AddPaymentReceiptDataDomain } from 'CreatePaymentReceiptModule';

@Injectable()
export class PaymentReceiptService {
  addPaymentReceiptDataDomain = {} as AddPaymentReceiptDataDomain;
  private readonly getReceiptTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getReceiptType}`;
  private readonly getReceiptModeUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getReceiptMode}`;
  private readonly getCheckDdBankUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getCheckDdBank}`
  private readonly getCardTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getCardType}`
  private readonly getBankTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getBank}`
  private readonly getEnquiryCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryCode}`
  private readonly getEnquiryDetailsFromEnqNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getEnquiryPayment}`
  private readonly savePaymentReceiptUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.addPayment}`
  // private readonly getExchangeAmountUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getExchangeAmount}`
  // private readonly getLoanAmountUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getLoanAmount}`
  // private readonly getMarginAmountUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getMarginAmount}`
  // private readonly getSubsidyAmountUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.paymentReceipt}${urlService.getSubsidyAmount}`
  private readonly printReceiptUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}${urlService.getPaymentReceiptPrint}`
  public submitOrReset = new EventEmitter<string>();

  constructor(private http: HttpClient) { }

  dropdownPaymentReceiptType(enqNo: string) {
    return this.http.get(`${this.getReceiptTypeUrl}`,{
      params: new HttpParams().set('enquiryNumber', enqNo)
    })
  }
  dropdownReceiptModeType() {
    return this.http.get(`${this.getReceiptModeUrl}`)
  }
  dropdownCheckDdBankType() {
    return this.http.get(`${this.getCheckDdBankUrl}`)
  }
  dropdownCardType() {
    return this.http.get(`${this.getCardTypeUrl}`)
  }
  dropdownBankType() {
    return this.http.get(`${this.getBankTypeUrl}`)
  }

  searchByEnquiryCode(enquiryNo) {
    return this.http.get(`${this.getEnquiryCodeUrl}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo)
    })
  }

  getEnquiryDetailsFromEnqNumber(enquiryNo) {
    return this.http.get(`${this.getEnquiryDetailsFromEnqNumberUrl}`, {
      params: new HttpParams().set('enquiryNo', enquiryNo)
    })
  }

  savePaymentReceipt(formData: AddPaymentReceiptDataDomain) {
    return this.http.post(`${this.savePaymentReceiptUrl}`, formData)
  }

  // populateExchangeAmount(enquiryNo) {
  //   return this.http.get(`${this.getExchangeAmountUrl}`, {
  //     params: new HttpParams().set('enquiryNo', enquiryNo)
  //   })
  // }
  // populateLoanAmount(enquiryNo) {
  //   return this.http.get(`${this.getLoanAmountUrl}`, {
  //     params: new HttpParams().set('enquiryNo', enquiryNo)
  //   })
  // }

  // populateMarginAmount(enquiryNo) {
  //   return this.http.get(`${this.getMarginAmountUrl}`, {
  //     params: new HttpParams().set('enquiryNo', enquiryNo)
  //   })
  // }

  // populateSubsidyAmount(enquiryNo) {
  //   return this.http.get(`${this.getSubsidyAmountUrl}`, {
  //     params: new HttpParams().set('enquiryNo', enquiryNo)
  //   })
  // }
  
  printReceipt(receiptNumber: string, printStatus:string) {
      return this.http.get<Blob>(this.printReceiptUrl, {
          params: new HttpParams().set('receiptNumber', receiptNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
