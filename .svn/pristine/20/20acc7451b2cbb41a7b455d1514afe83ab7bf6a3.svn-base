import { environment } from '../../../../../environments/environment'


export abstract class ReInstallationApi{
    private static readonly module = 'master'
    private static readonly controller = 'reInstallation'
    private static readonly controllerForDi = 'deliveryInstallation'
    private static readonly controllerForService = 'service'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${ReInstallationApi.controller}`
    static readonly apiControllerForDi = `${environment.baseUrl}/${environment.api}/${ReInstallationApi.controllerForDi}`
    static readonly apiControllerForService = `${environment.baseUrl}/${environment.api}/${ReInstallationApi.module}/${ReInstallationApi.controllerForService}`
   
    static readonly getSeries = `${environment.baseUrl}/${environment.api}/master/product/getSeries`
    static readonly serviceStaffNameAuto = `${ReInstallationApi.apiControllerForDi}/serviceStaffNameAuto`
    static readonly specificationDropdown = `${environment.baseUrl}/${environment.api}/master/service/checkpointSpecification/specificationDropdown`
    static readonly getAllReInstallationDetails = `${environment.baseUrl}/${environment.api}/getAllAggregateWithCheckPoints`
    static readonly reInstallationNumberAuto = `${ReInstallationApi.apiController}/reInstallationNumberAuto`
    static readonly searchRi = `${ReInstallationApi.apiController}/searchRi`
    static readonly chassisNoAuto = `${ReInstallationApi.apiController}/chassisNoAuto`
    static readonly getDetailsByChassisNo = `${ReInstallationApi.apiController}/getDetailsByChassisNo`
    static readonly representativeTypeDropdown = `${ReInstallationApi.apiControllerForDi}/representativeTypeDropdown`
    static readonly reInstallation = `${ReInstallationApi.apiController}`
    static readonly getRiById = `${ReInstallationApi.apiController}/getRiById`
}
//http://192.168.15.192:8383/api/master/service/reinstallation/getAllReInstallationDetails?series=B%20Series