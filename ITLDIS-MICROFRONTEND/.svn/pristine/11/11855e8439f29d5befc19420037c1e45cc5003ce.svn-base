import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { Depo } from '../../pages/purchase-order-create/purchase-order-create';

@Injectable()
export class PoMachineDetailsContainerService {
  private readonly getDataFromItemNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getByItemNo }`;
  private readonly getItemNumberAutocompleteUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getItemNo }`;
  private readonly getItemNumberObjectAutocompleteUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getMachineItemNo }`;
  private readonly getSalesPoApprovalDetailsUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.purchaseOrder }${ urlService.getSalesPoApprovalDetails }`;
  constructor(
    private httpClient: HttpClient
  ) { }

  getItemNumberAutocomplete(itemNo: string) {
    return this.httpClient.get(this.getItemNumberAutocompleteUrl, { params: new HttpParams().set('itemNo', itemNo)});
            
  }
  getItemNumberObjectAutocomplete(itemNo: string) {
    return this.httpClient.get(this.getItemNumberObjectAutocompleteUrl, { params: new HttpParams().set('itemNo', itemNo) })
  }
  // Code commented by Ankit Rai
  getDataFromItemNumber(itemNo: string, depot:Depo) {
    return this.httpClient.get(this.getDataFromItemNumberUrl, { params: new HttpParams().set('itemNo', itemNo).set("requestFrom","SALESPO")
        .set("depot", depot.branchName) })
  }
  // getDataFromItemNumber(itemNo: string, depot:string) {
  //   return this.httpClient.get(this.getDataFromItemNumberUrl, { params: new HttpParams().set('itemNo', itemNo).set("requestFrom","SALESPO")
  //       .set("depot",depot) })
  // }
  getSalesPoApprovalDetails(poId: string) {
    return this.httpClient.get<BaseDto<object[]>>(this.getSalesPoApprovalDetailsUrl, {
      params: new HttpParams().set('poId', poId)
    });
  }
}
