import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerCodeAuto, MobileNumberAuto } from '../../domain/customer-master-domain';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';

@Injectable()
export class SearchCustomerService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getCustomerCodeAuto(): Observable<CustomerCodeAuto> {
    return this.httpClient.get<CustomerCodeAuto>(CustomerMasterApi.customerCodeAuto)
  }

  getMobileNumberAuto(): Observable<MobileNumberAuto> {
    return this.httpClient.get<MobileNumberAuto>(CustomerMasterApi.mobileNumberAuto)
  }
  

  
  customerCodeAuto(value:string){
      return this.httpClient.get(CustomerMasterApi.customerCodeAuto, {
          params : new HttpParams().set('customerCode',value)
      })
  }
  
  customerMobileNo(value:string){
      return this.httpClient.get(CustomerMasterApi.mobileNumberAuto, {
          params : new HttpParams().set('mobileNumber',value)
      })
  }
}
