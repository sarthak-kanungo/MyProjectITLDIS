import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateCustomerCodeAuto, CustomerTypeDropdown, CustomerGroupAuto, TitleDropdown, QualificationDropdown, PreferrdeLanguageDropdown } from '../../domain/customer-master-domain';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { BaseDto } from 'BaseDto'

@Injectable()
export class CustomerDetailsService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getCreateCustomerCodeAuto(): Observable<CreateCustomerCodeAuto> {
    return this.httpClient.get<CreateCustomerCodeAuto>(CustomerMasterApi.createCustomerCodeAuto)
  }

  getCustomerTypeDropdown(): Observable<BaseDto<Array<CustomerTypeDropdown>>> {
    return this.httpClient.get<BaseDto<Array<CustomerTypeDropdown>>>(CustomerMasterApi.customerTypeDropdown)
  }

  getCustomerGroupAuto(): Observable<BaseDto<Array<CustomerGroupAuto>>> {
    return this.httpClient.get<BaseDto<Array<CustomerGroupAuto>>>(CustomerMasterApi.customerGroupAuto)
  }

  getTitleDropdown(): Observable<BaseDto<Array<TitleDropdown>>> {
    return this.httpClient.get<BaseDto<Array<TitleDropdown>>>(CustomerMasterApi.titleDropdown)
  }
  getQualificationDropdown(): Observable<BaseDto<Array<QualificationDropdown>>> {
    return this.httpClient.get<BaseDto<Array<QualificationDropdown>>>(CustomerMasterApi.qualificationDropdown)
  }

  getPreferrdeLanguageDropdown(): Observable<BaseDto<Array<PreferrdeLanguageDropdown>>> {
    return this.httpClient.get<BaseDto<Array<PreferrdeLanguageDropdown>>>(CustomerMasterApi.preferrdeLanguageDropdown)
  }
  
  validateMobileNumber(mobileNumber: string, customerCode:string): Observable<any> {
      return this.httpClient
        .get(CustomerMasterApi.validateMobileNumber, {
          params: new HttpParams().append("mobileNumber", mobileNumber)
                  .append("customerCode", customerCode),
        });
    }
}
