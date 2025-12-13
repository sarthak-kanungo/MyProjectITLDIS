import { Injectable } from '@angular/core';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ActivityProposalListSearchDomain, SearchFilterActivityProposalDomain } from 'ActivityProposalModule';

@Injectable()
export class MarketingActivitySearchContainerService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  private readonly searchActivityTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchactivitytype}`

  private readonly searchMarketingActivitystatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityStatus}`
  ///api/salesandpresales/marketingActivityStatus

  private readonly activityNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.searchActivityNumber}`
  // GET /api/salesandpresales/marketingActivityProposal/searchActivityNumber

  private readonly marketingActivityProposalSearch = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/marketingActivityProposalSearch`

  getSearchActivityType() {
    return this.httpClient.get(this.searchActivityTypeUrl)
  }
  getSearchActivityStatus() {
    return this.httpClient.get(this.searchMarketingActivitystatusUrl)
  }

  getActivityNo(activityNumber: string, functionality:string) {
    return this.httpClient.get(`${this.activityNoUrl}`, {
      params: new HttpParams().set('activityNumber', activityNumber)
          .set('functionality', functionality)
    })
  }

  searchUsingFilter(filter: SearchFilterActivityProposalDomain): Observable<BaseDto<Array<ActivityProposalListSearchDomain>>> {
    return this.httpClient.post<BaseDto<Array<ActivityProposalListSearchDomain>>>(
      this.marketingActivityProposalSearch, filter)
  }

  
  private prepareHttpParams(filter: SearchFilterActivityProposalDomain) {
    let httpParams = new HttpParams()
    for (const key of Object.keys(filter)) {
      console.log(key, filter[key])
      if (filter[key]) {
        if (key == 'toDate' || key == 'fromDate') {
          httpParams = httpParams.append(key, this.convertDateToOurFormat(filter[key]))
        }
        else httpParams = httpParams.append(key, filter[key])
      }
    }
    console.log(httpParams)
    return httpParams
  }

  private convertDateToOurFormat(dt: string) {
    if (dt) {
      return new Date(dt).toJSON().slice(0, 10).split('-').reverse().join('-')
    }
    return ''
  }
}
