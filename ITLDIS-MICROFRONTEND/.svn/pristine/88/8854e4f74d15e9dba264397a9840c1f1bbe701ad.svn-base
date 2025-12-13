import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';
import { ApproveProposalDomain } from 'approval';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class ApprovalService {

    //POST /api/salesandpresales/marketingActivityProposal/approveMarketingActivityProposal
    private readonly approveMarketingActivityProposal = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.approveMarketingActivityProposal}`;
    private readonly getZonalAndRegionalManagerForDealer = ``
    private readonly approveMarketingActivityClaim=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityClaim}${urlService.approveMarketingActivityClaim}`;

    constructor(
        private http: HttpClient
    ) { }

    approveProposalOrClaim(domain: ApproveProposalDomain) : Observable<BaseDto<string>> {
        console.log(domain)
        return this.http.post<BaseDto<string>>(this.approveMarketingActivityProposal, domain)
    }

    approveClaim(domain: ApproveProposalDomain) : Observable<BaseDto<string>> {
        console.log(domain)
        return this.http.post<BaseDto<string>>(this.approveMarketingActivityClaim, domain)
    }

}