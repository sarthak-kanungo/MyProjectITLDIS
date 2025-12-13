import { environment } from "src/environments/environment";

export abstract class viewDetails{
    private static readonly module = 'spares'
    private static readonly controller = 'branchTransfer';
    

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${viewDetails .module }`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    static readonly getTransitReport = `${viewDetails.apiController}/transitreport/getTransitReport`;
}