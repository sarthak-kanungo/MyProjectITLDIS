import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class ServiceClaimApprovalSearchService {
  static readonly submitClaimApprovalApi = `${environment.baseUrl}/${environment.api}/service/accpac/searchClaimSubmittedApproval`
   
  constructor(private httpClient: HttpClient) { }

  getClaimList(searchObject){
    return this.httpClient.post(ServiceClaimApprovalSearchService.submitClaimApprovalApi, searchObject);
  }

}
