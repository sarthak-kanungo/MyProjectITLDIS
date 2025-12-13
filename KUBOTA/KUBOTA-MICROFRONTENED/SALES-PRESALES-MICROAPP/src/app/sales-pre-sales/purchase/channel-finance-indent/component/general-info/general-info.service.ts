import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import {  SaveCFI } from 'channel-finance-indent';

@Injectable()
export class GeneralInfoService {
  private readonly getBanksFromDealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.dealerbankdetails}${urlService.getBankListByDealer}`;
  private readonly getDealerDetailsFromBankUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.dealerbankdetails}${urlService.getDealerBankDetailsByBankAndDealer}`;
  private readonly getAvailableInvoicesUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.getInvoices}`;
  private readonly getCFIByIdUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.getChannelFinanceIndentById}`;
  private readonly saveCFIDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.saveChannelFinanceIndent}`;
  private readonly updateStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}`;
  //private readonly saveinvoiceDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.channelFinanceIndent}${urlService.saveInvoiceData}`;
  private readonly printIndentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}${urlService.printIndentUrl}`;
  
  constructor(private httpClient: HttpClient) { }

  getBanksFromDealerCode(dealerCode: string) {
    return this.httpClient.get(`${this.getBanksFromDealerCodeUrl}`, {
      params: new HttpParams().set('dealerCode', dealerCode)
    })
  }

  getDealerDetailsFromBank(dealerCode: string, bankName: string) {
    return this.httpClient.get(`${this.getDealerDetailsFromBankUrl}`, {
      params: new HttpParams().set('dealerCode', dealerCode).set('bankName', bankName)
    })
  }
  getAvailableInvoices(dealerCode: string, formValue: object) {
    let finalObj = {}
    finalObj['dealerCode'] = dealerCode;
    finalObj['flexiLoanAmount'] = formValue['flexiLoanAccountNumber'];
    finalObj['indentAmount'] = formValue['indentAmount'];
    finalObj['bankName'] = formValue['bankName']
    return this.httpClient.post(this.getAvailableInvoicesUrl, finalObj)
  }
  saveCFIData(finalCfiData: SaveCFI) {
    return this.httpClient.post(this.saveCFIDataUrl, finalCfiData)
  }
  getCFIById(cfiId: string) {
    return this.httpClient.get(`${this.getCFIByIdUrl}/${cfiId}`)
  }
  updateIndentStatus(indentNo:string,status:string,remark:string){
      return this.httpClient.post(this.updateStatusUrl+'/updateIndentStatus', {'indentNumber':indentNo,'indentStatus':status,'approverRemarks':remark});
  }
  /*saveInvoiceData(data: InvoiceDetails[]){
    return this.httpClient.post(this.saveinvoiceDataUrl, data)
  }*/
  
  printIndent(indentNumber: string, printStatus:string) {
      return this.httpClient.get<Blob>(this.printIndentUrl, {
          params: new HttpParams().set('indentNumber', indentNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}