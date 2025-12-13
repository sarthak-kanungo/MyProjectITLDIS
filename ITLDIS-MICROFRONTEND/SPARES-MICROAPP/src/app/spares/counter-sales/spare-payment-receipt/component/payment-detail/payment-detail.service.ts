import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparesPaymentReceiptApi } from '../../url-utils/spare-payment-receipt-urls';

@Injectable()
export class PaymentDetailService {

  constructor(private httpClient: HttpClient) { }

  getCheckDdBankType() {
    return this.httpClient.get(`${SparesPaymentReceiptApi.getCheckDdBankUrl}`)
  }
  getCardType() {
    return this.httpClient.get(`${SparesPaymentReceiptApi.getCardTypeUrl}`)
  }
  getBankType() {
    return this.httpClient.get(`${SparesPaymentReceiptApi.getBankTypeUrl}`)
  }
}