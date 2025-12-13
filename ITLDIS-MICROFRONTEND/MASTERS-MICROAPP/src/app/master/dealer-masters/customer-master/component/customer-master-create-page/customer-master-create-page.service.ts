import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerMasterDto, SoilTypeDropdown, CropsGrownDropdown, DealerInformation } from '../../domain/customer-master-domain';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { BaseDto } from 'BaseDto';

@Injectable()
export class CustomerMasterCreatePageService {

  constructor(
    private httpClient: HttpClient
  ) { }

  submitCustomerMaster(formData: CustomerMasterDto): Observable<CustomerMasterDto> {
    return this.httpClient.post<CustomerMasterDto>(CustomerMasterApi.submitCreateDto, formData);
  }
  approveRejectChangeRequest(formData){
      return this.httpClient.post(CustomerMasterApi.submitChangeRequest, formData);
  }
  
  getCustomerDetails(customerCode:string) : Observable<BaseDto<CustomerMasterDto>> {
    return this.httpClient.get<BaseDto<CustomerMasterDto>>(CustomerMasterApi.getCustomerMaster, {
          params : new HttpParams().set("customerCode", customerCode)
      });
  }
  
  getCustomerChangeRequestDetails(customerCode:string) : Observable<BaseDto<CustomerMasterDto>> {
    return this.httpClient.get<BaseDto<CustomerMasterDto>>(CustomerMasterApi.getCustomerMasterChangeRequest, {
          params : new HttpParams().set("customerCode", customerCode)
      });
  }
  
  getSoilTypeDropdown(): Observable<BaseDto<Array<SoilTypeDropdown>>> {
    return this.httpClient.get<BaseDto<Array<SoilTypeDropdown>>>(CustomerMasterApi.soilTypeDropdown)
  }
  
  getCropsGrownDropdown(): Observable<BaseDto<Array<CropsGrownDropdown>>> {
    return this.httpClient.get<BaseDto<Array<CropsGrownDropdown>>>(CustomerMasterApi.cropsGrownDropdown)
  }
  
  getDealerRegionInfo(): Observable<BaseDto<Array<DealerInformation>>>{
      return this.httpClient.get<BaseDto<Array<DealerInformation>>>(CustomerMasterApi.getDealerRegionInfo)
      
  }
      
}
