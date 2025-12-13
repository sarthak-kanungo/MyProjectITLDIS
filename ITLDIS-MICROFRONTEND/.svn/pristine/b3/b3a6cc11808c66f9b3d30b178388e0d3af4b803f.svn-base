import { environment } from '../../../../../environments/environment'
import { urlService } from '../../../../webservice-config/baseurl';


export abstract class ServiceActivityClaimApi {
    private static readonly module = 'service'
    private static readonly controller = 'activityProposal'
    private static readonly controllerForClaim = 'activityClaim'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${ServiceActivityClaimApi.module}/${ServiceActivityClaimApi.controller}`
    static readonly apiControllerForClaim = `${environment.baseUrl}/${environment.api}/${ServiceActivityClaimApi.module}/${ServiceActivityClaimApi.controllerForClaim}`

    static readonly getAllActivityType = `${ServiceActivityClaimApi.apiController}/sbjc/getActiveActivityType`
    static readonly getActivityNumberForActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/getActivityNumberForActivityClaim`
    static readonly getHeaderDataForActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/getHeaderDataForActivityClaim`
    static readonly getHeadsDataForActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/getHeadsDataForActivityClaim`
    static readonly getSubActivityForActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/getSubActivityForActivityClaim`
    static readonly getActivityReportPhotosByProposalId = `${ServiceActivityClaimApi.apiControllerForClaim}/getActivityReportPhotosByProposalId`
    static readonly getActivityEffectiveness = `${environment.baseUrl}/${environment.api}/salesandpresales/marketingActivityClaim/getActivityEffectiveness`
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`
    static readonly saveServiceActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/saveServiceActivityClaim`
    static readonly approveServiceActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/approveServiceActivityClaim`
    static readonly serviceActivityClaimSearch = `${ServiceActivityClaimApi.apiControllerForClaim}/serviceActivityClaimSearch`
    static readonly getActivityClaimById = `${ServiceActivityClaimApi.apiControllerForClaim}/getActivityClaimById`
    static readonly getActivityClaimNumberForSearch = `${ServiceActivityClaimApi.apiControllerForClaim}/getActivityClaimNumberForSearch`
    static readonly getActivityNumberForSearch = `${ServiceActivityClaimApi.apiControllerForClaim}/getActivityNumberForSearch`
    static readonly generateDealerInvoice = `${ServiceActivityClaimApi.apiControllerForClaim}/generateDealerInvoice`
    // update api
    static readonly editServiceActivityClaim = `${ServiceActivityClaimApi.apiControllerForClaim}/editServiceActivityClaim`
    static readonly getReportImagesByReportId = `${ServiceActivityClaimApi.apiControllerForClaim}/getReportImagesByReportId`
    
}
//http://localhost:8383/api/service/activityClaim/getActivityReportPhotosByProposalId?activityNumberId=59