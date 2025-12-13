import { PcrApi } from './product-concern-report.urls';

export abstract class PCRExcludeUrl {
    static PCRExcludeUrls: Array<string> = [
        ...PcrApi.autoCompletePartFailureCode,
        ...PcrApi.autoCompletePcrNo,
        ...PcrApi.autoCompleteSearchChassisNo,
        ...PcrApi.searchAutoCompleteEngineNo,
    ]
}