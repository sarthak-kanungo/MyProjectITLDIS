import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SubmitSearchDto, CustomerSearchDto } from '../../domain/customer-master-domain';
import { Observable } from 'rxjs';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { BaseDto } from 'BaseDto';

@Injectable()
export class CustomerMasterSearchPageService {
  
  constructor(
    private httpClient: HttpClient
  ) { }

  submitSearchForm(formData: SubmitSearchDto): Observable<BaseDto<Array<CustomerSearchDto>>> {
    return this.httpClient.post<BaseDto<Array<CustomerSearchDto>>>(CustomerMasterApi.submitSearchDto, formData)
  }
  
  submitCustomerApprovalSearchForm(formData: SubmitSearchDto): Observable<BaseDto<Array<CustomerSearchDto>>> {
    return this.httpClient.post<BaseDto<Array<CustomerSearchDto>>>(CustomerMasterApi.submitSearchApprovalDto, formData)
  }
  
}
