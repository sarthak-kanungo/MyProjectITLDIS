import { WcrReportApi } from './wcr-report-url'

export abstract class WcrReportExcludeUrl {
    static WcrReportExcludeUrls: Array<string> = [
        ...WcrReportApi.getDealerCodeAutocomplete
    ]
}