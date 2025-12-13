import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class ApprovalService {

    private readonly approveMarketingActivityProposal = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/activityProposalGroupApproval`;
    private readonly approveMarketingActivityClaim=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}/activityClaimGroupApproval`;
    private readonly getMarketingActivityClaim=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.marketingActivityClaimPendingForApproval}`;
    private readonly getMarketingActivityProposal=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.marketingActivityProposalPendingForApproval}`;

    constructor(
        private http: HttpClient
    ) { }

    approveProposal(domain) : Observable<BaseDto<string>> {
        return this.http.post<BaseDto<string>>(this.approveMarketingActivityProposal, domain)
    }

    approveClaim(domain) : Observable<BaseDto<string>> {
        return this.http.post<BaseDto<string>>(this.approveMarketingActivityClaim, domain)
    }

    getClaimsPendingForApproval(){
        return this.http.get(this.getMarketingActivityClaim);
    }

    getProposalsPendingForApproval(){
        return this.http.get(this.getMarketingActivityProposal);
    }
}