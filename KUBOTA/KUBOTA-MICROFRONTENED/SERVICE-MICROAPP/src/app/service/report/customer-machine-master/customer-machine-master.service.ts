import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class CustomerMachineMasterService {
  private static readonly searchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/customerMachineMasterReport`;
  private static readonly searchMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/enquiry/getMobileNumber`
  private static readonly exportUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/exportCustomerMachineMasterReport`;
  
  constructor(private httpClient : HttpClient) { }

  getSearchList(searchFilter:any){
    return this.httpClient.post(CustomerMachineMasterService.searchUrl, searchFilter);
  }

  getMobileNoList(searchValue){
    return this.httpClient.get(CustomerMachineMasterService.searchMobileNoUrl, {
      params : new HttpParams().set('mobileNumber',searchValue)
        .set('requestFrom','CustomerMachineMasterReport')
    });
  }

  exportSearchList(searchObj){
    return this.httpClient.post<Blob>(CustomerMachineMasterService.exportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }
}
