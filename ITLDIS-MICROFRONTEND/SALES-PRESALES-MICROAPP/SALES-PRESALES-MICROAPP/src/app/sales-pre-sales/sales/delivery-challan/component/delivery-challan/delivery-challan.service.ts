import { Injectable } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class DeliveryChallanService {
  private readonly getMachineDetailsFromRequestUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getMachineAndDealerDetailsByRequestNumber}`;
  private readonly getMachineDetailsFromEnquiryUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getMachineDetailsByEnquiryNumber}`;
  private readonly searchByRequestNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcRequestNumberAutoComplete}`;
  private readonly searchByEnquiryNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getEnquiryNumberAutoComplete}`;
  private readonly searchByAllotmentNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.allotmentNumberAutoComplete}`;
  private readonly getImplementsDetailFromAllotmentUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.allotmentDetails}`;
  private readonly getDcTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDeliveryChallanType}`;
  private readonly getSystemGeneratedDateUrl = `${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient,
  ) { }

  createDeliveryChallanFrom() {
    return this.fb.group({
      deliveryChallanNumber: [{ value: null, disabled: true }, Validators.compose([])],
      deliveryChallanDate: [{ value: new Date(), disabled: true }, Validators.compose([])],
      deliveryChallanType: [null, Validators.compose([Validators.required])],
      enquiry: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      enquiryType: [{ value: null, disabled: true }, Validators.compose([])],
      dealerMachineTransfer: [null, Validators.compose([Validators.required])],
      status: [{ value: null, disabled: true }, Validators.compose([])],
      allotmentNumber: [{ value: null, disabled: true }, Validators.compose([])],
      machineAllotment: [{ value: null, disabled: true }, Validators.compose([])],
      allotmentDate: [{ value: null, disabled: true }, Validators.compose([])],
      gatePassNumber: [{ value: null, disabled: true }, Validators.compose([])],
      gatePassDate: [{ value: null, disabled: true }, Validators.compose([])],
      remarks: [{ value: null, disabled: false }, Validators.compose([])],
    })
  }


  getServerDate() {
    return this.httpClient.get(this.getSystemGeneratedDateUrl);
  }
  getDcType() {
    return this.httpClient.get(this.getDcTypeUrl);
  }
  searchByEnquiryNumber(searchKey: string, dcType: string) {
    return this.httpClient.get(this.searchByEnquiryNumberUrl,
      { params: new HttpParams().set('enquiryNumber', searchKey).set('dcType', dcType) });
  }
  searchByAllotmentNumber(searchKey: string) {
    return this.httpClient.get(this.searchByAllotmentNumberUrl,
      { params: new HttpParams().set('allotmentNumber', searchKey) });
  }
  searchByRequestNumber(searchKey: string) {
    return this.httpClient.get(this.searchByRequestNumberUrl,
      { params: new HttpParams().set('requestNumber', searchKey) });
  }
  getMachineDetailsFromEnquiry(enquiryNumber: string) {
    return this.httpClient.get(this.getMachineDetailsFromEnquiryUrl, {
      params: new HttpParams().set('enquiryNumber', enquiryNumber)
    })
  }
  getMachineDetailsFromRequest(requestNumber: string) {
    return this.httpClient.get(this.getMachineDetailsFromRequestUrl, {
      params: new HttpParams().set('requestNumber', requestNumber)
    })
  }
  getImplementsDetailFromAllotment(allotmentId: string) {
    return this.httpClient.get(this.getImplementsDetailFromAllotmentUrl, {
      params: new HttpParams().set('allotmentId', allotmentId)
    })
  }
}
