import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SearchTransferEnquiryByValue, TransferEnquiryDomain, SendTransferEnquiry } from 'TransferSearchCriteria';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class TransferSearchCriteriaService {
 selectedEnquiryIdsForTransfer: Array<number> = []
 selectedSalesPersonIdsForTransfer: Array<number> = []
  sendTransferEnquiry = {} as SendTransferEnquiry
  private readonly getAllTehsilURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getAllTehsil}`
  private readonly getEnquiryToTransferURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryToTransfer}`
  private readonly addTransferURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.addTransfer}`
  private readonly getSalesPersonNameInTransferURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getSalesPersonNameInTransfer}`
//http://192.168.15.109:8383/api/salesandpresales/enquiry/getSalesPersonNameInTransfer?id=2
  constructor(
    private http: HttpClient
  ) { }

  getAllTehsil(textValue) {
    return this.http.get(`${this.getAllTehsilURL}`, {
      params: new HttpParams().set('textValue', textValue)
    })
  }

  getEnquiryToTransfer(sendData: TransferEnquiryDomain): Observable<BaseDto<Array<SearchTransferEnquiryByValue>>> {
    return this.http.post<BaseDto<Array<SearchTransferEnquiryByValue>>>(this.getEnquiryToTransferURL, sendData)
  }

  addTransfer(fromData) {
    return this.http.post(`${this.addTransferURL}`, fromData)
  }

  addToSelectedEnquiries(enqId: number) {
    this.selectedEnquiryIdsForTransfer.push(enqId)
  }

  removeFromSelectedEnquiries(enqId: number) {
    this.selectedEnquiryIdsForTransfer = this.selectedEnquiryIdsForTransfer.filter( enq => enq !== enqId)
  }
  getSalesPersonNameInTransfer(id: number[]) {
    return this.http.get(`${this.getSalesPersonNameInTransferURL}`, {
      params: new HttpParams().set('id', id.toString())
    })
  }

  addToSelectedSalesPersonName(salesPersonId: number) {
    this.selectedSalesPersonIdsForTransfer.push(salesPersonId)
  }

  removeFromSelectedSalesPersonName(salesPersonId: number) {
    this.selectedSalesPersonIdsForTransfer = this.selectedSalesPersonIdsForTransfer.filter( sales => sales !== salesPersonId)
  }
}
