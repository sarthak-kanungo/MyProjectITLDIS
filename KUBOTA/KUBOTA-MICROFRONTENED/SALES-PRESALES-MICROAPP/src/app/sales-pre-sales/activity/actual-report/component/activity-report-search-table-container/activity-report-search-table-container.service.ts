import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ActivityReportSearchTableContainerService {
  
  constructor(private httpClient: HttpClient) { }
  private readonly searchMarketingActivitystatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityStatus}`


  getSearchActivityStatus() {
    return this.httpClient.get(this.searchMarketingActivitystatusUrl)
  }
}
