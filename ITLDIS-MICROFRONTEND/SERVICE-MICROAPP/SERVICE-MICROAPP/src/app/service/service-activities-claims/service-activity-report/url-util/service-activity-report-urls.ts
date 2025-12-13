import { environment } from '../../../../../environments/environment'
import { urlService } from '../../../../webservice-config/baseurl'


export abstract class ServiceActivityReportApi {
    private static readonly module = 'service'
    private static readonly controller = 'activityProposal'
    private static readonly controllerForActivityReport = 'activityReport'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${ServiceActivityReportApi.module}/${ServiceActivityReportApi.controller}`
    static readonly apiControllerForActivityReport = `${environment.baseUrl}/${environment.api}/${ServiceActivityReportApi.module}/${ServiceActivityReportApi.controllerForActivityReport}`

    static readonly getAllActivityType = `${ServiceActivityReportApi.apiController}/sbjc/getActiveActivityType`
    static readonly getActivityProposalSearchForListing = `${ServiceActivityReportApi.apiController}/getActivityProposalSearchForListing`
    static readonly getAllProduct = `${ServiceActivityReportApi.apiController}/getAllProduct`
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`
    static readonly getActivityEffectiveness = `${environment.baseUrl}/${environment.api}/salesandpresales/marketingActivityClaim/getActivityEffectiveness`
    static readonly activityNumberAuto = `${ServiceActivityReportApi.apiControllerForActivityReport}/activityNumberAuto`
    static readonly getHeaderDetails = `${ServiceActivityReportApi.apiControllerForActivityReport}/getHeaderDetails`
    static readonly getJobCardDetails = `${ServiceActivityReportApi.apiControllerForActivityReport}/getJobCardDetails`
    static readonly getMachineDetails = `${ServiceActivityReportApi.apiControllerForActivityReport}/getMachineDetails`
    static readonly getServiceDetails = `${ServiceActivityReportApi.apiControllerForActivityReport}/getServiceDetails` 
    static readonly saveServiceActivityReport = `${ServiceActivityReportApi.apiControllerForActivityReport}/saveServiceActivityReport` 
    static readonly serviceActivityReportSearch = `${ServiceActivityReportApi.apiControllerForActivityReport}/serviceActivityReportSearch` 
    static readonly getActivityReportById = `${ServiceActivityReportApi.apiControllerForActivityReport}/getActivityReportById`
    static readonly getActivityNumberForSearch = `${ServiceActivityReportApi.apiControllerForActivityReport}/getActivityNumberForSearch`
    static readonly printServiceActivityReport=`${ServiceActivityReportApi.apiControllerForActivityReport}/printServiceActivityReport`
}