import { environment } from '../../../../../environments/environment';

export abstract class SapApi{
    private static readonly module = 'service'
    private static readonly controller = 'activityProposal'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SapApi.module}/${SapApi.controller}`
  
    static readonly getAllActivityType = `${SapApi.apiController}/sbjc/getActiveActivityType`
    static readonly getAllProduct = `${SapApi.apiController}/getAllProduct`
    static readonly getMaxAllowedBudgetByNumberPerson = `${SapApi.apiController}/getMaxAllowedBudgetByNumberPerson`
    static readonly calculationForActivityType = `${SapApi.apiController}/calculationForActivityType`
    static readonly getAllHeadsByActivityTypeId = `${SapApi.apiController}/getAllHeadsByActivityTypeId`
    static readonly getSubActivityByActivityTypeId = `${SapApi.apiController}/getSubActivityByActivityTypeId`
    static readonly saveServiceActivityProposal = `${SapApi.apiController}/saveServiceActivityProposal`
    static readonly serviceActivityProposalSearch = `${SapApi.apiController}/serviceActivityProposalSearch`
    static readonly getServiceActivityProposalByActivityNumber = `${SapApi.apiController}/getServiceActivityProposalByActivityNumber`
    static readonly getActivityProposalSearchForListing = `${SapApi.apiController}/getActivityProposalSearchForListing`
    static readonly getServiceActivityProposalStatus = `${SapApi.apiController}/getServiceActivityProposalStatus`
    static readonly approveServiceActivityProposal = `${SapApi.apiController}/approveServiceActivityProposal`

    // Api for Bulk service activity proposal
    static readonly getProposalsPendingForApproval = `${SapApi.apiController}/getProposalPendingForApproval`
    static readonly activityProposalGroupApproval=`${SapApi.apiController}/activityProposalGroupApproval`
} 