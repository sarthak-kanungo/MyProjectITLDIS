import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpParams, HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class MachineTransferRequestCreateService {
  machineTransferFrom: FormGroup
  private readonly searchBytoDealerUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getTrasnferFromDealerCode}`;
  private readonly searchByrequestNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.searchRequestNumber}`;
  private readonly getDateUrl = `${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`;
  getDetailsFromLogindataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getDealerCodeAndDealerNameAndGstNo}`;
  
  constructor(private httpClient: HttpClient,
    private fb: FormBuilder) { }



  getDate() {
    return this.httpClient.get(this.getDateUrl)
  }

  searchByrequestNo(searkKey: string) {
    return this.httpClient.get(this.searchByrequestNoUrl, {
      params: new HttpParams().set('requestNumber', searkKey)
    })
  }


  searchBytoDealer(searchKey: string) {
    return this.httpClient.get(this.searchBytoDealerUrl, {
      params: new HttpParams().set('code', searchKey)
    })
  }


  getDetailsFromLogindata() {
    return this.httpClient.get(this.getDetailsFromLogindataUrl)
  }

}
