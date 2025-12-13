import { SapApi } from './sap-urls';

export abstract class SapExcludedUrl {
    static sapExcludedUrls: Array<string> = [
        SapApi.getActivityProposalSearchForListing
    ]
}
