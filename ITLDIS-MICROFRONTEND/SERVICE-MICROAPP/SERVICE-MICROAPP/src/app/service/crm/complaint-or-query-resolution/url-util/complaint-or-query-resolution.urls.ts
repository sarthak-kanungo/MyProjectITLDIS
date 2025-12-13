import { environment } from "src/environments/environment"

export abstract class ComplaintOrQueryResolutionUrls {

    private static readonly module = 'crm/crmmodule'
    private static readonly controller = 'complaintOrQueryResolution'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${ComplaintOrQueryResolutionUrls.module}/${ComplaintOrQueryResolutionUrls.controller}`

    static readonly updateResolutionDetails = `${ComplaintOrQueryResolutionUrls.apiController}/updateResolutionDetails`

    static readonly lookupCode =  `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`

     /* Search Page Calls */
    static readonly getComplaintOrQueryResolution = `${ComplaintOrQueryResolutionUrls.apiController}/getComplaintOrQueryResolution`
}