import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { BaseDto } from 'BaseDto';
import { url } from 'inspector';

@Injectable()
export class MarketingActivitySearchService {

    private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
    private readonly orgLevelByHODeptUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelByHODeptUrl}`
    private readonly orgLevelHierForParentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.orgLevelHierForParentUrl}`
    private static readonly stateUrl = `${environment.baseUrl}${urlService.api}/warranty/pcr/getDealerStates`
    private static readonly updateGracePeriodUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.updateGracePeriodUrl}`
    constructor(
        private httpClient: HttpClient,
      ) { }

    getDealerCodeList(dealerCode:string){
        return this.httpClient.get(`${this.dealerCodeUrl}`, {
            params: new HttpParams().set('dealerCode', dealerCode)
          })
    }
    getStates(dealerId:number){
        return this.httpClient.get<BaseDto<Array<Object>>>(MarketingActivitySearchService.stateUrl,{
            params: new HttpParams()
                .append('dealerId', dealerId+"")
        })  
    }
    getOrgLevelByHODept(deptCode:string){
        return this.httpClient.get(`${this.orgLevelByHODeptUrl}`, {
            params: new HttpParams().set('deptCode', deptCode)
        })
    }
    
    getOrgLevelHierForParent(levelId:string, hierId:string){
        return this.httpClient.get(`${this.orgLevelHierForParentUrl}`, {
            params: new HttpParams().set('levelId', levelId)
                .set('hierId', hierId)
        })
    }

    updateActivityGracePeriod(activityId){
        return this.httpClient.get(MarketingActivitySearchService.updateGracePeriodUrl, {
            params: new HttpParams().set('activityId', activityId)
        })
    }
}
