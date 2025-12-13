import { ServiceActivityClaimApi } from './service-activity-claim-urls';

export abstract class ServiceActivityClaimExcludedUrl {
    static serviceActivityClaimExcludedUrls: Array<string> = [
        ServiceActivityClaimApi.getActivityNumberForActivityClaim
    ]
}